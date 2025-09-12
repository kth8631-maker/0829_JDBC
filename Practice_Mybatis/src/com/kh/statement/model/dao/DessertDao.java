package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.vo.Dessert;

public class DessertDao {
	
	public int save(Connection conn, Dessert dessert) {
		
		// 0) 변수 세팅
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = """
						 INSERT 
						   INTO 
						        TB_DESSERT
						 VALUES 
						        (
						        SEQ_DESSERTNO.NEXTVAL
						      , ?
						      , ?
						      , ?
						      , ?
						      , SYSDATE
						        )
					""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dessert.getDessertName());
			pstmt.setString(2, dessert.getCategory());
			pstmt.setString(3, dessert.getIngredient());
			pstmt.setString(4, dessert.getCalories());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	public List<Dessert> findAll(Connection conn) {
		
		List<Dessert> desserts = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = """
						SELECT
							   DESSERTNO
							 , DESSERTNAME
							 , CATEGORY
							 , INGREDIENT
							 , CALORIES
							 , ENOLLDATE
						  FROM
						       TB_DESSERT
						 ORDER
						    BY
						       ENOLLDATE DESC
							
				
					""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Dessert dessert = new Dessert(rset.getInt("DESSERTNO")
											 ,rset.getString("DESSERTNAME")
											 ,rset.getString("CATEGORY")
											 ,rset.getString("INGREDIENT")
											 ,rset.getString("CALORIES")
											 ,rset.getDate("ENOLLDATE"));
				desserts.add(dessert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return desserts;
		
	}
	
	public Dessert findById(Connection conn, String dessertName) {
		
		Dessert dessert = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
						SELECT
						       DESSERTNO
						     , DESSERTNAME
						     , CATEGORY
						     , INGREDIENT
						     , CALORIES
						     , ENOLLDATE
						  FROM
						       TB_DESSERT
						 WHERE
						       DESSERTNAME = ?
					""";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dessertName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dessert = new Dessert(rset.getInt("DESSERTNO")
									 ,rset.getString("DESSERTNAME")
									 ,rset.getString("CATEGORY")
									 ,rset.getString("INGREDIENT")
									 ,rset.getString("CALORIES")
									 ,rset.getDate("ENOLLDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return dessert;
		
	}

	

}
