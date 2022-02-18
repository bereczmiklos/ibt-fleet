package com.example.fleet.controller;

import com.example.fleet.model.Car;
import com.example.fleet.service.FilterService;
import com.example.fleet.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {
    @Autowired
    FilterService filterService;
    @Autowired
    RentService rentService;

    public static final String FILTERPAGE = "filterpage";
    public static final String FILTEREDCARS = "filteredcars";

    private List<Car> unFilteredCars;
    private List<Car> filteredCars;

    @GetMapping("/filter")
    public String filterCars(@RequestParam(name = "brandfilter", required=false) String brandName,
                                    @RequestParam(name = "categoryfilter", required=false) String category,
                                    @RequestParam(name = "fuelfilter", required=false) String fuelType,
                                    @RequestParam(name = "cartocart", required=false) String plate,
                                    Model model){
        //Filtering
        if (brandName == null && category == null && fuelType == null)
        {
            unFilteredCars = filterService.UnfilteredCars();
            model.addAttribute(FILTEREDCARS, unFilteredCars);
        }
        else{
            filteredCars = filterService.filterCars(brandName, category, fuelType);
            model.addAttribute(FILTEREDCARS, filteredCars);
        }

        //Booking
        //Ha a plate nem null, kiválasztott autók száma maradjon,frissüljön a listázás (elérhetőség állapota)

        model.addAttribute("brandfilter", brandName);
        model.addAttribute("categoryfilter", category);
        model.addAttribute("fuelfilter", fuelType);

        if (plate != null){
            rentService.carAddToCart(plate);
            model.addAttribute("countofcarsincart", rentService.getCountOfCarsInCart());
        }
        return FILTERPAGE;
    }
}
