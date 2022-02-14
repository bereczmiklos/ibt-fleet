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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {

    @Autowired
    FilterService filterService;

    @GetMapping("/filter")
    public String filterCarsByBrand(@RequestParam(name = "brandfilter") Brand brand,
                                    Model model){

        List<Car> filteredByBrand = filterService.filterByBrand(brand);
        model.addAttribute("filteredbybrands",filteredByBrand);
        return null;
    }

    @GetMapping("/filter")
    public String filterCarsByCategory(@RequestParam(name = "categoryfilter") CarCategory category,
                                       Model model){

        List<Car> filterByCategory = filterService.filterByCategory(category);
        model.addAttribute("filteredbycategory",filterByCategory);
        return null;
    }

    @GetMapping("/filter")
    public String filterCarsByFuel(@RequestParam(name = "fuelfilter") CarFuelType fuelType,
                                   Model model){

        List<Car> filteredByFuel = filterService.filterByFuelType(fuelType);
        model.addAttribute("filteredbyfuel",filteredByFuel);
        return null;
    }
}
