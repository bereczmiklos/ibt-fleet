package com.example.fleet.service;

import com.example.fleet.model.Car;
import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.model.RentedCar;
import com.example.fleet.repository.CarRepository;
import com.example.fleet.repository.ClientRepository;
import com.example.fleet.repository.RentalRepository;
import com.example.fleet.repository.RentedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentService{

    private RentalRepository rentalRepository;
    private ClientRepository clientRepository;
    private RentedCarRepository rentedCarRepository;
    private CarRepository carRepository;

    /**
     * In case of a new rent, create and save a new entity to the db
     * @param clientId  the id of a client to rent
     * @param plates    the plate numbers (as a key) of the cars to rent
     * @param start     the date of begin
     * @param end       the date of expire or resign
     */
    public void newRent(int clientId, List<String> plates, Date start, Date end){
        Rental newRent = new Rental();
        newRent.setClient(clientRepository.findById(clientId));
        for (String s: plates) {
            newRentedCar(newRent, s);
        }
        newRent.setRent_begin(start);
        newRent.setRent_end(end);
        rentalRepository.save(newRent);
    }

    private void newRentedCar(Rental rent, String plate){
        RentedCar rentedCar = new RentedCar();
        rentedCar.setRental(rent);
        rentedCar.setCar(carRepository.findByPlate(plate));
        rentedCarRepository.save(rentedCar);
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
    }
}
