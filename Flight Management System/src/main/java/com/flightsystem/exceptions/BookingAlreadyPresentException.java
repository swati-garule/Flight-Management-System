package com.flightsystem.exceptions;

public class BookingAlreadyPresentException extends RuntimeException{
	
	public BookingAlreadyPresentException(String message){
		super(message);
	}
}
