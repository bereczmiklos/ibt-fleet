package com.example.fleet.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //foreign key: Brand(id)
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private CarCategory category;
    private String type;
    private String plate;
    private CarFuelType fuel;
    private Integer price;

    public Car() {
    }

    public Car(Brand brand, CarCategory category, String type, String plate, CarFuelType fuel, Integer price) {
        this.brand = brand;
        this.category = category;
        this.type = type;
        this.plate = plate;
        this.fuel = fuel;
        this.price = price;
    }

    public Integer getId() {
        return id;
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

    public Integer getPrice() {
        return price;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + id +
                ", category=" + category +
                ", type='" + type + '\'' +
                ", plate='" + plate + '\'' +
                ", fuel=" + fuel +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj == null ? null : this.getPlate().equals(((Car)obj).getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }
}
