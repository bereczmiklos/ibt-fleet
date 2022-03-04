package com.example.fleet.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //foreign key - Client(id)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //REVERSE NAVIGATION PROPERTY?
    @OneToMany(mappedBy = "rental")
    private List<RentedCar> rentedCars;

    @Column(name = "begin_date")
    private LocalDate begin;

    @Column(name = "end_date")
    private LocalDate end;



    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<RentedCar> getRentedCars() {
        return rentedCars;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRentedCars(List<RentedCar> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public void setBegin(LocalDate rent_begin) {
        this.begin = rent_begin;
    }

    public void setEnd(LocalDate rent_end) {
        this.end = rent_end;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rent_id=" + id +
                ", count of rented cars= " + rentedCars.size() +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
