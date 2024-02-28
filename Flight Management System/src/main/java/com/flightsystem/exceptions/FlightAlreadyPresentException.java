package com.flightsystem.exceptions;

public class FlightAlreadyPresentException extends RuntimeException{
	
	public FlightAlreadyPresentException(String message){
		super(message);
	}
}
