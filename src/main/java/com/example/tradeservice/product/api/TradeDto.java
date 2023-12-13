package com.example.tradeservice.product.api;

import com.example.tradeservice.product.ProductId;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class TradeDto {
    ProductId productId;
    Instant date;
    String currency;
    BigDecimal price;
}
