package com.sensor.api.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.api.main.entity.Sensor;
import com.sensor.api.main.entity.SensorReading;
import com.sensor.api.main.exception.SensorNotFoundException;
import com.sensor.api.main.helper.SensorReadingRequest;
import com.sensor.api.main.helper.SensorReadingResponse;
import com.sensor.api.main.service.SensorReadingService;
import com.sensor.api.main.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;
	@Autowired
	private SensorReadingService sensorReadingService;

	// Register the Sensor
	@PostMapping(value = "/register")
	public Sensor registerSensor(@RequestBody Sensor sensor) {
		sensorService.saveSensor(sensor);
		return sensor;
	}

	// Get the Data for Sensor by Id
	@GetMapping("/sensorData/{id}")
	public Sensor getSensorDataById(@PathVariable int id) {
		return sensorService.getSensor(id).orElseThrow(() -> new SensorNotFoundException(id));
	}

	// Get the SensorReading data for Sensor
	@PostMapping("/sensorReading")
	public List<SensorReadingResponse> getSensorReading(@RequestBody SensorReadingRequest sensorReadingRequest) {
		List<SensorReadingResponse> sensorReadingResponseList = new ArrayList<SensorReadingResponse>();
		List<String> metricList = sensorReadingRequest.getMetricList();
		Date toDate = sensorReadingRequest.getToDate();
		Date fromDate = sensorReadingRequest.getFromDate();
		if (sensorReadingRequest.getIdSensorList() == null || sensorReadingRequest.getIdSensorList().size() == 0) {
			List<Sensor> sensorList = sensorService.getSensorList();
			if (sensorList.size() > 0) {
				sensorList.forEach(sensor -> sensorReadingResponseList.add(calculateData(sensor, metricList, fromDate, toDate)));
			} else {
				SensorReadingResponse sensorReadingResponse = new SensorReadingResponse();
				sensorReadingResponse.setErrorMsg("No sensor data found in the Database.");
				sensorReadingResponseList.add(sensorReadingResponse);
			}
		} else {
			sensorReadingRequest.getIdSensorList()
			.forEach(id -> sensorReadingResponseList.add(calculateData(id, metricList, fromDate, toDate)));
		}

		return sensorReadingResponseList;
	}

	// Calculate data function for Sensor
	private SensorReadingResponse calculateData(Sensor sensor, List<String> metricList, Date fromDate, Date toDate) {
		if (fromDate != null && toDate != null) {
			return getBetweenDatesData(sensor, metricList, fromDate, toDate);
		} else {
			return getLatestData(sensor);
		}
	}

	// Calculate data function input Sensor id
	private SensorReadingResponse calculateData(Integer id, List<String> metricList, Date fromDate, Date toDate) {
		try {
			Sensor sensor = sensorService.getSensor(id).orElseThrow(() -> new SensorNotFoundException(id));
			if (fromDate != null && toDate != null) {
				return getBetweenDatesData(sensor, metricList, fromDate, toDate);
			} else {
				return getLatestData(sensor);
			}
		} catch (SensorNotFoundException e) {
			SensorReadingResponse sensorReadingResponse = new SensorReadingResponse();
			sensorReadingResponse
					.setErrorMsg("Sensor with ID:" + id + " Not Found in db. Please register the sensor first.");
			return sensorReadingResponse;
		}
	}

	// Function to get latest data for a Sensor
	private SensorReadingResponse getLatestData(Sensor sensor) {
		SensorReadingResponse sensorReadingResponse = new SensorReadingResponse();
		try {
			sensorReadingResponse.setSensor(sensor);
			SensorReading sensorReading = sensorReadingService.getLatestSensorReading(sensor)
					.orElseThrow(() -> new SensorNotFoundException(sensor.getId()));
			sensorReadingResponse.setAvgHumidity(sensorReading.getHumidity());
			sensorReadingResponse.setAvgTemp(sensorReading.getTemperature());
			return sensorReadingResponse;
		} catch (SensorNotFoundException e) {
			sensorReadingResponse.setErrorMsg("No Sensor readings found for Sensor with ID:" + sensor.getId() + ".");
			return sensorReadingResponse;
		}
	}

	// Function to get Calculated data fields for a Sensor between the from date and
	// to date
	private SensorReadingResponse getBetweenDatesData(Sensor sensor, List<String> metricList, Date fromDate,
			Date toDate) {
		SensorReadingResponse sensorReadingResponse = new SensorReadingResponse();
		sensorReadingResponse.setSensor(sensor);
		List<SensorReading> sensorReadingList = sensorReadingService.findByCreatedDateBetween(sensor, fromDate, toDate);
		if (!sensorReadingList.isEmpty()) {
			System.out.println("sensorReadingList size = " + sensorReadingList.size());
			if (metricList.contains("temperature") || metricList.contains("Temperature")) {
				Double avgTemp = (Double) sensorReadingList.parallelStream().map(sr -> sr.getTemperature())
						.collect(Collectors.toList()).parallelStream().mapToDouble(val -> val).average().orElse(0.0);
				sensorReadingResponse.setAvgTemp(avgTemp);

			}
			if (metricList.contains("humidity") || metricList.contains("Humidity")) {
				Double avgHumidity = (Double) sensorReadingList.parallelStream().map(sr -> sr.getHumidity())
						.collect(Collectors.toList()).parallelStream().mapToDouble(val -> val).average().orElse(0.0);
				sensorReadingResponse.setAvgHumidity(avgHumidity);
			}
		} else {
			sensorReadingResponse.setErrorMsg("No Sensor readings found for Sensor with ID:" + sensor.getId() + ".");
		}
		return sensorReadingResponse;
	}

}
