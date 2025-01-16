package com.healthcare.appointment_service;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients // Enable Feign client
public class AppointmentServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppointmentServiceApplication.class, args);
	}

}
