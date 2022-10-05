package com.vw.dev.parking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vw.dev.parking.entity.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	@GetMapping
	public List<Parking> findAll(){
		var parking = new Parking();
		parking.setColor("Preto");
		parking.setLicense("AS57I2");
		parking.setModel("GM Corsa");
		parking.setState("RN");
		
		return Arrays.asList(parking); 
	}
}
