package com.mongodb.crud.mongocrudapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.crud.mongocrudapp.dto.EmployeeDto;
import com.mongodb.crud.mongocrudapp.model.Employee;
import com.mongodb.crud.mongocrudapp.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public String createEmployee(@RequestBody EmployeeDto employee) {
		return service.createEmployee(employee);
	}
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmplOyee(){
		return service.getAllEmployee();
		
	}
	
	@DeleteMapping("/delete/{empId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteEmployee(@PathVariable String empId) {
		return service.deleteEmployee(empId);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public String updateEmployee(@RequestBody EmployeeDto dto)
	{
		return service.updateEmployee(dto);
	}

}
