package com.flightsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightsystem.dto.Airport;

@Repository
public interface AirportDao extends CrudRepository<Airport, String>{


}
