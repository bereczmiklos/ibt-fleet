package com.example.fleet.controller;

import com.example.fleet.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class RentController {

    public static final String CLIENT_ID = "clientId";
    public static final String MYRENTSPAGE = "myrentspage";

    @Autowired
    private RentService rentService;

    @GetMapping("/rent")
    public String rent(@RequestParam(name="plates")List<String> plates,
                     @RequestParam(name="start_date") Date startDate,
                     @RequestParam(name="end_date") Date endDate,
                     Model model){
        int clientId = Integer.parseInt((String) model.getAttribute(CLIENT_ID));
        rentService.newRent(clientId, startDate, endDate);
        return "filterpage";
    }

    @GetMapping("/myrents")
    public String myRents(Model model){
        return MYRENTSPAGE;
    }
}
