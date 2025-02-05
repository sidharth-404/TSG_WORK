package com.employee.employeeApp.repository;

import com.employee.employeeApp.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
