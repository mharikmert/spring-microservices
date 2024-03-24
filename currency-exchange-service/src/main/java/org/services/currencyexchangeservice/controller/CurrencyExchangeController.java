package org.services.currencyexchangeservice.controller;

import org.services.currencyexchangeservice.model.CurrencyExchange;
import org.services.currencyexchangeservice.service.CurrencyExchangeService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeService currencyExchangeService) {
        this.environment = environment;
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> getExchangeValue(@PathVariable String from, @PathVariable String to) {
        Optional<CurrencyExchange> currencyExchange = Optional.ofNullable(currencyExchangeService.getExchangeValue(from, to));
        if (currencyExchange.isEmpty())
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        currencyExchange.get().setEnvironment(environment.getProperty("local.server.port") + " " + environment.getProperty("eureka.instance.instanceId"));
        return ResponseEntity.ok(currencyExchange.get());
    }
}
