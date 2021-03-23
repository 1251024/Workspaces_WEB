package com.muldel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.muldel.db.SqlMapConfig;
import com.muldel.dto.MDBoardDto;



public class MDBoardDaoImpl extends SqlMapConfig implements MDBoardDao {
	
	private String namespace = "com.muldel.mapper.";

	@Override
	public List<MDBoardDto> selectList() {
	
		List<MDBoardDto> List = new ArrayList<MDBoardDto>();
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);//기본값은 true
			List = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return List;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		return null;
	}

	@Override
	public int insert(MDBoardDto dto) {
		
		return 0;
	}

	@Override
	public int update(MDBoardDto dto) {
		return 0;
	}

	@Override
	public int delete(int seq) {
		return 0;
	}

	@Override
	public int multiDelete(String[] seqs) {
		int count =0;
		
		//map 컬렉션객체를 파라미터로 전달
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		try(SqlSession session = getSqlSessionFactory().openSession(false);){
			count = session.delete(namespace+"multiDelete",map);
			if(count == seqs.length) {
				session.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
