package com.example.fleet.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int car_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private CarCategory category;
    private String type;
    private String plate;
    private CarFuelType fuel;
    private int price;

    public Car() {
    }

    public Car(Brand brand, CarCategory category, String type, String plate, CarFuelType fuel, int price) {
        this.brand = brand;
        this.category = category;
        this.type = type;
        this.plate = plate;
        this.fuel = fuel;
        this.price = price;
    }

    public int getCar_id() {
        return car_id;
    }

    public Brand getBrand() {
        return brand;
    }

    public CarCategory getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getPlate() {
        return plate;
    }

    public CarFuelType getFuel() {
        return fuel;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", brand=" + brand +
                ", category=" + category +
                ", type='" + type + '\'' +
                ", plate='" + plate + '\'' +
                ", fuel=" + fuel +
                ", price=" + price +
                '}';
    }
}
