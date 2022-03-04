package com.example.fleet.service;

import com.example.fleet.FleetApplication;
import com.example.fleet.model.Client;
import com.example.fleet.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(FleetApplication.class);
    @Autowired
    private ClientRepository clientRepository;

    public Client clientLogin(String email){
        return clientRepository.findByEmailAddress(email);
    }

    public void registerNewClient(String name, String email){

        if (clientRepository.findByEmailAddress(email) == null){
            Client newClient = new Client(name, email);
            clientRepository.save(newClient);
        }
        else throw new RuntimeException("A felhasználó már létezik!");
    }
}
