package com.example.fleet.service;

import com.example.fleet.model.*;
import com.example.fleet.repository.BrandRepository;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {
/*
    private static final Logger log = LoggerFactory.getLogger(FilterServiceTest.class);

    @Mock
    CarRepository carRepository;
    @Mock
    RentalRepository rentalRepository;
    @Mock
    BrandRepository brandRepository;
    @Mock
    RentedCarRepository rentedCarRepository;

    @InjectMocks
    FilterService filterService = new FilterService();

    private List<Car> cars = new ArrayList<>();
    private Brand ford;
    private Brand merc;
    private String brandArg = "FORD";
    private String fuelArg = "PETROL";

    @BeforeEach
    public void init(){
        ford = new Brand(new ArrayList<>(),BrandName.FORD);
        merc = new Brand(new ArrayList<>(),BrandName.MERCEDES);

        Car car1 = new Car(ford, CarCategory.CAR, "test", "AAA-123", CarFuelType.PETROL, 20000);
        car1.setId(1);
        cars.add(car1);

        Car car2 = new Car(ford, CarCategory.CAR, "test", "AAA-456", CarFuelType.DIESEL, 20000);
        car2.setId(2);
        cars.add(car2);

        Car car3 = new Car(ford, CarCategory.CAR, "test", "AAA-689", CarFuelType.DIESEL, 20000);
        car3.setId(3);
        cars.add(car3);

        Car car4 = new Car(merc, CarCategory.CAR, "test", "AAA-101", CarFuelType.PETROL, 20000);
        car4.setId(4);
        cars.add(car4);

        Car car5 = new Car(merc, CarCategory.CAR, "test", "AAA-112", CarFuelType.PETROL, 20000);
        car5.setId(5);
        cars.add(car5);

        Car car6 = new Car(merc, CarCategory.CAR, "test", "AAA-123", CarFuelType.PETROL, 20000);
        car6.setId(6);
        cars.add(car6);

        //avoid strict stubbing?
        Mockito.lenient().when(carRepository.findAll()).thenReturn(cars);
        Mockito.lenient().when(filterService.filterCars("","","")).thenReturn(cars);

    }

    @Test
    public void TestFilterCarsWithNoParam(){

        int allCar = carRepository.findAll().size();
        log.info("           cars count in carsList: " + allCar);

        List<Car> res = filterService.filterCars("","","");
        log.info("result - unflitered cars: " + res.size());

        Assert.assertEquals(allCar, res.size());
    }

    @Test
    public void TestFilterCarsWithOneParam(){
        int exp = (int)cars.stream()
                .filter(x->x.getBrand().equals(ford))
                .count();
        log.info("           cars with brand ford: " + exp);

        List<Car> res = filterService.filterCars(brandArg, "","");

        res.stream().forEach(x-> log.info(x.getPlate()));

        log.info("result - filtered cars with brand ford: " + res.size());

        Assert.assertEquals(exp, res.size());
    }

    @Test
    public void TestFilterCarsWithTwoParam(){
        int exp =
                (int)cars.stream()
                        .filter(x->x.getBrand().equals(ford) && x.getFuel().equals(CarFuelType.PETROL))
                        .count();
        log.info("           cars with brand ford and fueltype petrol: " + exp);

        List<Car> res = filterService.filterCars("","","");
        log.info("result - filtered cars with brand ford and fueltype petrol: " + res.size());

        Assert.assertEquals(1, res.size());
    }

    @Test
    public void TestFilterCarsWithInvalidParam(){
        Assert.assertThrows(java.lang.IllegalArgumentException.class, () -> filterService.filterCars(
                "merci","",""));
    }

    @Test
    public void TestOfferPackage(){

        List<Car> res = filterService.filterCarsByOffer("S");

        log.info("cars in offer:");
        res.stream().forEach(x-> log.info(x.getPlate()));

        Assert.assertEquals(5, res.size());
    }

 */
}
