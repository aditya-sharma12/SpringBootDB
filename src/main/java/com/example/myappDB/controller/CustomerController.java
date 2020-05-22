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
import com.example.myappDB.exception.ApiRequestException;
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
		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent()) {
            return customer.get();
        } else {
        	throw new ApiRequestException("No customer found with id " + id);
        }
	}
	
	@PostMapping("/customers")
	public String addNewCustomer(@RequestBody Customer customer) {
		customerService.save(customer);
		return "New Customer with id " + customer.getId() + " is added";
	} 
	
	@PutMapping("/customers/{id}")
	public String updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
				
		Optional<Customer> customerToUpdate = customerService.findById(id);
		if (!customerToUpdate.isPresent()) {
			customerService.save(customerDetails);
			return "New Customer with id " + customerDetails.getId() + " is added";
//			throw new ApiRequestException("No customer found with id " + id);
        } 
		
		customerToUpdate.get().setFirstName(customerDetails.getFirstName());
		customerToUpdate.get().setLastName(customerDetails.getLastName());
		
		customerService.save(customerToUpdate.get());
		return "Customer with id " + id + " is updated";
	}
	
	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable Integer id) {
		Optional<Customer> customerToDelete = customerService.findById(id);
		if (!customerToDelete.isPresent()) {
			throw new ApiRequestException("No customer found with id " + id);
        } 
		customerService.deleteById(id);
		return "Customer with id " + id + " is deleted";
	}
	
	
}
