package com.vw.dev.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vw.dev.parking.controller.dto.ParkingCreateDTO;
import com.vw.dev.parking.controller.dto.ParkingDTO;
import com.vw.dev.parking.controller.mapper.ParkingMapper;
import com.vw.dev.parking.entity.Parking;
import com.vw.dev.parking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	@ApiOperation("Find All Parkings")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkings = parkingService.findAll();
		List<ParkingDTO> parkingDTOs = parkingMapper.toParkingDTOList(parkings);
		return ResponseEntity.ok(parkingDTOs);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find by Id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parkingService.findById(id));
		return ResponseEntity.ok(parkingDTO);
	}
	
	@PostMapping
	@ApiOperation("Insert a new Parking registry")
	public ResponseEntity<ParkingDTO> insert(@RequestBody ParkingCreateDTO parkingDTO){
		Parking parkingCreated = parkingService.insert(parkingMapper.toParking(parkingDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingMapper.toParkingDTO(parkingCreated));
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Updating a Parking registry")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO parkingDTO){
		Parking parkingCreated = parkingService.update(id, parkingMapper.toParking(parkingDTO));
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parkingCreated));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete by Id")
	public ResponseEntity delete(@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping
	@ApiOperation("Check out from parking")
	public ResponseEntity<ParkingDTO> checkOut(@RequestParam String id) {
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parkingService.checkOut(id)));
	}
}
