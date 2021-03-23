package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import static com.cal.db.JDBCTemplate.*;

public class CalDao {

	public int insertCalBoard(CalDto dto) {
		Connection con = getConnection();
		String sql = " INSERT INTO CALBOARD "
				+ " VALUES(CALBOARDSEQ.NEXTVAL, ?,?,?,?,SYSDATE) ";
		
		PreparedStatement pstm = null;
		int res = 0;					//int값을 돌려받음, int= 1row
		
		try {
			pstm= con.prepareStatement(sql); //stmt와 pstm은 발사 포대 , sql은 야구공
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			System.out.println("3.query 준비 : "+ sql);
			
			res= pstm.executeUpdate();					//값을 보내서 값을 넣었다고 확인받으면(발사버튼)
			System.out.println("4.query 실행 및 리턴");
			if(res>0) {								
				commit(con);				//그럼 sql적용 시켜줘
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

	public List<CalDto> getCalList(String id, String yyyyMMdd) {
		Connection con = getConnection();
		//MDATE: 선택된 날짜, REGDATE: 작성한 날짜
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
					+ " FROM CALBOARD "
					+ " WHERE ID = ? "
					+ " AND SUBSTR(MDATE, 1, 8) = ? ";//SUBSTR:MDATE기록한 것들 중 1~8번째자리까지 짤라오겠다.
		
		PreparedStatement pstm = null;
		ResultSet rs = null;							//값을 넣었다고 확인받으면 데이터, 표를 돌려받음
		List<CalDto> list = new ArrayList<CalDto>();
		
		try {
			pstm = con.prepareStatement(sql);	//물음표 개수만큼 pstm.해준다
			pstm.setString(1, id);				
			pstm.setString(2, yyyyMMdd);
			System.out.println("3.query준비 : "+sql);
			
			rs= pstm.executeQuery();
			System.out.println("4.query 힐행 및 리턴");
			
			while (rs.next()) {							//다음값 있니?	//첫번재 행의
				CalDto dto = new CalDto(rs.getInt(1),	//어느 컬럼에서 값을 가져올거냐	//첫번째 열값
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getDate(6));
				list.add(dto);							//정해진리스트에 dto값을 추가해주겠다.
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list; //짜여진 표값 리턴
	}
	
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		Connection con = getConnection();
		String sql = " SELECT * "
				   + " FROM "
				   + "	( "
				   + "	SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE, 1, 8) ORDER BY MDATE))RN, SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				   + "	FROM CALBOARD "
				   + "	WHERE ID = ? "
				   + "	AND SUBSTR(MDATE, 1, 6)= ? "
				   + "	) "
				   + " WHERE RN BETWEEN 1 AND 4 ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			System.out.println("3.query 준비 :"+sql);
			
			rs = pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while(rs.next()) {
				CalDto dto = new CalDto(rs.getInt(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getDate(7));
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
	
	public int getCalViewCount(String id, String yyyyMMdd) {
		
		Connection con = getConnection();
		String sql = " SELECT COUNT(*) "
				   + " FROM CALBOARD "
				   + " WHERE ID = ? "
				   + " AND SUBSTR(MDATE, 1, 8) = ? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;						//테이블이 리턴, 테이블 값이 숫자일 뿐
		int count = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3. query 준비 : "+ sql);
			
			rs= pstm.executeQuery();
			System.out.println("4. qurey 실행 및 리턴");
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		
		return count;
	}
	
}

