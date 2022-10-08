package com.vw.dev.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.vw.dev.parking.controller.dto.ParkingCreateDTO;
import com.vw.dev.parking.repository.ParkingRepository;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class ParkingControllerTests extends AbstractContainerBase{

	@LocalServerPort
	private int randomPort;
	@Autowired
	private ParkingRepository repository;
	@BeforeEach
	void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@AfterAll
	void finallyTests() {
		repository.deleteAll();
	}

	@Test
	void whenFindAllCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM233K2");
		parkingCreateDTO.setModel("Corsa");
		parkingCreateDTO.setColor("Verde");
		parkingCreateDTO.setState("RN");

		RestAssured.given().when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
				.post("/parking");

		RestAssured.given().when().get("/parking").then().statusCode(HttpStatus.OK.value()).body("license",
				Matchers.hasItem("AB123C4"));
	}

	@Test
	void whenFindByIdCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM233K2");
		parkingCreateDTO.setModel("Corsa");
		parkingCreateDTO.setColor("Verde");
		parkingCreateDTO.setState("RN");

		String id = RestAssured.given().when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
				.post("/parking").then().extract().body().jsonPath().get("id");

		RestAssured.given().get("/parking/{id}", id).then().statusCode(HttpStatus.OK.value()).body("id",
				Matchers.equalTo(id));
	}
	
	@Test
	void whenFindByIdCheckException() {
		String id = "123";
		RestAssured.given().get("/parking/{id}", id).then().statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	void whenCreateParkingThenCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("AB123C4");
		parkingCreateDTO.setModel("Corcel");
		parkingCreateDTO.setColor("Laranja");
		parkingCreateDTO.setState("PI");

		RestAssured.given().when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().statusCode(HttpStatus.CREATED.value()).body("license", Matchers.equalTo("AB123C4"))
				.body("color", Matchers.equalTo("Laranja"));
	}

	@Test
	void whenDeleteThenCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM233K2");
		parkingCreateDTO.setModel("Corsa");
		parkingCreateDTO.setColor("Verde");
		parkingCreateDTO.setState("RN");
		
		String id = RestAssured.given()
				.when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().extract().body().jsonPath().get("id");
		
		RestAssured.given()
			.when().delete("/parking/{id}", id)
			.then().statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	void whenUpdateThenCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM233K2");
		parkingCreateDTO.setModel("Corsa");
		parkingCreateDTO.setColor("Verde");
		parkingCreateDTO.setState("RN");
		
		String id = RestAssured.given()
				.when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().extract().body().jsonPath().get("id");
		
		parkingCreateDTO.setColor("Verde Lodo");
		
		RestAssured.given()
			.when().contentType(MediaType.APPLICATION_JSON_VALUE).body(parkingCreateDTO)
			.put("/parking/{id}", id)
			.then().statusCode(HttpStatus.OK.value())
			.body("color", Matchers.equalTo("Verde Lodo"));
	}
}
