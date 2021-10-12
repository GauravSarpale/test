package com.sensor.api.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sensor.api.main.entity.Sensor;
import com.sensor.api.main.repo.SensorRepo;
import com.sensor.api.main.service.SensorService;

@SpringBootTest
class SensorGroupApplicationTests {
	
	@Autowired
	private SensorService sensorService;
	@MockBean
	private SensorRepo sensorRepo;
	@Test
	void contextLoads() {
	}
	
	@Test
	void registerSensor() {
		Sensor sensor = new Sensor(1,"Dublin","Ireland");
		when(sensorRepo.save(sensor)).thenReturn(sensor);
		assertEquals(sensor, sensorService.saveSensor(sensor));
	}
	
	@Test
	void getSensorDataById(){
		Optional<Sensor> sensor = Optional.ofNullable(new Sensor(1,"Dublin","Ireland"));
		when(sensorRepo.findById(1)).thenReturn(sensor);
		assertEquals(sensor, sensorService.getSensor(1));
	}
	
	@Test
	void getSensorReading() {
		when(sensorRepo.findAll()).thenReturn(Stream.of(new Sensor(1, "Dublin", "Ireland"),
				new Sensor(2, "Galway", "Ireland"), new Sensor(3, "Dingle", "Ireland")).collect(Collectors.toList()));
		assertEquals(3, sensorService.getSensorList().size());

	}
	
}
