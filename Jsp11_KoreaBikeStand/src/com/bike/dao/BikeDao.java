package com.bike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.BikeDto;

import static com.bike.db.JDBCTemplate.*;


public class BikeDao {

	public boolean insert(List<BikeDto> list) {
		//list 안에 있는 값들 전체 저장
		Connection con = getConnection();
		PreparedStatement pstm = null;
		//-------------------------
		String sql = " INSERT INTO KOREABIKE "
				   + " VALUES (?, ?, ?, ?, ?) ";
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			for(int i = 0;i < list.size();i++) {
				pstm.setString(1, list.get(i).getName());
				pstm.setString(2, list.get(i).getAddr());
				pstm.setDouble(3, list.get(i).getLatitude());
				pstm.setDouble(4, list.get(i).getLongitude());
				pstm.setInt(5, list.get(i).getBike_count());

				pstm.addBatch();
			}
			System.out.println("3.query 준비 :"+sql);
			
			int[] result = pstm.executeBatch();
			System.out.println("4.query 실행 및 리턴");
			for(int i = 0; i< result.length; i++) {
				if(result[i] == -2) {
					res++;
				}
			}
			if(res==list.size()) {
				con.commit();
			}else {
				con.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}


		return (res == list.size())? true: false;
		
	}
	
	public boolean delete() {
		//db 내용 전체 삭제
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
			con=DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2.계정연결");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = " DELETE FROM KOREABIKE ";
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.query 준비 : "+ sql);
			res= pstm.executeUpdate();
			System.out.println("4.query 실행 및 리턴");
			if(res>0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();

				System.out.println("5.db종료");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return (res>0)? true:false;
	}
}
