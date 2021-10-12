package com.sensor.api.main.exception;

public class SensorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SensorNotFoundException(int id) {
	    super("Could not find sensor with " + id);
	  }
}
