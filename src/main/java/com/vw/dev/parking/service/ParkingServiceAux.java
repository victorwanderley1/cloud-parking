package com.vw.dev.parking.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.vw.dev.parking.entity.Parking;

@Component
public class ParkingServiceAux {
	private static ParkingServiceAux parkingServiceAux;
	private static int ONE_HOUR = 60;
	private static int TWENTY_FOUR_HOUR = 24*ONE_HOUR;
	public final static Double ONE_HOUR_VALUE = 5.00;
	public final static Double ADDITIONAL_PER_HOUR_VALUE = 2.00;
	public final static Double DAY_VALUE = 20.00;
	private Parking parking;
	
	public static ParkingServiceAux getInstance() {
		if(parkingServiceAux == null) {
			parkingServiceAux = new ParkingServiceAux();
		}
		return parkingServiceAux;
	}
	
	public Parking checkOut(Parking parking) {
		return this.setParking(parking).setTimeExit().generateBill().getParking();
	}
	
	private ParkingServiceAux generateBill() {
		parking.setBill(calcBill(Duration.between(parking.getEntryDate(), parking.getExitDate()).toMinutes()));
		return this;
	}
	
	private Double calcBill(Long minutes) {
		if(minutes <= ONE_HOUR) {
			return ONE_HOUR_VALUE;
		}else if (minutes < TWENTY_FOUR_HOUR){
			int hours = (int) (minutes/ONE_HOUR);
			return (Double) ONE_HOUR_VALUE+(ADDITIONAL_PER_HOUR_VALUE*(hours));
		}else {
			int days = (int) (minutes/TWENTY_FOUR_HOUR);
			return (Double) DAY_VALUE*days;
		}
	}
	private ParkingServiceAux setTimeExit() {
		this.parking.setExitDate(LocalDateTime.now());
		return this;
	}
	
	private ParkingServiceAux setParking(Parking parking) {
		this.parking = parking;
		return this;
	}
	
	private Parking getParking() {
		return parking;
	}
}
