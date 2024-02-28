package com.flightsystem.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightsystem.dao.ScheduleDao;
import com.flightsystem.dto.Schedule;
import com.flightsystem.exceptions.ScheduleAlreadyPresentException;
import com.flightsystem.exceptions.ScheduleNotFoundException;
import com.flightsystem.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public ResponseEntity<?> createSchedule(Schedule schedule) {
		Optional<Schedule> findById = scheduleDao.findById(schedule.getScheduleId());
		try {
			if(!findById.isPresent()) {
				scheduleDao.save(schedule);
				return new ResponseEntity<Schedule>(schedule,HttpStatus.OK);
			}
			else {
				throw new ScheduleAlreadyPresentException("Schedule Id: "+ schedule.getScheduleId()+" already present");
			}
		}
		catch(ScheduleAlreadyPresentException e) {
			return new ResponseEntity<Schedule>(schedule,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) {
		Optional<Schedule> findById = scheduleDao.findById(schedule.getScheduleId());
		if(findById.isPresent()) {
			scheduleDao.save(schedule);
		}
		else {
			throw new ScheduleNotFoundException("Schedule Id: "+ schedule.getScheduleId()+" is not present");
		}
		return schedule;
	}

	@Override
	public String deleteSchedule(String scheduleId) {
		Optional<Schedule> findById = scheduleDao.findById(scheduleId);
		if(findById.isPresent()) {
			scheduleDao.deleteById(scheduleId);
			return "Schedule deleted Successfully";
		}
		else {
			throw new ScheduleNotFoundException("Airport Id: "+ scheduleId+" is not present");
		}
		
	}

	@Override
	public List<Schedule> getAllSchedule() {
		List<Schedule> scheduleList = (List<Schedule>) scheduleDao.findAll();
		return scheduleList;
	}

//	@Override
//	public Schedule getScheduleByDate(Date scheduleDate) {
//		Optional<Schedule> findByDate = scheduleDao.findById(scheduleDate);
//		if(findByDate.isPresent()) {
//			return findByDate.get();
//		}
//		else {
//			throw new ScheduleNotFoundException("Schedule with : "+ scheduleDate+" is not present");
//		}
//	}

}
