package com.muldel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.muldel.dto.MDBoardDto;

import static com.muldel.db.JDBCTemplate.*;


//import static com.muldel.db.JDBCTemplate.*;대신 extend JDBCTemplate해도된다

public class MDBoardDaoImpl implements MDBoardDao {

	@Override
	public List<MDBoardDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();


		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.query 준비 :" + SELECT_LIST_SQL);

			rs= pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while(rs.next()) {
				//밖에있으면 메모리에 dto객체에 하나만 만들어짐
				//마지막에 연결된 dto 담겨진 객체 하나만 남겨지기 때문에
				//while문안에 있어서 변수의 생명주기가 달라짐
				MDBoardDto dto = new MDBoardDto();

				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm= null;
		ResultSet rs = null;
		MDBoardDto dto = null;		//new MDBoardDto()로 하는거랑 null값인거랑 차이있음 npe hell

		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3.query 준비 : "+SELECT_ONE_SQL);
			
			rs= pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");

			while(rs.next()) {
				dto = new MDBoardDto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3.query 준비 : "+INSERT_SQL);
			
			res=pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3.query 준비 : "+UPDATE_SQL);
			
			
			res= pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");

			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3.query 준비 : "+DELETE_SQL);
			
			res=pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		return res;
	}

	@Override
	public int multiDelete(String[] seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		int[] cnt = null;
		
		try {
			pstm= con.prepareStatement(DELETE_SQL);
			
			for(int i = 0; i<seq.length;i++) {
				pstm.setString(1, seq[i]);//쿼리에 숫자는 싱글쿼테이션이 생략가능하다
				
				pstm.addBatch();//성공하면 -2, 실패하면 -3 ->자바에서 그렇게 지정해둠
				System.out.println("3.query 준비 : "+DELETE_SQL+"(삭제할 번호 :"+seq[i]+")");
			}
			
			cnt=pstm.executeBatch();//int형 배열로 리턴됨 ->예를 들면 [-2, -2, -2, -3]
			System.out.println("4.query 실행 및 리턴");
			
			
			for(int i = 0; i<cnt.length;i++) {	//0번지부터 마지막번지까지서 성공했는지 아닌지 확인한다
				if(cnt[i]==-2) {				//cnt[i]의 결과값을 확인해서 -2,-3를 확인해서 
					res++;						//성공했으면 +1
				}
			}
			//성공했으면 commit
			if(seq.length==res) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		
		return res;
	}

}
