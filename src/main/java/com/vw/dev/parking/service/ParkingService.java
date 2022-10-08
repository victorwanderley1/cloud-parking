package com.vw.dev.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vw.dev.parking.entity.Parking;
import com.vw.dev.parking.exception.ParkingNotFoundException;
import com.vw.dev.parking.repository.ParkingRepository;

@Service
public class ParkingService {
	
	ParkingRepository repository;
	
	public ParkingService(ParkingRepository repository) {
		super();
		this.repository = repository;
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll(){
		return repository.findAll(); 
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
	}
	
	@Transactional
	public Parking insert(Parking parking) {
		String id = getUUID();
		parking.setId(id);
		parking.setEntryDate(LocalDateTime.now());
		return repository.save(parking);
	}
	
	@Transactional
	public Parking update(String id, Parking parking) {
		Parking parkingOld = findById(id);
		parkingOld.setColor(parking.getColor());
		return repository.save(parkingOld);
	}
	
	@Transactional
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	@Transactional
	public Parking checkOut(String id) {
		return repository.save(ParkingServiceAux.getInstance().checkOut(findById(id)));
		
	}
}
