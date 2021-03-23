package com.muldel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// public 어디서든 접근가능한 static 스태틱메모리로 만들고 Connection 커넥션을 리터타입으로 받는다 
	// 그래서 return con;을 받음 getConnection은 메소드명
	// JDBC할 때 1.driver연결, 2. 계정연결은 반복적으로 공통으로 쓰이기 때문에 한번에 만들어놓고 가져다 쓸려고 템플릿으로 만듦
	// 다오에서 쓸려고 임포트함 import static com.mdboard.db.JDBCTemplate.*;
	// getConnection 메소드를 만들어서 가져다 쓰려고 메소드를 생성해줌 Connection con = getConnection();
	
	// 한줄로 줄어들게 됨 => getConnection(); 메소드호출명령

	// 접근제어자 메모리영역 리턴타입 메소드명(){
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
			System.out.println("2.계정연결");
			
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
