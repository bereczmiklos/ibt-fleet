package com.example.fleet.controller;

import com.example.fleet.model.Client;
import com.example.fleet.model.Rental;
import com.example.fleet.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RentController {

    private static final Logger log = LoggerFactory.getLogger(RentController.class);

    public static final String FINALIZATION = "finalization";
    public static final String CLIENT_ID = "clientId";
    public static final String MYRENTSPAGE = "myrentspage";

    @Autowired
    private RentService rentService;

    @GetMapping("/rent")
    public String rent(@RequestParam(name="start_date") String startDate,
                     @RequestParam(name="end_date") String endDate,
                     HttpSession session,
                     Model model){

        session.removeAttribute("bookedcars");
        session.removeAttribute("countofcarsincart");

        Client client = (Client)session.getAttribute("clientlogined");
        int clientId = client.getClient_id();

        log.info("add new rent - client id: " + clientId +
                ", start date: " + startDate +
                ", end date: " + endDate);

        Date start = new SimpleDateFormat("dd/MM/yyyy").parse(startDate,new ParsePosition(1));
        Date end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate, new ParsePosition(1));
        rentService.newRent(clientId, start, end);

        List<Rental> clientsRentals = rentService.getAllRentsByClient(clientId);
        session.setAttribute("clientsrental", clientsRentals);
        log.info("client rentals in session scope: " + clientsRentals.size());
        return "mainpage";
    }

    @GetMapping("/finalrent")
    public String rentFinalization(HttpSession session, Model model)
    {
        log.info("booked cars count in finalization html: " + session.getAttribute("countofcarsincart"));
        log.info("booked cars in finalization html: " + session.getAttribute("bookedcars"));

        return "finalization";
    }

    @GetMapping("/myrents")
    public String myRents(Model model, HttpSession session){
        //Ha egy rendelés véglegesítve van, listázzuk ki a főoldalon

        Client client = (Client)session.getAttribute("clientlogined");
        int clientId = client.getClient_id();

        session.setAttribute("clientsrental", rentService.getAllRentsByClient(clientId));
        return MYRENTSPAGE;
    }

    @GetMapping("/deletecarfromcart")
    public String deleteCarFromCart()
    {
        //Töröljük az adott autót egy linken keresztül a BookedCars objektumból
        return FINALIZATION;
    }
}
