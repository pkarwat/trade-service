package com.example.tradeservice.product.api;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class MatchedTradeDto {
    String date;
    String productName;
    String currency;
    BigDecimal price;
}
