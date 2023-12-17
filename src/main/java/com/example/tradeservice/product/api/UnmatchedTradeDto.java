package com.example.tradeservice.product.api;

import com.example.tradeservice.shared.ProductId;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UnmatchedTradeDto {
    ProductId productId;
    String date;
    String currency;
    BigDecimal price;
}
