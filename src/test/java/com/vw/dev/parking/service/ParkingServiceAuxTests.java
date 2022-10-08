package com.vw.dev.parking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vw.dev.parking.entity.Parking;

class ParkingServiceAuxTests {
	
	@Test
	void checkOutLessThanOneHourTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusMinutes(30));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(parking.getBill(), ParkingServiceAux.ONE_HOUR_VALUE);
	}
	
	@Test
	void checkOutGreaterThanOneHourTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusHours(2));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(parking.getBill(), ParkingServiceAux.ONE_HOUR_VALUE+(2*ParkingServiceAux.ADDITIONAL_PER_HOUR_VALUE));
	}
	
	@Test
	void checkOutGreaterThanOneDayTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusDays(1));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(parking.getBill(), ParkingServiceAux.DAY_VALUE);
	}
	
	@Test
	void checkOutGreaterThanTwoDayTest(){
		Parking parking = new Parking("", "WSP-1234", "PB", "Opala", "Preto");
		parking.setEntryDate(LocalDateTime.now().minusDays(2));
		parking = ParkingServiceAux.getInstance().checkOut(parking);
		
		assertEquals(parking.getBill(), ParkingServiceAux.DAY_VALUE*2);
	} 
}
