package com.example.fleet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String emailAddress;

    //REVERSE NAVIGATION PROPERTY?
    @OneToMany(mappedBy = "client")
    private List<Rental> rentals = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.emailAddress = email;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
