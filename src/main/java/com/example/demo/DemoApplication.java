package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        LOGGER.info("DemoApplication start:"+ System.currentTimeMillis());
        SpringApplication.run(DemoApplication.class, args);
        LOGGER.info("DemoApplication end:"+ System.currentTimeMillis());
    }
}
