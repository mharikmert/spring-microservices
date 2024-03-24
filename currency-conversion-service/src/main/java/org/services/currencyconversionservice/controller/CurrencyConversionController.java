package org.services.currencyconversionservice.controller;

import org.services.currencyconversionservice.model.CurrencyConversion;
import org.services.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.logging.Logger;

@RestController
public class CurrencyConversionController {
    private final Logger logger = Logger.getLogger(CurrencyConversionController.class.getName());

    private final CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        RestClient restClient = RestClient.create();

        CurrencyConversion currencyConversion = new CurrencyConversion();
        try {
            // Construct the URL
            String url = String.format("http://localhost:8000/currency-exchange/from/%s/to/%s", from, to);

            // Perform the HTTP GET request using RestClient
            ResponseEntity<CurrencyConversion> response = restClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .toEntity(CurrencyConversion.class);

            // Extract response body
            currencyConversion = response.getBody();

            if (currencyConversion == null)
                throw new RuntimeException("CurrencyConversion is null");

            // Construct and return the CurrencyConversion object
            return new CurrencyConversion(
                    currencyConversion.getId(),
                    from,
                    to,
                    currencyConversion.getConversionMultiple(),
                    quantity,
                    quantity.multiply(currencyConversion.getConversionMultiple()),
                    currencyConversion.getEnvironment());

        } catch (NullPointerException ex) {
            logger.log(logger.getLevel(), ex.getMessage());
        }

        return currencyConversion;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}/feign")
    public CurrencyConversion getExchangeValueFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.getExchangeValue(from, to);

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " feign");
    }

}
