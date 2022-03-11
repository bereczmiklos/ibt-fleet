package com.example.fleet.controller;

import com.example.fleet.model.Car;
import com.example.fleet.service.FilterService;
import com.example.fleet.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {

    private static final Logger log = LoggerFactory.getLogger(FilterController.class);

    public static final String FILTERPAGE = "filterpage";
    public static final String FILTEREDCARS = "filteredcars";

    @Autowired
    FilterService filterService;
    @Autowired
    RentService rentService;

    private List<Car> filteredCars;

    @GetMapping("/filter")
    public String filterCars(@RequestParam(name = "brandfilter", required=false) String brandName,
                             @RequestParam(name = "categoryfilter", required=false) String category,
                             @RequestParam(name = "fuelfilter", required=false) String fuelType,
                             @RequestParam(name = "cartocart", required=false) String plate,
                             Model model,
                             HttpSession session){

        //Filtering:
        filteredCars = filterService.filterCars(brandName, category, fuelType);
        model.addAttribute(FILTEREDCARS,filterService.availableFilter(filteredCars));

        //Booking:
        //Put back into request scope
        model.addAttribute("brandfilter", brandName);
        model.addAttribute("categoryfilter", category);
        model.addAttribute("fuelfilter", fuelType);

        if (plate != null){
            rentService.carAddToCart(plate);
            session.setAttribute("bookedcars", rentService.getCarsInCart());
            session.setAttribute("countofcarsincart", rentService.getCountOfCarsInCart());

            model.addAttribute(FILTEREDCARS,
                bookedFilter(filterService.availableFilter(filteredCars)));
        }
        return FILTERPAGE;
    }

    @GetMapping("/offer")
    public String offers(@RequestParam(name = "selectedoffer") String offerType, Model model){
        List<Car> carsInOffer = new ArrayList<>();

        switch (offerType){
            case "s":
                //carsInOffer = filterService.filterCarsByOffer(offerType));
                model.addAttribute("offer", filterService.filterCarsByOffer(offerType));
                break;
            case "m":
                model.addAttribute("offer", filterService.filterCarsByOffer(offerType));
                break;
            case "l":
                model.addAttribute("offer", filterService.filterCarsByOffer(offerType));
                break;
            case "xl":
                model.addAttribute("offer", filterService.filterCarsByOffer(offerType));
                break;
        }
        return "offerlistingpage";
    }
    private List<Car> bookedFilter(List<Car> unBookedCars){

        List<Car> bookedCarsInSession = rentService.getCarsInCart();
        List<Car> res = new ArrayList<>();

        if (bookedCarsInSession!=null){
            for (Car c : unBookedCars) {
                if (!bookedCarsInSession.contains(c)){
                    res.add(c);
                }
            }
        }
        else res = unBookedCars;

        return res;
    }
}
