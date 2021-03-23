package com.answer.dao;

import static com.answer.db.JDBCTemplate.close;
import static com.answer.db.JDBCTemplate.commit;
import static com.answer.db.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		// 1.2번은 겟커넥션으로 끝남
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();

		try {
			// 3.
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.query 준비 :" + SELECT_LIST_SQL);

			// 4.
			rs = pstm.executeQuery();
			System.out.println("4.query실행 및 리턴");

			while (rs.next()) { // 로우 한줄 가져오는애
				AnswerDto dto = new AnswerDto(rs.getInt(1), 
											  rs.getInt(2), 
											  rs.getInt(3), 
											  rs.getInt(4), 
											  rs.getString(5),
											  rs.getString(6), 
											  rs.getString(7), 
											  rs.getString(8), 
											  rs.getDate(9));

				list.add(dto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			System.out.println("3.query 준비: " + SELECT_ONE_SQL);

			rs = pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while (rs.next()) {
				dto.setBoardno(rs.getInt("BOARDNO"));
				dto.setGroupno(rs.getInt("GROUPNO"));
				dto.setGroupseq(rs.getInt("GROUPSEQ"));
				dto.setTitletab(rs.getInt("TITLETAB"));
				dto.setDelflag(rs.getString("DELFLAG"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegdate(rs.getDate("REGDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");

		}

		return dto;
	}

	@Override
	public boolean boardInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(BOARD_INSERT_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			System.out.println("3.query 준비 :" + BOARD_INSERT_SQL);

			res = pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return (res > 0) ? true : false;
	}

	@Override
	public boolean boardUpdate(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(BOARD_UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			System.out.println("3.query 준비 : " + BOARD_UPDATE_SQL);

			res = pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
				commit(con);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return false;
	}

	@Override
	public boolean boardDelete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(BOARD_DELETE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3.query준비:" + BOARD_DELETE_SQL);

			res = pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return (res > 0) ? true : false;
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
			System.out.println("3.query 준비 :" + ANSWER_UPDATE_SQL);

			res = pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			pstm = con.prepareStatement(ANSWER_INSERT_SQL);
			pstm.setInt(1, dto.getBoardno()); 		//부모의 글번호
			pstm.setInt(2, dto.getBoardno()); 		//부모의 글번호
			pstm.setInt(3, dto.getBoardno()); 		//부모의 글번호
			pstm.setString(4, dto.getTitle());		//글
			pstm.setString(5, dto.getContent());	//내용
			pstm.setString(6, dto.getWriter());		//작성자
			System.out.println("3.query 준비:" + ANSWER_INSERT_SQL);

			res = pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

}
