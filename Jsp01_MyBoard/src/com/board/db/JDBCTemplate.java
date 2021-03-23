package com.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// 1. driver 연결
	// 2. 계정연결
	// 5. close 객체 -트라이캐치 또해야하니까

	public static Connection getConnection() {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver연결");
			// ojdbc6 추가해야함(webcontent폴더에 webINF폴더에 lib폴더에 추가)-web app libraries가 생김

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2. 계정연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null; // return con 으로 잡아주기

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정 연결");

			// 우리가 원할 때 commit /rollback 할 수 있도록,
			// connection 객체의 auto commit 속성을 false로 바꿈.
			con.setAutoCommit(false);

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
