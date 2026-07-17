package com.tijanidevit.phylx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class PhylxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhylxApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";
	}
}
