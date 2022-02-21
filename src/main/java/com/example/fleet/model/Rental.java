package com.example.fleet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //foreign key - Client(id)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    //REVERSE NAVIGATION PROPERTY?
    @OneToMany(mappedBy = "rental")
    private List<RentedCar> rentedCars;

    private Date begin;
    private Date end;

    public Rental() {
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<RentedCar> getRentedCars() {
        return rentedCars;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRentedCars(List<RentedCar> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public void setRent_begin(Date rent_begin) {
        this.begin = rent_begin;
    }

    public void setRent_end(Date rent_end) {
        this.end = rent_end;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rent_id=" + id +
                ", rent_begin=" + begin +
                ", rent_end=" + end +
                '}';
    }
}
