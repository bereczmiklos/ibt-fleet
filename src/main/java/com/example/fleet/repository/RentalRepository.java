package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Integer> {

    List<Rental> findAll();

    Rental findById(int id);

    List<Rental> findByClient(Client client);
}