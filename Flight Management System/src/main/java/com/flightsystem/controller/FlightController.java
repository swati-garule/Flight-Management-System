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

import com.flightsystem.dto.Flight;
import com.flightsystem.exceptions.FlightAlreadyPresentException;
import com.flightsystem.exceptions.FlightNotFoundException;
import com.flightsystem.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/addFlight")
	@ExceptionHandler(FlightAlreadyPresentException.class)
	public void addFlight(@RequestBody Flight flight) {
		flightService.addFlight(flight);
	}
	
	@PutMapping("/updateFlight")
	@ExceptionHandler(FlightNotFoundException.class)
	public void updateFlight(@RequestBody Flight flight) {
		flightService.updateFlight(flight);
	}
	
	@DeleteMapping("/deleteFlight/{id}")
	@ExceptionHandler(FlightNotFoundException.class)
	public void deleteFlight(@PathVariable("id") String flightId) {
		flightService.deleteFlight(flightId);
	}
	
	@GetMapping("/getAllFlights")
	public Iterable<Flight> getAllFlights(){
		return flightService.getAllFlights();
	}
	
	@GetMapping("/getFlightByName/{name}")
	@ExceptionHandler(FlightNotFoundException.class)
	public Flight getFlightByName(@PathVariable("name") String flightName) {
		return flightService.getFlightByName(flightName);
	}

}
