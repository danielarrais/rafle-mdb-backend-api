package dev.danielarrais.raflebackendapi;

import org.springframework.boot.SpringApplication;

public class TestRafleBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(RafleBackendApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
