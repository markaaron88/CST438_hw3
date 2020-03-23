package cst438hw2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import cst438.domain.Reservation;
import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	@Autowired
    CityRepository cityRepository;
	@Autowired
    CountryRepository countryRepository;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {

		CityInfo city = cityService.getCityInfo(cityName);
		model.addAttribute("city", city);
		return "city_weather";
	} 

	@GetMapping("/cities/new")
    public String createCity( Model model) {
        City city = new City();
        model.addAttribute("city", city);
        return "city_form";
    }

    
    @PostMapping("/cities/new")
    public String processCityForm(@Valid City city, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "city_form";
        } 

        cityRepository.save(city);
        return "city_show";
    }
    
    @GetMapping("/countries/new")
    public String createCountry( Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "country_form";
    }

    
    @PostMapping("/countries/new")
    public String processCountryForm(@Valid Country country, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "country_form";
        } 

        countryRepository.save(country);
        return "country_show";
    }
	
    @PostMapping("/cities/reservation")
	public String createReservation(
			@RequestParam("city") String cityName, 
			@RequestParam("level") String level, 
			@RequestParam("email") String email, 
			Model model) {
		
		model.addAttribute("city", cityName);
		model.addAttribute("level", level);
		model.addAttribute("email", email);
		cityService.requestReservation(cityName, level, email);
		return "request_reservation";
	}

    
}


