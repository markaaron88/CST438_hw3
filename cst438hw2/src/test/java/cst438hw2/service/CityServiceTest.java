package cst438hw2.service;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {
	
		int id = 6;
		String name = "Monterey";
		String countryCode = "MONT";
		String district = "MONTDISTR";
		int population = 8990;
		
		
		//expected CityInfo to be returned
		City attempt =
				new City(6, name, countryCode,  district, population);
									
		//stub out the City Service class
		given(weatherService.getTempAndTime(name)).willReturn(new TempAndTime(78.87,1580580699,-28800));
		
		given(cityRepository.findByName(name)).willReturn(Lists.newArrayList(attempt));
		
		given(countryRepository.findByCode(countryCode)).willReturn(new Country (countryCode,"MONT"));
	
		 CityInfo expected = cityService.getCityInfo(name);
		 
		 CityInfo expect =
					new CityInfo(6, "Monterey", "MONT", "MONT", "MONTDISTR", 8990, 78.87 , "11:39");
		 
		 assertEquals(expected,expect); 
									
	}
	
	@Test 
	public void  testCityNotFound() {
		
		given(cityRepository.findByName("empty")).willReturn(Lists.newArrayList());
		
		given(countryRepository.findByCode("empty")).willReturn(null);
		
		given(weatherService.getTempAndTime("empt")).willReturn(null);
		
		CityInfo expected = cityService.getCityInfo("empty");
		 assertNull(expected); 
		
		// TODO 
	}
	
	@Test 
	public void  testCityMultiple() {
		
		
		
		
		int id = 6;
		String name = "Monterey";
		String countryCode = "MONT";
		String district = "MONTDISTR";
		int population = 8990;
		
		
		//expected CityInfo to be returned
		List<City> cities = Lists.newArrayList(
				new City(6,name,countryCode,district,8990),
				new City(8,name,"MONT", "MONTDISTR",1344));
		
									
		//stub out the City Service class
		given(weatherService.getTempAndTime(name)).willReturn(new TempAndTime(78.87,1580580699,-28800));
		
		given(cityRepository.findByName(name)).willReturn((cities));
		
		given(countryRepository.findByCode(countryCode)).willReturn(new Country (countryCode,"USAA"));
	
		 CityInfo expected = cityService.getCityInfo(name);
		 
		 CityInfo expect =
					new CityInfo(6, "Monterey", "MONT", "USAA", "MONTDISTR", 8990, 78.87 , "11:39");
		 
		 assertEquals(expected,expect); 
		 
		 
		 
		
		
		
	}

}
