package com.example.fleet.service;

import com.example.fleet.model.*;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    private static final Logger log = LoggerFactory.getLogger(FilterService.class);

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentedCarRepository rentedCarRepository;

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
            log.info("filter by brand: " + brandName);
            return carRepository.findByBrandName(BrandName.valueOf(brandName));
        }
        else if (category != ""){
            log.info("filter by category: " + category);
            return carRepository.findByCategory(CarCategory.valueOf(category));
        }
        else if (fuelType != ""){
            log.info("filter by fueltype: " + fuelType);
            return carRepository.findByFuel(CarFuelType.valueOf(fuelType));
        }
        else{
            log.info("filter by no parameters");
            return carRepository.findAll();
        }
    }

    /**
     * Returns: unfiltered cars
     * @return
     */
    public List<Car> getUnfilteredCars()
    {
        return carRepository.findAll();
    }

    public List<Car> availableFilter(List<Car> filtered)
    {
        List<Car> available = new ArrayList<>();

        for (Car c:filtered) {
            if (isAvailable(c))
                available.add(c);
        }

        return available;
    }

    private boolean isAvailable(Car c)
    {
        if (rentedCarRepository.findByCar(c) == null)
            return true;
        else
            return false;
    }
}
