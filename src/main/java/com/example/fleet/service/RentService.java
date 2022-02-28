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

import java.time.LocalDate;
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

    private BookedCars bookedCars = new BookedCars();

    /**
     * Add cars by plate numbers to BookedCars instance
     * @param plate
     */
    public void carAddToCart(String plate){
        Car car = carRepository.findByPlate(plate);
        bookedCars.addCar(car);
        log.info("car added to cart: {car id=" + car.getId() +
                ", cars in cart=" + getCountOfCarsInCart() + "}");
    }

    /**
     * Delete cars from BookedCars instance
     * @param plate
     */
    public void carDeleteFromCart(String plate){
        Car car = carRepository.findByPlate(plate);

        bookedCars.removeCar(car);

        log.info("car deleted from cart: {car id=" + car.getId() +
                ", cars in cart=" + getCarsInCart().size() + "}");
    }

    /**
     * Returns: count of booked cars
     * @return
     */
    public int getCountOfCarsInCart(){
        return bookedCars.getCountOfBookedCars();
    }

    /**
     * Returns: booked cars from BookedCars instance
     * @return
     */
    public List<Car> getCarsInCart()
    {
        return bookedCars.getBookedCars();
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
    public void newRent(int clientId, LocalDate start, LocalDate end){
        Rental newRent = new Rental();
        newRent.setClient(clientRepository.findById(clientId));

        newRent.setBegin(start);
        newRent.setEnd(end);
        newRent.setRentedCars(new ArrayList<RentedCar>());
        rentalRepository.save(newRent);

        for (Car s: bookedCars.getBookedCars()) {
            newRentedCar(newRent, s);
        }

        log.info("new rent created: {client = " + clientId +
                ", begin: " + start +
                ", end: " + end +
                ", count cars: " + newRent.getRentedCars().size() + "}");

        bookedCars.clearBookedCars();
    }

    /**
     * If a rent expire or resigned, delete the rent from the db
     * @param rentId the expired or resigned rent's id
     */
    public void deleteRent(int rentId){
        //Az összes RentedCar rekord törlése ahol a bérlésId azonos a törlendő bérlés id-val:
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
