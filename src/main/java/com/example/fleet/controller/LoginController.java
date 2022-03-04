package com.example.fleet.controller;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.service.LoginService;
import com.example.fleet.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public static final String MAINPAGE = "mainpage";
    public static final String CLIENTSRENTAL = "clientsrental";
    public static final String CLIENTLOGINED = "clientlogined";

    @Autowired
    private LoginService loginService;
    @Autowired
    private RentService rentService;

    private Client clientLogined;

    @PostMapping("/login")
    public String login(@RequestParam(name = "emailaddress") String email, HttpSession session){

        clientLogined = loginService.clientLogin(email);

        if (clientLogined != null){
            session.setAttribute(CLIENTLOGINED, clientLogined);
            session.setAttribute("clientname", clientLogined.getName());

            List<Rental> clientsRentals = rentService.getAllRentsByClient(clientLogined.getClient_id());
            session.setAttribute(CLIENTSRENTAL, clientsRentals);

            log.info("client login: {id=" + clientLogined.getClient_id() +
                   ", name=" + clientLogined.getName() +
                    ", active rentals=" + clientsRentals.size() +"}");

            return MAINPAGE;
        }
        else return "invalid";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute(CLIENTLOGINED);

        log.info("client logout: {id=" + clientLogined.getClient_id() +
                ", name=" + clientLogined.getName() + "}");

        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registerpage";
    }

    @GetMapping("/register")
    public String register(@RequestParam(name = "name") String name,
                           @RequestParam(name = "email") String email,
                           HttpSession session){


        loginService.registerNewClient(name,email);
        clientLogined = loginService.clientLogin(email);
        session.setAttribute(CLIENTLOGINED, clientLogined);

        log.info("new client registered: {id=" +clientLogined.getClient_id() +
                ", name=" + clientLogined.getName() +
                ", email=" + clientLogined.getEmailAddress());

        List<Rental> clientsRentals = rentService.getAllRentsByClient(clientLogined.getClient_id());
        session.setAttribute(CLIENTSRENTAL, clientsRentals);

        log.info("client login: {id=" + clientLogined.getClient_id() +
                ", name=" + clientLogined.getName() +
                ", active rentals=" + clientsRentals.size() +"}");

        return MAINPAGE;
    }

}
