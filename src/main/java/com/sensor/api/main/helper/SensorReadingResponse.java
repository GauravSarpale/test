package com.sensor.api.main.helper;

import com.sensor.api.main.entity.Sensor;

public class SensorReadingResponse {

	
	private Sensor sensor;
	private Double avgTemp;
	private Double avgHumidity;
	private String errorMsg;
	
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public Double getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(Double avgTemp) {
		this.avgTemp = avgTemp;
	}
	public Double getAvgHumidity() {
		return avgHumidity;
	}
	public void setAvgHumidity(Double avgHumidity) {
		this.avgHumidity = avgHumidity;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
