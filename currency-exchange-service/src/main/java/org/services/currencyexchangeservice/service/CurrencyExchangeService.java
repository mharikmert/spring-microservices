package org.services.currencyexchangeservice.service;

import org.services.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyExchangeService {
    CurrencyExchange getExchangeValue(String from, String to);
}
