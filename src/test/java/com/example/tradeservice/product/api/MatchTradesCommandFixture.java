package com.example.tradeservice.product.api;

import java.math.BigDecimal;
import java.util.List;

public class MatchTradesCommandFixture {

    public static MatchTradesCommand sampleMatchedTrades() {
        return new MatchTradesCommand(List.of(
                new UnmatchedTradeDto(1, "20221218", "EUR", BigDecimal.valueOf(10)),
                new UnmatchedTradeDto(2, "20221218", "EUR", BigDecimal.valueOf(20)),
                new UnmatchedTradeDto(3, "20221218", "EUR", BigDecimal.valueOf(30)),
                new UnmatchedTradeDto(4, "20221218", "EUR", BigDecimal.valueOf(40)),
                new UnmatchedTradeDto(5, "20221218", "EUR", BigDecimal.valueOf(50)),
                new UnmatchedTradeDto(6, "20221218", "EUR", BigDecimal.valueOf(60)),
                new UnmatchedTradeDto(7, "20221218", "EUR", BigDecimal.valueOf(70)),
                new UnmatchedTradeDto(8, "20221218", "EUR", BigDecimal.valueOf(80)),
                new UnmatchedTradeDto(9, "20221218", "EUR", BigDecimal.valueOf(90)),
                new UnmatchedTradeDto(10, "20221218", "EUR", BigDecimal.valueOf(100))
        ));
    }
}
