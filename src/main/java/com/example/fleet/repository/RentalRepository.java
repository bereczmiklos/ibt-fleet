package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Customer;
import com.example.fleet.model.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Integer> {

    List<Rental> findAllRental();

    Rental findById(int id);
}