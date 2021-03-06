package com.example.fleet.controller;

import com.example.fleet.model.BookedCars;
import com.example.fleet.model.Car;
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

    public static final String FINALIZATION = "finalization";
    public static final String BOOKEDCARS = "bookedcars";
    public static final String MAINPAGE = "mainpage";

    @Autowired
    private RentService rentService;
    private int clientId;

    @GetMapping("/rent")
    public String rent(@RequestParam(name="start_date") String startDate,
                     @RequestParam(name="end_date") String endDate,
                     HttpSession session,
                     Model model){

        session.removeAttribute(BOOKEDCARS);
        session.removeAttribute("countofcarsincart");

        Client client = (Client)session.getAttribute("clientlogined");
        clientId = client.getClient_id();

        Date start = new SimpleDateFormat("yyyy-mm-dd").parse(startDate,new ParsePosition(1));
        Date end = new SimpleDateFormat("yyyy-mm-dd").parse(endDate, new ParsePosition(1));
        rentService.newRent(clientId, start, end);

        session.setAttribute("clientsrental", rentService.getAllRentsByClient(clientId));

        return MAINPAGE;
    }

    @GetMapping("/resignation")
    public String rentResignation(@RequestParam("resignatedrentid") int rentId,
                                  HttpSession session){

        rentService.deleteRent(rentId);
        session.setAttribute("clientsrental", rentService.getAllRentsByClient(clientId));

        return MAINPAGE;
    }

    @GetMapping("/finalrent")
    public String rentFinalization(HttpSession session, Model model)
    {
        sumPriceRefresh(session);
        return FINALIZATION;
    }

    @GetMapping("/deletecarfromcart")
    public String deleteCarFromCart(@RequestParam("platetodelete") String plate,HttpSession session)
    {
        //T??r??lj??k az adott aut??t egy linken kereszt??l a BookedCars objektumb??l
        session.removeAttribute(BOOKEDCARS);
        rentService.carDeleteFromCart(plate);
        session.setAttribute(BOOKEDCARS, rentService.getCarsInCart());
        sumPriceRefresh(session);
        return FINALIZATION;
    }

    private void sumPriceRefresh(HttpSession session)
    {
        int rentPrice = 0;
        List<Car> carsInCart = (List<Car>)session.getAttribute(BOOKEDCARS);
        if (carsInCart != null){
            for (Car c: carsInCart) {
                rentPrice += c.getPrice();
            }
        }
        session.setAttribute("rentprice", rentPrice);
    }
}
