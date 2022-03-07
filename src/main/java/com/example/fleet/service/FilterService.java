package com.example.fleet.service;

import com.example.fleet.model.*;
import com.example.fleet.repository.BrandRepository;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    private static final Logger log = LoggerFactory.getLogger(FilterService.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentedCarRepository rentedCarRepository;

    /**
     * Filter by only one parameter, default: findAll
     *
     * @param brandName
     * @param category
     * @param fuelType
     * @return  list of filtered cars by one parameter
     */
    public List<Car> filterCars(String brandName, String category, String fuelType){

        // collect filter args
        Brand filterBrand = null;
        if (brandName != "") {
            log.info("filter by brand: " + brandName);
            filterBrand = brandRepository.findByName(BrandName.valueOf(brandName));
        }
        CarCategory filterCategory = null;
        if (category != "") {
            log.info("filter by category: " + category);
            filterCategory = CarCategory.valueOf(category);
        }
        CarFuelType filterFuelType = null;
        if (fuelType != "") {
            log.info("filter by fueltype: " + fuelType);
            filterFuelType = CarFuelType.valueOf(fuelType);
        }

        // apply filtering (if any)
        if (filterBrand != null || filterCategory != null || filterFuelType != null) {
            // find by example - where all params filled in with non-null value match
            Example<Car> example = Example.of(new Car(filterBrand, filterCategory, null, null, filterFuelType, null));
            return carRepository.findAll(example);
        }
        else {
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
