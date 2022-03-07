package com.example.fleet.service;

import com.example.fleet.model.*;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.RentalRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//TODO: asserts
@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FilterService.class);

    @Mock
    CarRepository carRepository;
    @Mock
    RentalRepository rentalRepository;
    @InjectMocks
    FilterService filterService;

    @Test
    public void TestFilterCarsWithNoParam(){
        List<Car> res = filterService.filterCars("","","");
        //Assert.assertEquals(13, res.size());
    }

    @Test
    public void TestFilterCarsWithOneParam(){
        List<Car> res = filterService.filterCars("","","");
        //Assert.assertEquals(13, res.size());
    }

    @Test
    public void TestFilterCarsWithTwoParam(){
        List<Car> res = filterService.filterCars("","","");
        //Assert.assertEquals(13, res.size());
    }

    @Test
    public void TestFilterCarsWithInvalidParam(){
        Assert.assertThrows(java.lang.IllegalArgumentException.class, () -> filterService.filterCars(
                "merci","",""));
    }
}
