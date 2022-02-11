package com.example.fleet.repository;

import com.example.fleet.model.BrandName;
import com.example.fleet.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByBrandName(BrandName brandName);
}