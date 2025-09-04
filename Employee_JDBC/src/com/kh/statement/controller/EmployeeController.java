package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.service.EmployeeService;
import com.kh.statement.model.vo.Employee;

public class EmployeeController {
	
	public List<Employee> findAll(){
		
		List<Employee> employees = new EmployeeService().findAll();
		
		return employees;
	}
	
	public Employee findByDept(String dept_Title) {
		
		Employee employee = new EmployeeService().findByDept(dept_Title);
		
		return employee;
	}

}
