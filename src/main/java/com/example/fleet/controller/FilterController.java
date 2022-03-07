package com.example.fleet.controller;

import com.example.fleet.model.Car;
import com.example.fleet.service.FilterService;
import com.example.fleet.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {

    public static final String FILTERPAGE = "filterpage";
    public static final String FILTEREDCARS = "filteredcars";

    @Autowired
    FilterService filterService;
    @Autowired
    RentService rentService;

    private List<Car> unFilteredCars;
    private List<Car> filteredCars;

    @GetMapping("/filter")
    public String filterCars(@RequestParam(name = "brandfilter", required=false) String brandName,
                             @RequestParam(name = "categoryfilter", required=false) String category,
                             @RequestParam(name = "fuelfilter", required=false) String fuelType,
                             @RequestParam(name = "cartocart", required=false) String plate,
                             Model model,
                             HttpSession session){

        //Filtering:
        if (brandName == null && category == null && fuelType == null)
        {
            unFilteredCars = filterService.getUnfilteredCars();
            model.addAttribute(FILTEREDCARS, filterService.availableFilter(unFilteredCars));
        }
        else{
            filteredCars = filterService.filterCars(brandName, category, fuelType);
            model.addAttribute(FILTEREDCARS, filterService.availableFilter(filteredCars));
            model.addAttribute("cartedcars", rentService.getCarsInCart());
        }

        //Booking:
        //vissza tesszük a request scope-ba
        model.addAttribute("brandfilter", brandName);
        model.addAttribute("categoryfilter", category);
        model.addAttribute("fuelfilter", fuelType);

        if (plate != null){
            rentService.carAddToCart(plate);
            session.setAttribute("bookedcars", rentService.getCarsInCart());
            session.setAttribute("countofcarsincart", rentService.getCountOfCarsInCart());
        }
        return FILTERPAGE;
    }
}
