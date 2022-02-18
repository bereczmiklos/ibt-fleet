package com.example.fleet.controller;

import com.example.fleet.model.Brand;
import com.example.fleet.model.Car;
import com.example.fleet.model.CarCategory;
import com.example.fleet.model.CarFuelType;
import com.example.fleet.service.FilterService;
import com.example.fleet.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {

    @Autowired
    FilterService filterService;
    @Autowired
    RentService rentService;

    @GetMapping("/filter")
    public String filterCars(@RequestParam(name = "brandfilter", required=false) String brandName,
                                    @RequestParam(name = "categoryfilter", required=false) String category,
                                    @RequestParam(name = "fuelfilter", required=false) String fuelType,
                                    Model model){

        if (brandName == null && category == null && fuelType == null)
        {
            List<Car> unFilteredCars = filterService.UnfilteredCars();
            model.addAttribute("filteredcars",unFilteredCars);
        }
        else{
            List<Car> filteredCars = filterService.filterCars(brandName, category, fuelType);
            model.addAttribute("filteredcars",filteredCars);
        }
        return "filterpage";
    }

    @GetMapping("/addtocart")
    public String addToCart(@RequestParam(name = "cartocart") String carPlate,
                            Model model){

        //TODO: count of cars in the cart
        rentService.carAddToCart(carPlate);
        return "filterpage";
    }
}
