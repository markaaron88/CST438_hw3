package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
	      CityInfo cityRest = cityService.getCityInfo(cityName);
	      if (cityRest == null) {
	         return new ResponseEntity<CityInfo>( HttpStatus.NOT_FOUND);
	      } else {
	         return new ResponseEntity<CityInfo>(cityRest, HttpStatus.OK);
	      }
	   } 

	}
	