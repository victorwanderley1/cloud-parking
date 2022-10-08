package com.vw.dev.parking.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.vw.dev.parking.controller"))
				.build().apiInfo(getMetaData()).securityContexts(Arrays.asList(getSecurityContext()))
				.securitySchemes(Arrays.asList(basicScheme()));
	}
	
	private SecurityContext getSecurityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference())).build();
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}
	
	private SecurityScheme basicScheme() {
		return new BasicAuth("basicAuth");
	}

	private ApiInfo getMetaData() {
		return new ApiInfoBuilder()
				.title("Cloud Parking")
				.description("Spring Boot REST Api for Parking")
				.version("0.0.3-SNAPSHOT")
				.build();
	}
	
}
