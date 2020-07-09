package br.com.wep.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class CustomApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CustomApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "3333"));
		app.run(args);
	}
}
