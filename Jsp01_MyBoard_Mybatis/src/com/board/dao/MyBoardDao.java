package com.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.board.db.SqlMapConfig;
import com.board.dto.MyBoardDto;

public class MyBoardDao extends SqlMapConfig{

	//전체 출력
	public List<MyBoardDto> selectList(){
		SqlSession session = null;
		
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		//ibatis 3.대부터 mybatis로 바뀌었음, ibatis는 옛날 버전
		session = getSqlSessionFactory().openSession(true);
		list = session.selectList("boardmapper.selectList");//boardmapper에서 selectList찾아서 실행해라
		
		session.close();
		
		return list;
	}
	//하나 출력
	public MyBoardDto selectOne(int seq) {
		//1. 세션준비
		SqlSession session = null;	
		//2.리턴할 값 정리
		MyBoardDto dto = new MyBoardDto();
		
		try {
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne("boardmapper.selectOne", seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return dto;
	}
	
	
	//추가
	public int insert(MyBoardDto dto){
		int res = 0;
		
		//try with resources ->finally할때 자동종료 session에 클로즈 구문 안써줘도 됨
		try (SqlSession session = getSqlSessionFactory().openSession(true);){
			res = session.insert("boardmapper.insert", dto);
			
		}catch (Exception e) {
		}
		
		return res;
	}
	
	
	//수정
	public int update(MyBoardDto dto){
		
		return 0;
	}
	
	
	//삭제
	public int delete(int seq) {
		
		return 0;
	}
	
}
