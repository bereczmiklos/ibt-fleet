package com.example.fleet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    //REVERSE NAVIGATION PROPERTY?
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Rental> rentals;

    public Client() {
    }

    public Client(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public int getClient_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + id +
                ", name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }
}
