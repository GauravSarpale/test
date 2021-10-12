package com.sensor.api.main.helper;

import java.util.Date;
import java.util.List;

public class SensorReadingRequest {
	
	private List <Integer> idSensorList;
	private List <String> metricList;
	private Date fromDate;
	private Date toDate;
	
	public List<Integer> getIdSensorList() {
		return idSensorList;
	}
	public void setIdSensorList(List<Integer> idSensorList) {
		this.idSensorList = idSensorList;
	}
	public List<String> getMetricList() {
		return metricList;
	}
	public void setMetricList(List<String> metricList) {
		this.metricList = metricList;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
