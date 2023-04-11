package com.medinote.medinote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.medinote")
@SpringBootApplication
public class MediNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediNoteApplication.class, args);
	}

}
