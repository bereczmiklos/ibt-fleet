package com.example.fleet.repository;

import com.example.fleet.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAll();

    Client findById(int id);

    Client findByEmailAddress(String email);
}