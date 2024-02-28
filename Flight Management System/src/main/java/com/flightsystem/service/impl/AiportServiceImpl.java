package com.flightsystem.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightsystem.dao.AirportDao;
import com.flightsystem.dto.Airport;
import com.flightsystem.exceptions.AirportAlreadyPresentException;
import com.flightsystem.exceptions.AirportNotFoundException;
import com.flightsystem.service.AirportService;

@Service
public class AiportServiceImpl implements AirportService{

	@Autowired
	AirportDao airportDao;

	@Override
	public ResponseEntity<?> addAirport(Airport airport) {
		Optional<Airport> findById = airportDao.findById(airport.getAirportId());
		try {
			if(!findById.isPresent()) {
				airportDao.save(airport);
				return new ResponseEntity<Airport>(airport,HttpStatus.OK);
			}
			else {
				throw new AirportAlreadyPresentException("Airport Id: "+ airport.getAirportId()+" already present");
			}
		}
		catch(AirportAlreadyPresentException e) {
			return new ResponseEntity<Airport>(airport,HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public Airport updateAirport(Airport airport) {
		Optional<Airport> findById = airportDao.findById(airport.getAirportId());
		if(findById.isPresent()) {
			airportDao.save(airport);
		}
		else {
			throw new AirportNotFoundException("Airport Id: "+ airport.getAirportId()+" is not present");
		}
		return airport;
	}

	@Override
	public String deleteAirport(String airportId) {
		Optional<Airport> findById = airportDao.findById(airportId);
		if(findById.isPresent()) {
			airportDao.deleteById(airportId);
			return "Airport Removed Successfully";
		}
		else {
			throw new AirportNotFoundException("Airport Id: "+ airportId+" is not present");
		}

	}

	@Override
	public List<Airport> getAllAirports() {
		List<Airport> airportList = (List<Airport>) airportDao.findAll();
		return airportList;
	}

	@Override
	public Airport getAirportByLoc(String airportLocation) {
		Optional<Airport> findByLocation = airportDao.findById(airportLocation);
		if(findByLocation.isPresent()) {
			return findByLocation.get();
		}
		else {
			throw new AirportNotFoundException("Airport Location: "+ airportLocation+" is not present");
		}
	}

	@Override
	public Airport getAirportByName(String airportName) {
		Optional<Airport> findByName = airportDao.findById(airportName);
		if(findByName.isPresent()) {
			return findByName.get();
		}
		else {
			throw new AirportNotFoundException("Airport Location: "+ airportName+" is not present");
		}
	}

}
