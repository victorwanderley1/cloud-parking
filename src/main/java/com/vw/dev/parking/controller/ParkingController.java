package com.vw.dev.parking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vw.dev.parking.controller.dto.ParkingDTO;
import com.vw.dev.parking.controller.mapper.ParkingMapper;
import com.vw.dev.parking.entity.Parking;
import com.vw.dev.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	public List<ParkingDTO> findAll(){
		List<Parking> parkings = parkingService.findAll();
		List<ParkingDTO> parkingDTOs = parkingMapper.toParkingDTOList(parkings);
		return parkingDTOs;
	}
}
