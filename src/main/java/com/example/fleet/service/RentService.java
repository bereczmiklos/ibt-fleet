package com.example.fleet.service;

import com.example.fleet.FleetApplication;
import com.example.fleet.model.Car;
import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.model.RentedCar;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentService{

    private static final Logger log = LoggerFactory.getLogger(RentService.class);

    private RentalRepository rentalRepository;
    private ClientRepository clientRepository;
    private RentedCarRepository rentedCarRepository;
    @Autowired
    private CarRepository carRepository;

    private List<Car> carsInCart = new ArrayList<>();

    public void carAddToCart(String plate){
        Car car = carRepository.findByPlate(plate);
        car.setAvaible(false);
        carsInCart.add(car);
    }

    public void carDeleteFromCart(String plate){
        Car car = carRepository.findByPlate(plate);
        car.setAvaible(true);
        carsInCart.remove(car);
    }

    public int getCountOfCarsInCart(){
        return carsInCart.size();
    }

    public List<Car> getCarsInCart()
    {
        return  carsInCart;
    }
    /**
     * In case of a new rent, create and save a new entity to the db
     * @param clientId  the id of a client to rent
     * @param start     the date of begin
     * @param end       the date of expire or resign
     */
    public void newRent(int clientId, Date start, Date end){
        Rental newRent = new Rental();
        newRent.setClient(clientRepository.findById(clientId));
        for (Car s: carsInCart) {
            newRentedCar(newRent, s);
        }
        carsInCart.clear();
        newRent.setRent_begin(start);
        newRent.setRent_end(end);
        rentalRepository.save(newRent);
    }

    private void newRentedCar(Rental rent, Car car){
        RentedCar rentedCar = new RentedCar();
        rentedCar.setRental(rent);
        rentedCar.setCar(carRepository.findByPlate(car.getPlate()));
        rentedCarRepository.save(rentedCar);
        log.info("add new rented car - car plate: " + car.getPlate());
    }

    /**
     * If a rent expire or resigned, delete the rent from the db
     * @param rentId the expired or resigned rent's id
     */
    public void deleteRent(int rentId){
        //Az összes RentedCar rekord törlése ahol a bérlésId azonos a törlendő bérlés id-val:
        deleteRentedCar(rentId);
        rentalRepository.delete(rentalRepository.findById(rentId));
    }

    private void deleteRentedCar(int rentId){
        for(RentedCar rc : rentedCarRepository.findAll()){
            if (rc.getRental().getId() == rentId){
                rentedCarRepository.delete(rc);
            }
        }
        log.info("delete rented car - rent id: " + rentId);
    }
}
