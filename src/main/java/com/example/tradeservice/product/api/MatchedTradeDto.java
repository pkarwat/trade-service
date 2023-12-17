package com.example.tradeservice.product.api;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class MatchedTradeDto {
    String date;    //TODO LocalDate / Date
    String productName;
    String currency;
    BigDecimal price;
}
