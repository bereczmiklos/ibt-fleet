package com.example.fleet.service;

import com.example.fleet.model.Brand;
import com.example.fleet.model.Car;
import com.example.fleet.model.CarCategory;
import com.example.fleet.model.CarFuelType;
import com.example.fleet.repository.BrandRepository;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {
    private CarRepository carRepository;

    /**
     * Filter by only one parameter, default: findAll
     * @param brand
     * @param category
     * @param fuelType
     * @return  list of filtered cars by one parameter
     */
    //TODO: multiple filter parameter
    public List<Car> filterCars(Brand brand, CarCategory category, CarFuelType fuelType){
        if (brand != null)
        {
            return carRepository.findByBrandName(brand.getName());
        }
        else if (category != null){
            return carRepository.findByCategory(category);
        }
        else if (fuelType != null){
            return carRepository.findByFuel(fuelType);
        }
        else return carRepository.findAll();
    }
}
