package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findAll();

    Client findById(int id);

    Client findByEmailAddress(String email);
}