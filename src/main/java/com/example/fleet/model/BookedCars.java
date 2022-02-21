package com.example.fleet.model;

import java.util.ArrayList;
import java.util.List;

public class BookedCars {
    private static List<Car> bookedCars = new ArrayList<>();

    public static List<Car> getInstance(){
        return bookedCars;
    }

    private BookedCars() {
    }
}
