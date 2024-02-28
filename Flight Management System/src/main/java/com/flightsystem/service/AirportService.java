package com.flightsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightsystem.dto.Airport;

public interface AirportService {
	
	ResponseEntity<?> addAirport(Airport airport);
	Airport updateAirport(Airport airport);
	String deleteAirport(String airportId);
	List<Airport> getAllAirports();
	Airport getAirportByLoc(String airportLocation);
	Airport getAirportByName(String airportName);
}
