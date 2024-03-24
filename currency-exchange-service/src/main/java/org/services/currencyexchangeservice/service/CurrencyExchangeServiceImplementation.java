package org.services.currencyexchangeservice.service;

import org.services.currencyexchangeservice.model.CurrencyExchange;
import org.services.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeServiceImplementation implements CurrencyExchangeService{
    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeServiceImplementation(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public CurrencyExchange getExchangeValue(String from, String to) {
        return currencyExchangeRepository.findByFromAndTo(from, to);
    }

}
