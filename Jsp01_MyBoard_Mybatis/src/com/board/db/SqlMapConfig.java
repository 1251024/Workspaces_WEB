package com.board.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sqlSessionFactory;	//명시적 생성자 정의
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		//경로일 때는 /를 사용한다.
		String resource = "com/board/db/mybatis-config.xml";//마이바티스사용하려면 이게 필요
		//1. mybatis-config.xml 가져와서 
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);//2.읽어들여서 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//4. sqlSessionFactory 객체를 만든다 = 3. sqlSessionFactory 빌더를 만들어서 
			//sqlSessionFactory는 다오에서 쓸거임
			inputStream.close();//inputStream 닫아줌
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return sqlSessionFactory;
	}
	
}
