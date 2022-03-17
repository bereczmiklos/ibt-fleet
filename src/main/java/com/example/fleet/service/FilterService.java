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
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!ObjectUtils.isEmpty(brandName)) {
            log.info("filter by brand: " + brandName);
            filterBrand = brandRepository.findByName(BrandName.valueOf(brandName));
        }
        CarCategory filterCategory = null;
        if (!ObjectUtils.isEmpty(category)) {
            log.info("filter by category: " + category);
            filterCategory = CarCategory.valueOf(category);
        }
        CarFuelType filterFuelType = null;
        if (!ObjectUtils.isEmpty(fuelType)) {
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

    public List<Car> filterCarsByOffer(String offerType){
        List<Car> cars = new ArrayList<>();
        List<Car> vans = new ArrayList<>();
        List<Car> minibus = new ArrayList<>();

        switch (offerType){
            case "S":
                cars = filterCars(null, "CAR", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(5)
                        .collect(Collectors.toList());
                break;
            case "M":
                cars = filterCars(null, "CAR", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(5)
                        .collect(Collectors.toList());
                vans = filterCars(null,"VAN",null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(1)
                        .collect(Collectors.toList());
                break;
            case "L":
                cars = filterCars(null, "CAR", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(5)
                        .collect(Collectors.toList());
                vans = filterCars(null,"VAN",null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(1)
                        .collect(Collectors.toList());
                minibus = filterCars(null,"MINIBUS", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(1)
                        .collect(Collectors.toList());
                break;
            case "XL":
                cars = filterCars(null, "CAR", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(10)
                        .collect(Collectors.toList());
                vans = filterCars(null,"VAN",null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(5)
                        .collect(Collectors.toList());
                minibus = filterCars(null,"MINIBUS", null).stream()
                        .filter(x->isAvailable(x) == true)
                        .limit(1)
                        .collect(Collectors.toList());
                break;
        }

        //Mergeing lists
        List<Car> res = Stream.of(cars,vans,minibus)
                        .flatMap(x -> x.stream())
                        .collect(Collectors.toList());
        return res;
    }

    /**
     * Returns: unfiltered cars
     * @return
     */
    public List<Car> getUnfilteredCars()
    {
        return carRepository.findAll();
    }

    /**
     * Filtering cars that aren't in rented cars
     * @param filtered: filtered car list
     * @return
     */
    public List<Car> availableFilter(List<Car> filtered)
    {
        List<Car> available = new ArrayList<>();

        for (Car c:filtered) {
            if (isAvailable(c))
                available.add(c);
        }
        log.info("Available filter: " + available);
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
