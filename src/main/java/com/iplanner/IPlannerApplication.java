package com.iplanner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iplanner.mapper")
public class IPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IPlannerApplication.class, args);
    }

}
