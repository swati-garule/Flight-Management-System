package com.flightsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightsystem.dto.Flight;

public interface FlightService {
	
	ResponseEntity<?> addFlight(Flight flight);
	Flight updateFlight(Flight flight);
	String deleteFlight(String flightId);
	List<Flight> getAllFlights();
	Flight getFlightByName(String flightName);
}
