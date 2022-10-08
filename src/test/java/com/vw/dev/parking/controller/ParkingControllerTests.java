package com.vw.dev.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.vw.dev.parking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class ParkingControllerTests extends AbstractContainerBase{

	@LocalServerPort
	private int randomPort;
	@BeforeEach
	void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("HJ236R3");
		parkingCreateDTO.setModel("Clio");
		parkingCreateDTO.setColor("Verde");
		parkingCreateDTO.setState("RN");

		RestAssured.given().auth().basic("user","abc@vw@123").when().body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
				.post("/parking");

		RestAssured.given().auth().basic("user","abc@vw@123").when().get("/parking").then().statusCode(HttpStatus.OK.value()).body("license",
				Matchers.hasItem("HJ236R3"));
	}

	@Test
	void whenFindByIdCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM233K2");
		parkingCreateDTO.setModel("Gol");
		parkingCreateDTO.setColor("Branco");
		parkingCreateDTO.setState("BA");

		String id = RestAssured.given().when().auth().basic("user","abc@vw@123").body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
				.post("/parking").then().extract().body().jsonPath().get("id");

		RestAssured.given().when().auth().basic("user","abc@vw@123").get("/parking/{id}", id).then().statusCode(HttpStatus.OK.value()).body("id",
				Matchers.equalTo(id));
	}
	
	@Test
	void whenFindByIdCheckException() {
		String id = "123";
		RestAssured.given().when().auth().basic("user","abc@vw@123").get("/parking/{id}", id).then().statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	void whenCreateParkingThenCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("AB123C4");
		parkingCreateDTO.setModel("Corcel");
		parkingCreateDTO.setColor("Laranja");
		parkingCreateDTO.setState("PI");

		RestAssured.given().when().auth().basic("user","abc@vw@123").body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().statusCode(HttpStatus.CREATED.value()).body("license", Matchers.equalTo("AB123C4"))
				.body("color", Matchers.equalTo("Laranja"));
	}

	@Test
	void whenDeleteThenCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("ET753B1");
		parkingCreateDTO.setModel("Palio");
		parkingCreateDTO.setColor("Cinza");
		parkingCreateDTO.setState("RN");
		
		String id = RestAssured.given()
				.when().auth().basic("user","abc@vw@123").body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().extract().body().jsonPath().get("id");
		
		RestAssured.given()
			.when().auth().basic("user","abc@vw@123").delete("/parking/{id}", id)
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
				.when().auth().basic("user","abc@vw@123").body(parkingCreateDTO).contentType(MediaType.APPLICATION_JSON_VALUE).post("/parking")
				.then().extract().body().jsonPath().get("id");
		
		parkingCreateDTO.setColor("Verde Lodo");
		
		RestAssured.given()
			.when().auth().basic("user","abc@vw@123").contentType(MediaType.APPLICATION_JSON_VALUE).body(parkingCreateDTO)
			.put("/parking/{id}", id)
			.then().statusCode(HttpStatus.OK.value())
			.body("color", Matchers.equalTo("Verde Lodo"));
	}
	
	@Test
	void whenCheckOutCheckResult() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO();
		parkingCreateDTO.setLicense("WM783V3");
		parkingCreateDTO.setModel("Meriva");
		parkingCreateDTO.setColor("Prata");
		parkingCreateDTO.setState("RN");
		
		String id = RestAssured.given()
		.when().auth().basic("user","abc@vw@123").contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(parkingCreateDTO).post("/parking")
		.then().statusCode(HttpStatus.CREATED.value())
		.extract().body().jsonPath().get("id");
		
		RestAssured.given()
		.when().auth().basic("user","abc@vw@123").param("id", id).patch("/parking")
		.then().statusCode(HttpStatus.OK.value())
		.body("bill", Matchers.notNullValue())
		.and().body("exitDate", Matchers.notNullValue());
		
	}
	
	
}
