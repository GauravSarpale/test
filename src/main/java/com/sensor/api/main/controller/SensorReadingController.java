package com.sensor.api.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.api.main.entity.SensorReading;
import com.sensor.api.main.exception.SensorNotFoundException;
import com.sensor.api.main.service.SensorReadingService;

@RestController
@RequestMapping("/sensorReading")
public class SensorReadingController {

	@Autowired
	private SensorReadingService sensorReadingService;

	@PostMapping(value = "/register")
	public SensorReading putSensorReadingData(@RequestBody SensorReading sensorReading) {
		sensorReadingService.saveSensorReading(sensorReading);
		return sensorReading;
	}

	@GetMapping("/getSensorReadingData/{id}")
	SensorReading getSensorReadingDataById(@PathVariable int id) {
		return sensorReadingService.getSensorReading(id).orElseThrow(() -> new SensorNotFoundException(id));
	}
}
