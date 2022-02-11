package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Customer;
import com.example.fleet.model.RentedCar;
import org.springframework.data.repository.CrudRepository;

public interface RentedCarRepository extends CrudRepository<RentedCar, Integer> {

    RentedCar findById(int id);
}