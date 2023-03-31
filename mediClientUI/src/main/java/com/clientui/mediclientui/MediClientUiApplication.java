package com.clientui.mediclientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.clientui")
@SpringBootApplication
public class MediClientUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediClientUiApplication.class, args);
    }

}
