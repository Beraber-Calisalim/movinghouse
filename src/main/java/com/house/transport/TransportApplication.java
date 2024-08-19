package com.house.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportApplication {
//This is application context, this application context includes IOC container
	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

}
