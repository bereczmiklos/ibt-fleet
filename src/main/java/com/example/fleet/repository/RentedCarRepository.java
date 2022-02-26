package com.example.fleet.repository;

import com.example.fleet.model.Car;
import com.example.fleet.model.RentedCar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedCarRepository extends JpaRepository<RentedCar, Integer> {

    List<RentedCar> findAll();

    RentedCar findById(int id);

    RentedCar findByCar(Car car);
}