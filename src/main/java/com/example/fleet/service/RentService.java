package com.example.fleet.service;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class RentService {
    RentalRepository rentalRepository;
    ClientRepository clientRepository;
    RentedCarRepository rentedCarRepository;
}
