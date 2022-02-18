package com.example.fleet.controller;

import com.example.fleet.model.Brand;
import com.example.fleet.model.Car;
import com.example.fleet.model.CarCategory;
import com.example.fleet.model.CarFuelType;
import com.example.fleet.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {

    @Autowired
    FilterService filterService;

    @GetMapping("/filter")
    public String filterCars(@RequestParam(name = "brandfilter", required=false) String brandName,
                                    @RequestParam(name = "categoryfilter", required=false) CarCategory category,
                                    @RequestParam(name = "fuelfilter", required=false) CarFuelType fuelType,
                                    Model model){

        List<Car> filteredCars = filterService.filterCars(brandName, category, fuelType);
        if (filteredCars != null){
            model.addAttribute("filteredcars",filteredCars);
        }
        return "filterpage";
    }
}
