package com.example.fleet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int brand_id;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Car> cars;

    private BrandName name;

    public Brand(List<Car> cars, String ford) {
    }

    public Brand(List<Car> cars, BrandName name) {
        this.cars = cars;
        this.name = name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public BrandName getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand_id=" + brand_id +
                ", cars=" + cars +
                ", name=" + name +
                '}';
    }
}
