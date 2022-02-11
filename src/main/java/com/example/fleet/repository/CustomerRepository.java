package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findByFirstNameAndLastName(String firstName, String lastName);

    Customer findBySalary(int salary);

    Customer findById(long id);
}