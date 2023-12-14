package com.example.tradeservice.product.api;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Value
public class MatchedTradeDto {
    Instant date;
    String productName;
    Currency currency;
    BigDecimal price;
}
