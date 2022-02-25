package com.example.fleet.model;

import javax.persistence.*;

@Entity
@Table(name="rented_car")
public class RentedCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    //foreign key - Rental(id)
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    //foreign key - Car(id)
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public RentedCar() {
    }

    public int getId() {
        return Id;
    }

    public Rental getRental() {
        return rental;
    }

    public Car getCar() {
        return car;
    }

    public void setId(int id) {
        this.Id = id;
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
                "rendtedCar_id=" + Id +
                ", rental_id=" + rental.getId() +
                ", car_id=" + car.getId() +
                '}';
    }
}
