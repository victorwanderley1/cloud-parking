package com.vw.dev.parking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.vw.dev.parking.entity.Parking;

class ParkingServiceAuxTests {
	
	@Test
	void checkOutLessThanOneHourTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusMinutes(30));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(ParkingServiceAux.ONE_HOUR_VALUE, parking.getBill());
	}
	
	@Test
	void checkOutGreaterThanOneHourTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusHours(2));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(ParkingServiceAux.ONE_HOUR_VALUE+(2*ParkingServiceAux.ADDITIONAL_PER_HOUR_VALUE), parking.getBill());
	}
	
	@Test
	void checkOutGreaterThanOneDayTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusDays(1));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(ParkingServiceAux.DAY_VALUE, parking.getBill());
	}
	
	@Test
	void checkOutGreaterThanTwoDayTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusDays(2));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(ParkingServiceAux.DAY_VALUE*2, parking.getBill());
	} 
}
