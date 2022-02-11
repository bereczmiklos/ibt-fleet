package com.example.fleet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int client_id;
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rental> rentals;

    public Client() {
    }

    public Client(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public int getClient_id() {
        return client_id;
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
                "client_id=" + client_id +
                ", name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }
}
