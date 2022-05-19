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

    public static final String MAINPAGE = "mainpage.html";
    public static final String CLIENTSRENTAL = "clientsrental";
    public static final String CLIENTLOGINED = "clientlogined";
    private List<Rental> clientsRentals;

    @Autowired
    private LoginService loginService;
    @Autowired
    private RentService rentService;

    private Client clientLogined;

    @PostMapping("/login")
    public String login(@RequestParam(name = "emailaddress") String email,
                        @RequestParam(name="password") String password,
                        HttpSession session){

        clientLogined = loginService.clientLogin(email);

        if (clientLogined != null && clientLogined.getPassword().equals(password)){
            session.setAttribute(CLIENTLOGINED, clientLogined);

            clientsRentals = rentService.getAllRentsByClient(clientLogined.getClient_id());
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

        return "logout.html";
    }

    @GetMapping("/register")
    public String register(@RequestParam(name = "name") String name,
                           @RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password,
                           HttpSession session){

        session.removeAttribute(CLIENTLOGINED);

        loginService.registerNewClient(name,email,password);
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

    @GetMapping("/offers")
    public String offers(){
        return "offerspage.html";
    }

    @GetMapping("/toregiserpage")
    public String toRegisterPage(){
        return "registerpage.html";
    }

    @GetMapping("/main")
    public String backToMain(HttpSession session){
        session.setAttribute(CLIENTLOGINED, clientLogined);
        session.setAttribute(CLIENTSRENTAL, clientsRentals);
        return MAINPAGE;
    }
}
