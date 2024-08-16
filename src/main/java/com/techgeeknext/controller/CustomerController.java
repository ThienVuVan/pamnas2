package com.techgeeknext.controller;

import com.techgeeknext.entity.Customer;
import com.techgeeknext.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        String message = "Service is running smoothly!";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @GetMapping("/get/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") String customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @DeleteMapping("/delete/customer/{id}")
    public String deleteCustomerById(@PathVariable("id") String customerId) {
        return  customerRepository.deleteCustomerById(customerId);
    }

    @PutMapping("/update/customer/{id}")
    public String updateCustomer(@PathVariable("id") String customerId, @RequestBody Customer customer) {
        return customerRepository.updateCustomer(customerId,customer);
    }
}
