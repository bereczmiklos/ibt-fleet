package com.example.fleet.model;

import javax.persistence.*;

@Entity
public class RentedCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rendtedCar_id;

    //foreign key - Rental(rental_id)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id", referencedColumnName = "rental_id")
    private Rental rental;

    //foreign key - Car(car_id)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;

    public RentedCar() {
    }

    public int getRendtedCar_id() {
        return rendtedCar_id;
    }

    public Rental getRental() {
        return rental;
    }

    public Car getCar() {
        return car;
    }

    public void setRendtedCar_id(int rendtedCar_id) {
        this.rendtedCar_id = rendtedCar_id;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "RentedCar{" +
                "rendtedCar_id=" + rendtedCar_id +
                ", rental=" + rental +
                ", car=" + car +
                '}';
    }
}
