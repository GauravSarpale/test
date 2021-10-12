package com.sensor.api.main.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Sensor_Tb")
public class Sensor {

	@Id
	private int id;
	private String city;
	private String country;
	
	
	public Sensor(int id, String city, String country) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country) && id == other.id;
	}
	@Override
	public String toString() {
		return this.getId() + " " +  this.getCountry() + " " + this.getCity(); 
	}
	
}

