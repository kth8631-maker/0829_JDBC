package com.kh.statement.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.statement.model.dao.DessertDao;
import com.kh.statement.model.vo.Dessert;

public class DessertService {
	
	private Connection conn = null;
	
	public DessertService() {
		this.conn = getConnection();
	}
	
	public int save(Dessert dessert) {
		Connection conn = getConnection();
		
		int result = new DessertDao().save(conn, dessert);
		
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	public List<Dessert> findAll(){
		
		Connection conn = getConnection();
		
		List<Dessert> desserts = new DessertDao().findAll(conn);
		
		close(conn);
		
		return desserts;
		
	}
	
	public Dessert findById(String dessertName) {
		
		Dessert dessert = new DessertDao().findById(conn, dessertName);
		
		close(conn);
		
		return dessert;
	}

}
