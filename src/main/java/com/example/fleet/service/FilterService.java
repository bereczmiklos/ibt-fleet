package com.example.fleet.service;

import com.example.fleet.FleetApplication;
import com.example.fleet.model.*;
import com.example.fleet.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    private static final Logger log = LoggerFactory.getLogger(FleetApplication.class);

    @Autowired
    private CarRepository carRepository;

    /**
     * Filter by only one parameter, default: findAll
     * @param brandName
     * @param category
     * @param fuelType
     * @return  list of filtered cars by one parameter
     */
    //TODO: multiple filter parameter
    public List<Car> filterCars(String brandName, String category, String fuelType){
        if (brandName != "")
        {
            return carRepository.findByBrandName(BrandName.valueOf(brandName));
        }
        else if (category != ""){
            return carRepository.findByCategory(CarCategory.valueOf(category));
        }
        else if (fuelType != ""){
            return carRepository.findByFuel(CarFuelType.valueOf(fuelType));
        }
        else return carRepository.findAll();
    }

    public List<Car> UnfilteredCars()
    {
        return carRepository.findAll();
    }
}
