package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Car;
import com.example.fleet.model.RentedCar;
import org.springframework.data.repository.CrudRepository;

public interface RentedCarRepository extends CrudRepository<RentedCar, Integer> {

    List<RentedCar> findAll();

    RentedCar findById(int id);

    RentedCar findByCar(Car car);
}