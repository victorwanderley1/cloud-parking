package com.vw.dev.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vw.dev.parking.entity.Parking;
import com.vw.dev.parking.exception.ParkingNotFoundException;

@Service
public class ParkingService {
	
	//Mocked registries to implement service layer
	private static Map<String, Parking> parkingMap = new HashMap();
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); 
	}

	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if(parking == null) throw new ParkingNotFoundException(id);
		return parking;
	}

	public Parking insert(Parking parking) {
		String id = getUUID();
		parking.setId(id);
		parking.setEntryDate(LocalDateTime.now());
		parkingMap.put(id, parking);
		return parking;
	}
	
	public Parking update(String id, Parking parking) {
		findById(id);
		parkingMap.put(id, parking);
		return parking;
	}
	
	public void delete(String id) {
		findById(id);
		parkingMap.remove(id);
	}
}
