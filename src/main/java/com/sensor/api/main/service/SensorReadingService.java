package com.sensor.api.main.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sensor.api.main.entity.Sensor;
import com.sensor.api.main.entity.SensorReading;
import com.sensor.api.main.exception.SensorNotFoundException;
import com.sensor.api.main.repo.SensorReadingRepo;
@Service
public class SensorReadingService {
	@Autowired
	private SensorReadingRepo sensorReadingRepo;
	@Autowired
	private SensorService sensorService;
	
	public SensorReading saveSensorReading(SensorReading sensorReading) {
		if(sensorReading.getSensor()!= null) {
			int id =sensorReading.getSensor().getId(); 
			sensorReading.setSensor(sensorService.getSensor(id).orElseThrow(() -> new SensorNotFoundException(id)));
		}
		sensorReading.setCreatedDate(java.time.Instant.now());
		return sensorReadingRepo.save(sensorReading);
	}

	public Optional<SensorReading> getSensorReading(int id) {
		return sensorReadingRepo.findById(id);
	}

	public List <SensorReading> getSensorReading(Sensor sensor) {
		return sensorReadingRepo.findBySensor(sensor);
	}
	
	public List <SensorReading> findByCreatedDateBetween(Sensor sensor, Date fromDate, Date toDate) {
		return sensorReadingRepo.findByCreatedDateBetween(sensor, fromDate.toInstant(), toDate.toInstant());
	}
	
	public Optional<SensorReading> getLatestSensorReading(Sensor sensor) {
		 		return sensorReadingRepo.findTopBySensor(sensor,Sort.by(Direction.DESC, "id"));
	}
	
}
