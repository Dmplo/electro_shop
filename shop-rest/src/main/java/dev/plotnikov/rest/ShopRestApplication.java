package dev.plotnikov.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRestApplication.class, args);
    }

}
