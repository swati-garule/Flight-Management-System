package com.flightsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightsystem.dao.FlightDao;
import com.flightsystem.dto.Flight;
import com.flightsystem.exceptions.FlightAlreadyPresentException;
import com.flightsystem.exceptions.FlightNotFoundException;
import com.flightsystem.service.FlightService;

@Service
public class FlightServiceIml implements FlightService{

	@Autowired
	FlightDao flightDao;

	@Override
	public ResponseEntity<?> addFlight(Flight flight) {
		Optional<Flight> findById = flightDao.findById(flight.getFlightId());
		try {
			if(!findById.isPresent()) {
				flightDao.save(flight);
				return new ResponseEntity<Flight>(flight,HttpStatus.OK);
			}
			else {
				throw new FlightAlreadyPresentException("Flight Id: "+ flight.getFlightId()+" already present");
			}
		}
		catch(FlightAlreadyPresentException e) {
			return new ResponseEntity<Flight>(flight,HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public Flight updateFlight(Flight flight) {
		Optional<Flight> findById = flightDao.findById(flight.getFlightId());
		if(findById.isPresent()) {
			flightDao.save(flight);
		}
		else {
			throw new FlightNotFoundException("Flight Id: "+ flight.getFlightId()+" is not present");
		}
		return flight;
	}

	@Override
	public String deleteFlight(String flightId) {
		Optional<Flight> findById = flightDao.findById(flightId);
		if(findById.isPresent()) {
			flightDao.deleteById(flightId);
			return "Airport Removed Successfully";
		}
		else {
			throw new FlightNotFoundException("Flight Id: "+ flightId+" is not present");
		}

	}

	@Override
	public List<Flight> getAllFlights() {
		List<Flight> flightList = (List<Flight>) flightDao.findAll();
		return flightList;
	}

	@Override
	public Flight getFlightByName(String flightName) {
		Optional<Flight> findByName = flightDao.findById(flightName);
		if(findByName.isPresent()) {
			return findByName.get();
		}
		else {
			throw new FlightNotFoundException("Flight Name: "+ flightName+" is not present");
		}
	}

}
