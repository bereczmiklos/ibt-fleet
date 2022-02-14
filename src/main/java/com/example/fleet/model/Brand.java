package com.example.fleet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //foreign key: Car(id)
    @OneToMany(mappedBy = "brand")
    private List<Car> cars;

    private BrandName name;

    public Brand() {
    }

    public Brand(List<Car> cars, BrandName name) {
        this.cars = cars;
        this.name = name;
    }

    public int getBrand_id() {
        return id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public BrandName getName() {
        return name;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setName(BrandName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
