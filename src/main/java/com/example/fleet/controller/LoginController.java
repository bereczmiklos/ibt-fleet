package com.example.fleet.controller;

import com.example.fleet.model.Client;
import com.example.fleet.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Retention;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam(name = "emailaddress") String email, HttpSession session){

        Client clienLogined = loginService.clientLogin(email);
        if (clienLogined != null){
            session.setAttribute("clientlogined", clienLogined);
            session.setAttribute("clientname", clienLogined.getName());
            return "mainpage";
        }
        else return "invalid";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute(RentController.CLIENT_ID,null);
        return "index";
    }
}
