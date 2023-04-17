package com.esi.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class BookingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsApplication.class, args);
	}

}
