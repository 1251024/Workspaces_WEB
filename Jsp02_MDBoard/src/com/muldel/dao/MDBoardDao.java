package com.muldel.dao;

import java.util.List;

import com.muldel.dto.MDBoardDto;

public interface MDBoardDao {

	String SELECT_LIST_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
							+ " FROM MDBOARD ORDER BY SEQ DESC ";
	String SELECT_ONE_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
							+ " FROM MDBOARD WHERE SEQ = ? ";
	String INSERT_SQL = " INSERT INTO MDBOARD "
						+ " VALUES(MDBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
	String UPDATE_SQL = " UPDATE MDBOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ? "; 
	String DELETE_SQL = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
	//전체출력
	public List<MDBoardDto>selectList();
	//선택출력
	public MDBoardDto selectOne(int seq);
	//추가
	public int insert(MDBoardDto dto);
	//수정
	public int update(MDBoardDto dto);
	//삭제
	public int delete(int seq);
	//여러개 삭제
	public int multiDelete(String[] seq);
	
			
}
