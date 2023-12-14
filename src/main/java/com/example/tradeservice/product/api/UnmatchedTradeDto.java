package com.example.tradeservice.product.api;

import com.example.tradeservice.shared.ProductId;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class UnmatchedTradeDto {
    ProductId productId;
    Instant date;
    String currency;
    BigDecimal price;
}
