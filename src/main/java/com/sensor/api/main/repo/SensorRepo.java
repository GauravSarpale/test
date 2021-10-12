package com.sensor.api.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensor.api.main.entity.Sensor;

public interface SensorRepo extends JpaRepository<Sensor, Integer>{

}
