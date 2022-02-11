package com.example.fleet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rent_id;

    //foreign key - Client(client_id)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    @OneToMany(mappedBy = "rental", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RentedCar> rentedCars;

    private Date rent_begin;
    private Date rent_end;

    public Rental() {
    }

    public int getRent_id() {
        return rent_id;
    }

    public Client getClient() {
        return client;
    }

    public List<RentedCar> getRentedCars() {
        return rentedCars;
    }

    public Date getRent_begin() {
        return rent_begin;
    }

    public Date getRent_end() {
        return rent_end;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRentedCars(List<RentedCar> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public void setRent_begin(Date rent_begin) {
        this.rent_begin = rent_begin;
    }

    public void setRent_end(Date rent_end) {
        this.rent_end = rent_end;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rent_id=" + rent_id +
                ", client=" + client +
                ", rentedCars=" + rentedCars +
                ", rent_begin=" + rent_begin +
                ", rent_end=" + rent_end +
                '}';
    }
}
