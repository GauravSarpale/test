package com.sensor.api.main.entity;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="SensorReading_Tb")
@AllArgsConstructor
@NoArgsConstructor
public class SensorReading {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sensor", referencedColumnName = "id")
	private Sensor sensor;
	private Double temperature;
	private Double humidity;
	private Instant createdDate;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Sensor getSensor() {
		return sensor;
	}


	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}


	public Double getTemperature() {
		return temperature;
	}


	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}


	public Double getHumidity() {
		return humidity;
	}


	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}


	@Override
	public int hashCode() {
		return Objects.hash(humidity, id, sensor, temperature);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorReading other = (SensorReading) obj;
		return humidity == other.humidity && id == other.id && Objects.equals(sensor, other.sensor)
				&& temperature == other.temperature;
	}


	@Override
	public String toString() {
		return "SensorReading [id=" + id + ", sensor=" + sensor + ", temperature=" + temperature + ", humidity="
				+ humidity + "]";
	}


	public Instant getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}


	
	
}
