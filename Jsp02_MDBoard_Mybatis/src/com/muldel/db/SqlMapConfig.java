package com.muldel.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		//inputstream 대신에 reader 써도 된다 /사용한 객체만 다름 
		//리더로 리소스 읽어온거고
		//리더가 좀더 추상화된 객체라 리더할때 한줄씩 읽어오는것이 가능
		String resource = "com/muldel/db/config.xml";
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return sqlSessionFactory;
	}
	
}
