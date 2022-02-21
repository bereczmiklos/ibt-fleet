package com.example.fleet.controller;

import com.example.fleet.FleetApplication;
import com.example.fleet.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class RentController {

    private static final Logger log = LoggerFactory.getLogger(RentController.class);

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
        log.info("add new rent - client id: " + clientId +
                ", start date: " + startDate +
                ", end date: " + endDate);
        rentService.newRent(clientId, startDate, endDate);
        return "filterpage";
    }

    @GetMapping("/finalrent")
    public String rentFinalization(Model model)
    {
        log.info("booked cars: " + model.getAttribute("bookedcars"));
        return "finalization";
    }

    @GetMapping("/myrents")
    public String myRents(Model model){
        return MYRENTSPAGE;
    }
}
