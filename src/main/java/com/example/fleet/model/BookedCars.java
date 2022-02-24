package com.example.fleet.model;

import com.example.fleet.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class BookedCars {
    CarRepository carRepository;
    private  List<Car> bookedCars;

    public BookedCars() {
        this.bookedCars  = new ArrayList<>();
    }

    public void addCar(Car car){
        bookedCars.add(car);
    }

    public void clearBookedCars(){
        this.bookedCars.clear();
    }

    public void removeCar(Car car){
        int idx = 0;
        for (int i = 0; i < bookedCars.size(); i++){
            if (bookedCars.get(i) == car)
                idx = i;
        }
        bookedCars.remove(idx);
    }

    public int getCountOfBookedCars(){
        return this.bookedCars.size();
    }

    public List<Car> getBookedCars(){
        return this.bookedCars;
    }
}
