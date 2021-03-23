package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;

import static com.mvc.db.JDBCTemplate.*;

public class MVCBoardDaoImpl implements MVCBoardDao {

	@Override
	public List<MVCBoardDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();

		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.query 준비 :" + SELECT_LIST_SQL);

			rs = pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while (rs.next()) {
				MVCBoardDto dto = new MVCBoardDto();

				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));

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
	public MVCBoardDto selectOne(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCBoardDto dto = null;

		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3.query 준비 : " + SELECT_ONE_SQL);

			rs = pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");

			while (rs.next()) {
				dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
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
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;

		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3.query 준비 : " + INSERT_SQL);

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
		}

		return res;
	}

	@Override
	public int update(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		// 1. driver연결 2. 계정연결 3. query 준비 4.query실행 및 리턴 5.db종료
		// pstm 불러왔는데 con인 이유에 대하여-> Connection을 항상해줘야함
		// 1,2번은 jdbc템플릿에 담아놓고 그걸 getConnection();으로 호출해오고
		// 계정연결되어있어야 statement구문을 쓸수 있음
		// 다오에 sql문을 담아놓고 변수로 불러와서 sql문 사용

		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3.query 준비 : " + UPDATE_SQL);

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
			System.out.println("3.query 준비 : " + DELETE_SQL);

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
		}

		return res;
	}

}
