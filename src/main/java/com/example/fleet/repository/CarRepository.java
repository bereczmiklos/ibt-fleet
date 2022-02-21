package com.example.fleet.repository;

import com.example.fleet.model.BrandName;
import com.example.fleet.model.Car;
import com.example.fleet.model.CarCategory;
import com.example.fleet.model.CarFuelType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

    List<Car> findAll();

    Car findById(int id);

    Car findByPlate(String plate);

    List<Car> findByBrandName(BrandName brandName);

    List<Car> findByCategory(CarCategory category);

    List<Car> findByFuel(CarFuelType fuel);

}