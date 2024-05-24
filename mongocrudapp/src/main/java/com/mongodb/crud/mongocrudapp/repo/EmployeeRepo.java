package com.mongodb.crud.mongocrudapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.crud.mongocrudapp.model.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {

}
