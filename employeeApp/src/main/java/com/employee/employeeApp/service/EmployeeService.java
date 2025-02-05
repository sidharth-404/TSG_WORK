package com.employee.employeeApp.service;

import com.employee.employeeApp.Model.Employee;
import com.employee.employeeApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRespo;

    public Employee addEmployee(Employee employee)
    {
        return employeeRespo.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRespo.findAll();
    }
}
