package com.kh.statement.model.vo;

import java.util.Objects;

public class Employee {
	
	private String emp_Id;
	private String emp_Name;
	private String salary;
	private String dept_Title;
	private String job_Name;
	
	public Employee() {
		super();
	}

	public Employee(String emp_Id, String emp_Name, String salary, String dept_Title, String job_Name) {
		super();
		this.emp_Id = emp_Id;
		this.emp_Name = emp_Name;
		this.salary = salary;
		this.dept_Title = dept_Title;
		this.job_Name = job_Name;
	}

	public String getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(String emp_Id) {
		this.emp_Id = emp_Id;
	}

	public String getEmp_Name() {
		return emp_Name;
	}

	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDept_Title() {
		return dept_Title;
	}

	public void setDept_Title(String dept_Title) {
		this.dept_Title = dept_Title;
	}

	public String getJob_Name() {
		return job_Name;
	}

	public void setJob_Name(String job_Name) {
		this.job_Name = job_Name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dept_Title, emp_Id, emp_Name, job_Name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(dept_Title, other.dept_Title) && Objects.equals(emp_Id, other.emp_Id)
				&& Objects.equals(emp_Name, other.emp_Name) && Objects.equals(job_Name, other.job_Name)
				&& Objects.equals(salary, other.salary);
	}
	
	
	

}
