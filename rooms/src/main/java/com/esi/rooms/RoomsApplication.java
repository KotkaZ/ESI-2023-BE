package com.esi.rooms;

import com.esi.rooms.model.Room;
import com.esi.rooms.repository.RoomsRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class RoomsApplication {

	private final RoomsRepository roomsRepository;

	public static void main(String[] args) {
		SpringApplication.run(RoomsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			val room1 = Room
				.builder()
				.roomNumber(900)
				.price(BigDecimal.valueOf(125.00))
				.description("King Suite")
				.guestsMaxNumber(2)
				.build();

			val room2 = Room
				.builder()
				.roomNumber(10)
				.price(BigDecimal.valueOf(19.99))
				.description("Basement room")
				.guestsMaxNumber(2)
				.build();

			roomsRepository.saveAndFlush(room1);
			roomsRepository.saveAndFlush(room2);
		};
	}

}
