package com.kh.statement.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.vo.Employee;

public class EmployeeDao {
	
	private Properties prop = new Properties();
	
	public EmployeeDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/employee-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> findAll(Connection conn){
		
		List<Employee> employees = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Employee employee = new Employee(rset.getString("EMP_ID")
						,rset.getString("EMP_NAME")
						,rset.getString("SALARY")
						,rset.getString("DEPT_TITLE")
						,rset.getString("JOB_NAME"));
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employees;
	}
	
	public Employee findByDept(Connection conn, String dept_Title) {
		
		Employee employee = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findByDept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept_Title);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				employee = new Employee(rset.getString("EMP_NAME")
									   ,rset.getString("DEPT_TITLE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return employee;
		
		
	}

}
