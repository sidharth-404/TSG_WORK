package com.mongodb.crud.mongocrudapp.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value="employee")
public class Employee {
	@Id
	private String id;
	private String empName;
	private String location;
	private BigDecimal salary;
	
	public Employee() {
		
	}
	
	
	public Employee(String id, String empName, String location, BigDecimal salary) {
		super();
		this.id = id;
		this.empName = empName;
		this.location = location;
		this.salary = salary;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	

}
