package br.com.wep.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@SpringBootApplication
@CrossOrigin
public class CustomApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CustomApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "3333"));
		app.run(args);
	}
}
