package com.vw.dev.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vw.dev.parking.entity.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
