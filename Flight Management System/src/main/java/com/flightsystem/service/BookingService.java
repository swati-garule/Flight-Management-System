package com.flightsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightsystem.dto.Booking;

public interface BookingService {

	ResponseEntity<?> bookFlight(Booking booking);
	String cancelBooking(String bookingId);
	List<Booking> getBookingDetails();
}
