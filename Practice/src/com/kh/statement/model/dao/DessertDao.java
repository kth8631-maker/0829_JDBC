package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.statement.model.vo.Dessert;

public class DessertDao {

	public int save(Dessert dessert) {

		
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		String sql = "INSERT "
				    +  "INTO "
				          + "MEMBER "
				   + "VALUES "
				          + "("
				          + "SEQ_USERNO.NEXTVAL"
				        + ",'" + dessert.getDessertName() + "'"
				        + ",'" + dessert.getCategory() + "'"
				        + ",'" + dessert.getIngredient() + "'"
				        + ",'" + dessert.getCalories() + "'"
				        + ", SYSDATE"
				          + ")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE"
					                           , "KTH04"
					                           , "KTH041234");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);

			if(result > 0) {
				conn.commit();
			}
					                           			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(stmt != null) {
					stmt.close();
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
		return result;
		
		
	}
	

}
