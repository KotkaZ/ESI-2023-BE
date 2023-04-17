package com.esi.rooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class RoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomsApplication.class, args);
	}

}
