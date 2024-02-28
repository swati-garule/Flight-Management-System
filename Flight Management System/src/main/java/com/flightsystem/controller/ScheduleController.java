package com.flightsystem.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightsystem.dto.Schedule;
import com.flightsystem.exceptions.ScheduleAlreadyPresentException;
import com.flightsystem.exceptions.ScheduleNotFoundException;
import com.flightsystem.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@PostMapping("/createSchedule")
	@ExceptionHandler(ScheduleAlreadyPresentException.class)
	public void createSchedule(@RequestBody Schedule schedule) {
		scheduleService.createSchedule(schedule);
	}
	
	@PutMapping("/updateSchedule")
	@ExceptionHandler(ScheduleNotFoundException.class)
	public void updateSchedule(@RequestBody Schedule schedule) {
		scheduleService.updateSchedule(schedule);
	}
	
	@DeleteMapping("/deleteSchedule/{id}")
	@ExceptionHandler(ScheduleNotFoundException.class)
	public void deleteSchedule(@PathVariable("id") String scheduleId) {
		scheduleService.deleteSchedule(scheduleId);
	}
	
	@GetMapping("/getAllSchedule")
	public Iterable<Schedule> getAllSchedule(){
		return scheduleService.getAllSchedule();
	}
	
//	@GetMapping("/getScheduleByDate/{date}")
//	@ExceptionHandler(ScheduleNotFoundException.class)
//	public Schedule getScheduleByDate(@PathVariable("date") Date scheduleDate) {
//		return scheduleService.getScheduleByDate(scheduleDate);
//	}
}
