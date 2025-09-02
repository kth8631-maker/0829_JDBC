package com.kh.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Dessert {
	
	private int dessertNo;
	private String dessertName;
	private String category;
	private String ingredient;
	private String calories;
	private Date enrollDate;
	
	public Dessert() {
		super();
	}
	
	

	public Dessert(String dessertName, String category, String ingredient, String calories) {
		super();
		this.dessertName = dessertName;
		this.category = category;
		this.ingredient = ingredient;
		this.calories = calories;
	}



	public Dessert(int dessertNo, String dessertName, String category, String ingredient, String calories,
			Date enrollDate) {
		super();
		this.dessertNo = dessertNo;
		this.dessertName = dessertName;
		this.category = category;
		this.ingredient = ingredient;
		this.calories = calories;
		this.enrollDate = enrollDate;
	}

	public int getDessertNo() {
		return dessertNo;
	}

	public void setDessertNo(int dessertNo) {
		this.dessertNo = dessertNo;
	}

	public String getDessertName() {
		return dessertName;
	}

	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Dessert [dessertNo=" + dessertNo + ", dessertName=" + dessertName + ", category=" + category
				+ ", ingredient=" + ingredient + ", calories=" + calories + ", enrollDate=" + enrollDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(calories, category, dessertName, dessertNo, enrollDate, ingredient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dessert other = (Dessert) obj;
		return Objects.equals(calories, other.calories) && Objects.equals(category, other.category)
				&& Objects.equals(dessertName, other.dessertName) && dessertNo == other.dessertNo
				&& Objects.equals(enrollDate, other.enrollDate) && Objects.equals(ingredient, other.ingredient);
	}
	
	


	
	
	
	
	
	
	

}
