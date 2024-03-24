package org.services.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CurrencyExchangeService {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeService.class, args);
    }

}
