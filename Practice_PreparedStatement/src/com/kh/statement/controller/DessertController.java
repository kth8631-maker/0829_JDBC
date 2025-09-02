package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dao.DessertDao;
import com.kh.statement.model.vo.Dessert;

public class DessertController {
	
	public int save(String dessertName, String category, String ingredient, String calories) {
		
		Dessert dessert = new Dessert(dessertName, category, ingredient, calories);
		
		int result = new DessertDao().save(dessert);
		
		return result;
	}
	
	public List<Dessert> findAll() {
		
		List<Dessert> desserts = new DessertDao().findAll();
		
		return desserts;
	}

}
