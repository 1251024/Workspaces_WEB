package com.board.dao;

import static com.board.db.JDBCTemplate.*;//템플릿 임포트 필수!!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.dto.MyBoardDto;

//DAO:Data Access Object :DB와 연결, DB접근하는 거-항상하는 125는 템플릿으로 만들자
//문서를 보고 어떤 메소드가 필요한지 메소드를 잘만들자
public class MyBoardDao_old {

	//전체출력
	
	//파라미터:메소드 외부에서 전달되는 값을 받아서 메소드 내부에서 사용하는 변수
	//()-> 다오의 파라미터는 db에 전달할 값
	//List<MyBoardDto> -> 다오의 리턴타입은 db에서 가져올 값
	//리턴타입 dto를 묶어줘야해서 collecion을 사용해야하는데 순서가 필요해서 list 사용
	
	public List<MyBoardDto> selectList(){
		//1.driver연결
		//2.계정연결
		//1번과 2번은 템플릿에 완료해서 가져오기만
		Connection con  = getConnection();
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
					+ " FROM MYBOARD "
					+ " ORDER BY SEQ DESC ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		//3,4번은 왜 밖으로 안빼는 건지 검사하지 않는 숙제
		try {
			//3.query준비
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비");

			//4.query 실행 및 리턴
			rs=pstm.executeQuery();
			while(rs.next()) {
				MyBoardDto dto=new MyBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
				list.add(dto);
			}
			System.out.println("4.query 실행 및 리턴");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. db 종료
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");

		}
		
		return list;
	}
	
	//하나출력
	//유일한 하나를 식별할 기본키 int seq
	public MyBoardDto selectOne(int seq) {
		Connection con  = getConnection();
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
					+ " FROM MYBOARD "
					+ " WHERE SEQ = ? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyBoardDto dto = null;	//변수명을 선언해주고 밑에 dto=해줘야함

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3.query 준비 : "+sql);
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				dto = new MyBoardDto(rs.getInt("SEQ"),
									 rs.getString("WRITER"),
								 	 rs.getString("TITLE"),
									 rs.getString("CONTENT"),//content 오타 java.sql.SQLException: 부적합한 열 이름
									 rs.getDate("REGDATE"));
			}
			System.out.println("4.query 실행 및 리턴");
			
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
	
	
	//추가
	//파라미터에 물음표 3개의 값 넣어서 전달해주면
	//int가 리턴 n개의 rows가 inserted가 됨
	public int insert(MyBoardDto dto){
		Connection con = getConnection();
		String sql = " INSERT INTO MYBOARD "
					+ " VALUES(MYBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비 : "+sql);
			
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
	
	
	//수정
	public int update(MyBoardDto dto){
		Connection con =getConnection();
		String sql = " UPDATE MYBOARD "
					+ " SET TITLE = ?, CONTENT = ? "
					+ " WHERE SEQ = ? "; 
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3.query 준비 : "+sql);
			
			res= pstm.executeUpdate();
			if(res>0) {
				commit(con);
				
			}
			System.out.println("4.query 실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		return res;
	}
	
	
	//삭제
	//파라미터 MyBoardDto dto로 해도되지만 한번만 삭제하니까
	//pk를 사용하여 유일한 하나를 찾아서 삭제하자

	//where는 값에 맞는 row를 찾음
	//pk가 아닌 경우 해당 값에 맞는 로우를 여러개 찾아서 여러개 삭제됨
	
	public int delete(int seq) {
		Connection con =getConnection();
		String sql = " DELETE FROM MYBOARD "
					+ " WHERE SEQ = ? ";
		PreparedStatement pstm = null;
		
		int res= 0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비 : "+sql);
			
			res= pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
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
