package com.mediassessment.mediassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.mediassessment")
@SpringBootApplication
public class MediAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediAssessmentApplication.class, args);
	}

}
