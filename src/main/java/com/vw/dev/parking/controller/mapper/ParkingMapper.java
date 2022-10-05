package com.vw.dev.parking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vw.dev.parking.controller.dto.ParkingDTO;
import com.vw.dev.parking.entity.Parking;

@Component
public class ParkingMapper {
	
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	
	public ParkingDTO toParkingDTO(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}
	public List<ParkingDTO> toParkingDTOList(List<Parking> parkings) {
		return parkings.stream().map(parking -> toParkingDTO(parking)).collect(Collectors.toList());
	}
}
