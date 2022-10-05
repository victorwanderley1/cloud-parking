package com.vw.dev.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vw.dev.parking.entity.Parking;

@Service
public class ParkingService {
	
	//Mocked registries to implement service layer
	private static Map<String, Parking> parkingMap = new HashMap();
	
	static {
		var id = getUUID();
		var parking = new Parking(id, "WM233K2", "RN", "Corsa Sedan", "Verde");
		parkingMap.put(id, parking);
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); 
	}
}