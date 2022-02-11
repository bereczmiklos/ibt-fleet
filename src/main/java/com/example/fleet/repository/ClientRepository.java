package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Client;
import com.example.fleet.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    Client findById(int id);
}