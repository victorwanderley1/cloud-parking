package com.vw.dev.parking.service;

import java.time.LocalDateTime;
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
		var id1 = getUUID();
		var parking = new Parking(id, "WM233K2", "RN", "Corsa Sedan", "Verde");
		var parking1 = new Parking(id1, "WY123Z4", "PB", "Uno", "Branco");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); 
	}

	public Parking findById(String id) {
		return parkingMap.get(id);
	}

	public Parking insert(Parking parking) {
		String id = getUUID();
		parking.setId(id);
		parking.setEntryDate(LocalDateTime.now());
		parkingMap.put(id, parking);
		return parking;
	}
}
