package com.example.myappDB.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myappDB.entities.Customer;
import com.example.myappDB.service.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return (List<Customer>)customerService.findAll();
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable Integer id) {
//		Optional<Customer> val = customerService.findById(id);
//
//        if (val.isPresent()) {
//            System.out.println(val.get());
//        } else {
//            System.out.printf("No customer found with id %d%n", id);
//        }
		return customerService.findById(id).get();
	}
	
	@PostMapping("/customers")
	public String addNewCustomer(@RequestBody Customer customer) {
		customerService.save(customer);
		return "Customer " + customer.getId() + " added";
	} 
	
	@PutMapping("/customers/{id}")
	public String updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
		
		Customer customerToUpdate = customerService.findById(id).get();
		
		customerToUpdate.setFirstName(customerDetails.getFirstName());
		customerToUpdate.setLastName(customerDetails.getLastName());
		
		customerService.save(customerToUpdate);
		
		return "Customer " + id + " updated";
	}
	
	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable Integer id) {
		customerService.deleteById(id);
		return "Customer " + id + " deleted";
	}
	
	
}
