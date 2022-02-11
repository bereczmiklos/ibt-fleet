package com.example.fleet.controller;

import com.example.fleet.model.Customer;
import com.example.fleet.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        Customer customer = repository.findByFirstNameAndLastName("Jack",name);
        String customerName = name + " (unknown)";

        if (customer != null){
            customerName = customer.getFirstName() + " " + customer.getLastName();
        }



        model.addAttribute("name", customerName);
        return "greeting";
    }

}