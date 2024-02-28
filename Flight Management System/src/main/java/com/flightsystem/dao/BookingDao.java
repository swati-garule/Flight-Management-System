package com.flightsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightsystem.dto.Booking;

@Repository
public interface BookingDao extends CrudRepository<Booking, String>{

}
