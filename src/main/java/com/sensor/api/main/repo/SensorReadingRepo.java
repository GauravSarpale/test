package com.sensor.api.main.repo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sensor.api.main.entity.Sensor;
import com.sensor.api.main.entity.SensorReading;


public interface SensorReadingRepo extends JpaRepository<SensorReading, Integer> {

	List<SensorReading> findBySensor(Sensor sensor);
	
	 @Modifying
	 @Transactional
	 @Query("select sr from SensorReading sr where sr.createdDate >= :fromDate and sr.createdDate <= :toDate and sr.sensor = :sensor ")
	 List<SensorReading> findByCreatedDateBetween( @Param("sensor") Sensor sensor, @Param("fromDate") Instant fromDate, @Param("toDate") Instant toDate);
	
	 Optional<SensorReading> findTopBySensor(Sensor sensor, Sort descending);

}


