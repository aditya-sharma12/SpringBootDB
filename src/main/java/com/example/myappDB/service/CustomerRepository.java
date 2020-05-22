package com.example.myappDB.service;

import org.springframework.data.repository.CrudRepository;

import com.example.myappDB.entities.Customer;

//to create a connection with a database for the Customer entity
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}