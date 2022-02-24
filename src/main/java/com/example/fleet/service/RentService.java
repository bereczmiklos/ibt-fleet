package com.example.fleet.service;

import com.example.fleet.model.*;
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

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RentedCarRepository rentedCarRepository;
    @Autowired
    private CarRepository carRepository;

    /**
     * Add cars by plate numbers to BookedCars instance
     * @param plate
     */
    public void carAddToCart(String plate){
        Car car = carRepository.findByPlate(plate);
        BookedCars.getInstance().add(car);
        log.info("car added to cart: " + car.getId() +
                ", cars in cart: " + getCountOfCarsInCart());
    }

    /**
     * Delete cars from BookedCars instance
     * @param plate
     */
    public void carDeleteFromCart(String plate){
        Car car = carRepository.findByPlate(plate);
        //TODO: remove(car) isnt working
        BookedCars.getInstance().remove(car);

        log.info("car deleted from cart: " + plate +
                ", cars in cart: " + getCountOfCarsInCart());
    }

    /**
     * Returns: count of booked cars
     * @return
     */
    public int getCountOfCarsInCart(){
        return BookedCars.getInstance().size();
    }

    /**
     * Returns: booked cars from BookedCars instance
     * @return
     */
    public List<Car> getCarsInCart()
    {
        return BookedCars.getInstance();
    }

    /**
     * Returns: the booked cars plate numbers
     * @return
     */
    public List<String> getBookedCarsPlates()
    {
        List<String> plates = new ArrayList<>();
        for (Car car : getCarsInCart()) {
            plates.add(car.getPlate());
        }
        return plates;
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

        newRent.setBegin(start);
        newRent.setEnd(end);
        newRent.setRentedCars(new ArrayList<RentedCar>());
        rentalRepository.save(newRent);

        for (Car s: BookedCars.getInstance()) {
            newRentedCar(newRent, s);
        }

        log.info("new rent created: {client = " + clientId +
                ", begin: " + start +
                ", end: " + end +
                ", count cars: " + newRent.getRentedCars().size() + "}");

        BookedCars.getInstance().clear();
    }

    /**
     * If a rent expire or resigned, delete the rent from the db
     * @param rentId the expired or resigned rent's id
     */
    public void deleteRent(int rentId){
        //Az összes RentedCar rekord törlése ahol a bérlésId azonos a törlendő bérlés id-val:
        log.info("------rented car table: " + rentedCarRepository.findAll().size());
        log.info("------delete by rentid: " + rentId);
        deleteRentedCar(rentId);
        rentalRepository.delete(rentalRepository.findById(rentId));

        log.info("rent deleted: {rent id: "+ rentId + "}");
    }

    /**
     * Returns: all rentals find by client id
     * @param clientId
     * @return
     */
    public List<Rental> getAllRentsByClient(int clientId)
    {
        Client client = clientRepository.findById(clientId);
        return rentalRepository.findByClient(client);
    }

    private void newRentedCar(Rental rent, Car car){
        RentedCar rentedCar = new RentedCar();
        rentedCar.setRental(rent);
        rentedCar.setCar(carRepository.findByPlate(car.getPlate()));

        rent.getRentedCars().add(rentedCar);

        rentedCarRepository.save(rentedCar);

        log.info("rented car added: {car id:" + car.getId() +
                ", rental id: " + rent.getId() + "}");
    }

    private void deleteRentedCar(int rentId){
        for(RentedCar rc : rentedCarRepository.findAll()){
            if (rc.getRental() == rentalRepository.findById(rentId)){
                rentedCarRepository.delete(rc);
            }
        }
    }
}
