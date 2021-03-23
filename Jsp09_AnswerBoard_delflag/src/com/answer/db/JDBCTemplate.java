package com.answer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2.계정연결");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// statement가 부모라서 pstm은 자식객체라서 statement를 상속받음
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// setautocommit false로 해놨기때문에 원할때 commit 하고 rollback하기 위해서 모듈화시킴
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
