package com.example.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Retention;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String userName, Model model){
        model.addAttribute(RentController.CLIENT_ID, "1");
        //TODO:
        return "main";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute(RentController.CLIENT_ID,null);
        return "index";
    }
}
