package com.vw.dev.parking.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {

	static final PostgreSQLContainer POSTGRES_SQL_CONTAINER;

	
	static {
		POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest");
		POSTGRES_SQL_CONTAINER.start();
		System.setProperty("spring.datasource.url", POSTGRES_SQL_CONTAINER.getJdbcUrl());
		System.setProperty("spring.datasource.username", POSTGRES_SQL_CONTAINER.getUsername());
		System.setProperty("spring.datasource.password", POSTGRES_SQL_CONTAINER.getPassword());
	}
}
