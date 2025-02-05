package com.employee.employeeApp.service;

import com.employee.employeeApp.Model.Customer;
import com.employee.employeeApp.repository.CustomerRepository;
import com.employee.employeeApp.tenantConfig.TenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer>  getALlCustomer(){
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        if("shared".equals(TenantIdentifierResolver.getTenantType())){
            customer.setTenantId(TenantIdentifierResolver.getCurrentTenant());
        }
        return customerRepository.save(customer);
    }

}
