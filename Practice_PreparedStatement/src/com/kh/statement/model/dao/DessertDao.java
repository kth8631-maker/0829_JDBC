package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Dessert;

public class DessertDao {
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "KTH04";
	private final String PASSWORD = "KTH041234";
		
	public int save(Dessert dessert) {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """ 
						INSERT
				      	  INTO
				               MEMBER
				        VALUES 
				               (
				               SEQ_USERNO.NEXTVAL
				             , ?
				             , ?
				             , ?
				             , ?
				         	   SYSDATE
				       			)
					""";
		
		try {
			// 1) JDBC Driver등록
			Class.forName("DRIVER");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			// 3_1) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// 3_2) 미완성된 SQL문 완성시켜주기
			pstmt.setString(1, dessert.getDessertName());
			pstmt.setString(2, dessert.getCategory());
			pstmt.setString(3, dessert.getIngredient());
			pstmt.setString(4, dessert.getCalories());
			
			// 4, 5) DB에 전달된 SQL문을 실행하고 결과 받기
			result = pstmt.executeUpdate();
			
			// 6) 트랜잭션 처리
			if(result > 0) {
				conn.commit();
			}
					                           			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) JDBC close()
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		// 8) 결과반환
		return result;
	}
	
	public List<Dessert> findAll() {
		
		// 0) 필요한 변수 세팅
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Dessert> desserts = new ArrayList();
		
		try {
			// 1) JDBC Driver 등록
			Class.forName(DRIVER);
			
			// 2) Connection 생성
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(
										"""
										  SELECT
										         DESSERTNO
										       , DESSERTNAME
										       , CATEGORY
										       , INGREDIENT
										       , CALORIES
										       , ENROLLDATE
										    FROM 
										         MEMBER
										   ORDER 
										      BY 
										         ENROLLDATE DESC
										"""
										 );
			
			// 4, 5) SQL(SELECT)문 실행 후 결과(ResultSet)받기
			rset = pstmt.executeQuery();
			
			// 6) 결과값 매핑
			while(rset.next()) {
				Dessert dessert = new Dessert();
				
				dessert.setDessertNo(rset.getInt("DESSERTNO"));
				dessert.setDessertName(rset.getString("DESSERTNAME"));
				dessert.setCategory(rset.getString("CATEGORY"));
				dessert.setIngredient(rset.getString("INGREDIENT"));
				dessert.setCalories(rset.getString("CALORIES"));
				dessert.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				desserts.add(dessert);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7. JDBC용 객체 반납 (생성의 역순)
			
			try {
				if(rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 8. 컨트롤러로 결과 반환
		return desserts;
	}
	
	
	
	
	
	
	
	
	

}
