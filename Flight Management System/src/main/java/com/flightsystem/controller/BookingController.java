package com.flightsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightsystem.dto.Booking;
import com.flightsystem.exceptions.BookingAlreadyPresentException;
import com.flightsystem.exceptions.BookingNotFoundException;
import com.flightsystem.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookFlight")
	@ExceptionHandler(BookingAlreadyPresentException.class)
	public void bookFlight(@RequestBody Booking booking) {
		bookingService.bookFlight(booking);
	}
	
	@DeleteMapping("/cancelBooking/{id}")
	@ExceptionHandler(BookingNotFoundException.class)
	public void cancelBooking(@PathVariable("id") String bookingId) {
		bookingService.cancelBooking(bookingId);
	}
	
	@GetMapping("/getBookingDetails")
	public Iterable<Booking> getBookingDetails(){
		return bookingService.getBookingDetails();
	}
	
}
