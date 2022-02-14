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

    public List<Car> filterByBrand(Brand brand){
        if (brand != null){
            return carRepository.findByBrandName(brand.getName());
        }
        else return new ArrayList<Car>();
    }

    public List<Car> filterByCategory(CarCategory category){
        if (category != null){
            return carRepository.findByCategory(category);
        }
        else return new ArrayList<Car>();
    }

    public List<Car> filterByFuelType(CarFuelType fuel){
        if (fuel != null){
            return  carRepository.findByFuel(fuel);
        }
        else return new ArrayList<Car>();
    }
}
