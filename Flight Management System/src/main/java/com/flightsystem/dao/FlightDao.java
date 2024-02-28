package com.flightsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightsystem.dto.Flight;

@Repository
public interface FlightDao extends CrudRepository<Flight, String>{

}
