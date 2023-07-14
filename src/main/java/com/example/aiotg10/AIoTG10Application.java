package com.example.aiotg10;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.aiotg10.mapper")

public class AIoTG10Application {

    public static void main(String[] args) {
        SpringApplication.run(AIoTG10Application.class, args);
    }

}
