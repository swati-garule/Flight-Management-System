package com.flightsystem.dao;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightsystem.dto.Schedule;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, String>{

	Optional<Schedule> findById(Date scheduleDate);
	
}
