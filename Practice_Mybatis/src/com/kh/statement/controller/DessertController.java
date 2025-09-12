package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dao.DessertDao;
import com.kh.statement.model.service.DessertService;
import com.kh.statement.model.vo.Dessert;

public class DessertController {
	
	public int save(String dessertName, String category, String ingredient, String calories) {
		
		Dessert dessert = new Dessert(dessertName, category, ingredient, calories);
		
		int result = new DessertService().save(dessert);
		
		return result;
	}
	
	public List<Dessert> findAll() {
		
		List<Dessert> desserts = new DessertService().findAll();
		
		return desserts;
	}

	public Dessert findById(String dessertName) {
		
		Dessert dessert = new DessertService().findById(dessertName);
		
		return dessert;
		
	}
	
	
	
	
	
}
