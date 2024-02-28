package com.flightsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightsystem.dao.BookingDao;
import com.flightsystem.dto.Booking;
import com.flightsystem.dto.Flight;
import com.flightsystem.exceptions.BookingAlreadyPresentException;
import com.flightsystem.exceptions.BookingNotFoundException;
import com.flightsystem.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingDao bookingDao;

	@Override
	public ResponseEntity<?> bookFlight(Booking booking) {
		Optional<Booking> findById = bookingDao.findById(booking.getBookingId());
		try {
			if(!findById.isPresent()) {
				bookingDao.save(booking);
				return new ResponseEntity<Booking>(booking,HttpStatus.OK);
			}
			else {
				throw new BookingAlreadyPresentException("Booking Id: "+ booking.getBookingId()+" already present");
			}
		}
		catch(BookingAlreadyPresentException e) {
			return new ResponseEntity<Booking>(booking,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public String cancelBooking(String bookingId) {
		Optional<Booking> findById = bookingDao.findById(bookingId);
		if(findById.isPresent()) {
			bookingDao.deleteById(bookingId);
			return "Booking Cancelled Successfully";
		}
		else {
			throw new BookingNotFoundException("Booking Id: "+ bookingId+" is not present");
		}
		
	}

	@Override
	public List<Booking> getBookingDetails() {
		List<Booking> bookingList = (List<Booking>) bookingDao.findAll();
		return bookingList;
	}

}
