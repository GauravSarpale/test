package com.sensor.api.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.api.main.entity.Sensor;
import com.sensor.api.main.repo.SensorRepo;

@Service
public class SensorService {

	@Autowired
	private SensorRepo sensorRepo;
	
	public Sensor saveSensor(Sensor sensor) {
		return sensorRepo.save(sensor);
	}

	public Optional<Sensor> getSensor(int id) {
		return sensorRepo.findById(id);
	}
	public List<Sensor> getSensorList() {
		return sensorRepo.findAll();
	}
}
