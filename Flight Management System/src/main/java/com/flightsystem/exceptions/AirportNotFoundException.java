package com.flightsystem.exceptions;

public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException(String message) {
		super(message);
	}
}
