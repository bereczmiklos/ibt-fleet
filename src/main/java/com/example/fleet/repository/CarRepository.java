package com.example.fleet.repository;

import com.example.fleet.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAll();

    Car findById(int id);

    Car findByPlate(String plate);
}