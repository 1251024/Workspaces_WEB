package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;

import static com.answer.db.JDBCTemplate.*;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		//1.2번은 겟커넥션으로 끝남
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			//3.
			pstm= con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.query 준비 :"+SELECT_LIST_SQL);
			
			//4.
			rs = pstm.executeQuery();
			System.out.println("4.query실행 및 리턴");
			
			while(rs.next()) {	//로우 한줄 가져오는애
				AnswerDto dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));//컬럼하나가져오는애
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				
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
	public AnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AnswerDto dto = new AnswerDto();
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3.query 준비: "+SELECT_ONE_SQL);
			
			rs=pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while(rs.next()) {
				dto.setBoardno(rs.getInt("BOARDNO"));
				dto.setGroupno(rs.getInt("GROUPNO"));
				dto.setGroupseq(rs.getInt("GROUPSEQ"));
				dto.setTitletab(rs.getInt("TITLETAB"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWriter(rs.getString("WRITER"));
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
	public int boardInsert(AnswerDto dto) {
		
		
		return 0;
	}

	@Override
	public int boardUpdate(AnswerDto dto) {
		
		
		return 0;
	}

	@Override
	public int boardDelete(int boardno) {
		return 0;
	}

	@Override
	public int answerUpdate(int parentBoardNo) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(ANSWER_UPDATE_SQL);
			pstm.setInt(1, parentBoardNo);
			pstm.setInt(2, parentBoardNo);
			System.out.println("3.query 준비 :"+ANSWER_UPDATE_SQL);
			
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
	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm=con.prepareStatement(ANSWER_INSERT_SQL);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			System.out.println("3.query 준비:"+ANSWER_INSERT_SQL);
			
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

}
