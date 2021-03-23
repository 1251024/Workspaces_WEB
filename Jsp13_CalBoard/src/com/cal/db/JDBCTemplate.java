package com.cal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// dao에서 db연결할 때마다 사용하기 때문에 static영역에 올리고 필요할때마다 사용
		//non static이면 데이터베이스에서 호출할때마다 객체를 만들어야 함 -객체 만들면 heap영역에 저장되고 메모리 낭비됨
		public static Connection getConnection() {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");//Class.forName:다른라이브러리에 있는 클래스를 가져오겠다.//ojdbc6 라이브러리의 패키지경로
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
				con.setAutoCommit(false);									//오토커밋 기능을 비활성화
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
