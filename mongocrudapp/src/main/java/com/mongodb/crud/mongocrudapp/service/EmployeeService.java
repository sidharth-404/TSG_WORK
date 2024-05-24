package com.mongodb.crud.mongocrudapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.crud.mongocrudapp.dto.EmployeeDto;
import com.mongodb.crud.mongocrudapp.model.Employee;
import com.mongodb.crud.mongocrudapp.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repository;
	
	public String createEmployee(EmployeeDto employee) {
		try {
		Employee emp=new Employee();
	   emp.setEmpName(employee.getEmpName());
	   emp.setLocation(employee.getLocation());
	   emp.setSalary(employee.getSalary());
	   repository.save(emp);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return "employee id added successfully";
		
	}
	
	public List<Employee> getAllEmployee(){
		List<Employee> empList=new ArrayList<>();
		try {
			empList=repository.findAll();
		}
		catch(Exception e) {
			
		}
		return empList;
	}
	
	public String deleteEmployee(String empId) {
		try {
		repository.deleteById(empId);
		}
		catch(Exception e) {
			
		}
		return "deleted successfully";
	}
	
	
	public String updateEmployee(EmployeeDto employee) {
		try {
		Employee emp=new Employee();
		emp.setId(employee.getId());
	   emp.setEmpName(employee.getEmpName());
	   emp.setLocation(employee.getLocation());
	   emp.setSalary(employee.getSalary());
	   repository.save(emp);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return "employee id updated successfully";
		
	}
	
	

}
