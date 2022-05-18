/*package com.example.fleet.service;

import com.example.fleet.model.*;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @Mock
    RentalRepository rentalRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    RentedCarRepository rentedCarRepository;
    @Spy
    BookedCars bookedCars;

    @InjectMocks
    RentService rentService = new RentService();

    private Car car;
    private Client client;

    @BeforeEach
    public void init(){
        Brand brand = new Brand(new ArrayList<>(),BrandName.FORD);

        car = new Car(brand, CarCategory.CAR, "test", "AAA-123", CarFuelType.PETROL, 20000);
        car.setId(1);

        //avoid strict stubbing?
        Mockito.lenient().when(carRepository.findByPlate("AAA-123")).thenReturn(car);

        client = new Client("testclient", "tc@tc.com","123");
    }

    @Test
    public void TestNewRentWithCorrectParams() {

        rentService.newRent(client.getClient_id(), LocalDate.parse("2000-01-01"),
                 LocalDate.parse("2000-02-01"));

        assertNotNull(rentalRepository.findByClient(client),"New rent added with correct " +
                "params");
    }

    @Test
    public void TestNewRentWithIncorrectParams(){
        Client testClient = new Client();

        assertThrows(java.time.format.DateTimeParseException.class,
                () -> rentService.newRent(0,
                        LocalDate.parse("2000.01.01"),LocalDate.parse("20000201")),"New rent " +
                        "added with incorrect dates");
    }

    @Test
    public void TestCarAddToCart(){
        rentService.carAddToCart(car.getPlate());
        assertNotNull(rentService.getCarsInCart(),"Car added to cart");
    }
}*/