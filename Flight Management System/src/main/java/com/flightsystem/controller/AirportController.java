package com.flightsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightsystem.dto.Airport;
import com.flightsystem.exceptions.AirportAlreadyPresentException;
import com.flightsystem.exceptions.AirportNotFoundException;
import com.flightsystem.service.AirportService;

@RestController
@RequestMapping("/airport")
public class AirportController {
	
	@Autowired
	AirportService airportService;

	@PostMapping("/addAirport")
	@ExceptionHandler(AirportAlreadyPresentException.class)
	public void addAirport(@RequestBody Airport airport) {
		airportService.addAirport(airport);
	}
	
	@PutMapping("/updateAirport")
	@ExceptionHandler(AirportNotFoundException.class)
	public void updateAirport(@RequestBody Airport airport) {
		airportService.updateAirport(airport);
	}
	
	@DeleteMapping("/deleteAirport/{id}")
	@ExceptionHandler(AirportNotFoundException.class)
	public void deleteAirport(@PathVariable("id") String airportId) {
		airportService.deleteAirport(airportId);
	}
	
	@GetMapping("/getAllAirports")
	public Iterable<Airport> getAllAirports(){
		return airportService.getAllAirports();
	}
	
	@GetMapping("/getAirportByLoc/{loc}")
	@ExceptionHandler(AirportNotFoundException.class)
	public Airport getAirportByLoc(@PathVariable("loc") String airportLocation) {
		return airportService.getAirportByLoc(airportLocation);
	}
	
	@GetMapping("/getAirportByName/{name}")
	@ExceptionHandler(AirportNotFoundException.class)
	public Airport getAirportByName(@PathVariable("name") String airportName) {
		return airportService.getAirportByName(airportName);
	}
}
