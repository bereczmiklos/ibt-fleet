package com.example.fleet.repository;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findAll();

    Rental findById(int id);

    List<Rental> findByClient(Client client);
}