package com.flightsystem.service;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightsystem.dto.Schedule;

public interface ScheduleService {

	ResponseEntity<?> createSchedule(Schedule schedule);
	Schedule updateSchedule(Schedule schedule);
	String deleteSchedule(String scheduleId);
	List<Schedule> getAllSchedule();
//	Schedule getScheduleByDate(Date scheduleDate);
}
