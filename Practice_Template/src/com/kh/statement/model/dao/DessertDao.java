package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.statement.model.vo.Dessert;

public class DessertDao {
	
	public int save(Connection conn, Dessert dessert) {
		
		// 0) 변수 세팅
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = """
						INTSERT 
						   INTO 
						        DESSERT
						 VALUES 
						        (
						        SEQ_USERNO.NEXTVAL
						      , ?
						      , ?
						      , ?
						      , ?
						      , SYSDATE
						        )
					""";
		
		
		
		
		
		
	}
	

	

}
