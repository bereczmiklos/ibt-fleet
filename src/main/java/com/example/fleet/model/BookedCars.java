package com.example.fleet.model;

import com.example.fleet.repository.CarRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BookedCars {

    private List<Car> bookedCars = new ArrayList<>();

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
        return bookedCars.contains(car);
    }
}
