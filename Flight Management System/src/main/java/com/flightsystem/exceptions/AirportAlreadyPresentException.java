package com.flightsystem.exceptions;

public class AirportAlreadyPresentException extends RuntimeException{

	public AirportAlreadyPresentException(String message){
		super(message);
	}
}
