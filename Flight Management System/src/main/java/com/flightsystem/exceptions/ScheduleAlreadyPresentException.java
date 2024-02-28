package com.flightsystem.exceptions;

public class ScheduleAlreadyPresentException extends RuntimeException{
	
	public ScheduleAlreadyPresentException(String message){
		super(message);
	}
}
