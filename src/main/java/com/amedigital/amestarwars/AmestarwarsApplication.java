package com.amedigital.amestarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.amedigital.amestarwars.repository.PlanetaRepository")
public class AmestarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmestarwarsApplication.class, args);
	}

}
