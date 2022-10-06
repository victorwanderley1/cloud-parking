package com.vw.dev.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException {

	public ParkingNotFoundException() {
		super("Parking not found ");
	}

	public ParkingNotFoundException(String id) {
		super("Parking not found whit Id: "+id);
	}
	
}
