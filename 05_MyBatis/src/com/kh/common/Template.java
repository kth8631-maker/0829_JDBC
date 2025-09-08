package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

	// Connection객체의 역할을 대신할 SqlSession객체를 만들어서 반환해주는 메소드를 구현할 것
	
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		// mybatis-config.xml 파일에 있는 내용을 읽어와서
		// 해당 DB와 접속된 SqlSession객체를 반환
		
		String config = "mybatis-config.xml";
				
		// /는 bin폴더(default output folder)를 의미함
		
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			
			// 1단계 : SqlSessionFactoryBuilder 만들기
			// 만드는 방법 : 기본생성자를 호출한다.
			// new SqlSessionFactoryBuilder();
			// 2단게 : SqlSessionFactory 만들기
			// 만드는 방법 : 메소드를 호출한다.
			// .build(접속 내용을 담은 파일을 읽어온 입력스트림);
			// 3단계 : SqlSession 만들기
			// 만드는 방법 : 메소드를 호출한다.
			// .openSession();
			
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
	
	
	
	
	
	
}
