package com.example.fleet.service;

import com.example.fleet.model.Car;
import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    RentalRepository rentalRepository;
    @Mock
    RentedCarRepository rentedCarRepository;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    RentService rentService = new RentService();

    private static Client testClient;
    private static Car testCar;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void setup(){
        testClient = new Client("testclient", "testclient@tc.com");

        testCar = new Car();
        testCar.setPlate("AAA-123");
    }

    @Test
    public void TestNewRentWithCorrectParams() {
        rentService.newRent(testClient.getClient_id(), LocalDate.parse("2000-01-01"), LocalDate.parse(
                "2000-02-01"));

        List<Rental> r = rentalRepository.findByClient(testClient);
        assertNotNull(r);
    }

    @Test
    public void TestNewRentWithIncorrectParams(){
    }

    @Test
    public void deleteRent() {

    }

    @Test
    public void TestCarAddToCart(){

    }

}