package com.example.fleet.controller;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.service.LoginService;
import com.example.fleet.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Retention;
import java.util.List;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    public static final String CLIENTLOGINED = "clientlogined";

    @Autowired
    private LoginService loginService;

    @Autowired
    private RentService rentService;

    @PostMapping("/login")
    public String login(@RequestParam(name = "emailaddress") String email, HttpSession session){

        Client clienLogined = loginService.clientLogin(email);

        if (clienLogined != null){
            session.setAttribute(CLIENTLOGINED, clienLogined);
            session.setAttribute("clientname", clienLogined.getName());
            log.info("client logined: " + clienLogined.getName());

            List<Rental> clientsRentals = rentService.getAllRentsByClient(clienLogined.getClient_id());
            session.setAttribute("clientsrental", clientsRentals);

            return "mainpage";
        }
        else return "invalid";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(CLIENTLOGINED);
        log.info("client logined out");
        return "login";
    }
}
