package com.example.fleet.model;

import com.example.fleet.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class BookedCars {
    private static BookedCars CART;
    private List<Car> bookedCars = new ArrayList<>();

    private BookedCars(){

    }

    public static BookedCars getInstance(){
        if (CART == null){
            CART = new BookedCars();
        }
        return CART;
    }

    public List<Car> getBookedCars() {
        return bookedCars;
    }

    public int getBookedCarsCount(){
        return bookedCars.size();
    }

    public void clearBookedCars(){
        bookedCars.clear();
    }

    public void addCar(Car car){
        bookedCars.add(car);
    }

    public void removeCar(Car car){
        int idx = 0;
        for (int i = 0; i < bookedCars.size(); i++){
            if (bookedCars.get(i) == car)
                idx = i;
        }
        bookedCars.remove(idx);
    }

    public boolean isContainsCar(Car car){
        if (bookedCars != null)
        {
            for (Car c: bookedCars) {
                if (car == c)
                    return true;
            }
        }
        return false;
    }
}
