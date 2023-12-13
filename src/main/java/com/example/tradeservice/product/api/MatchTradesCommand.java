package com.example.tradeservice.product.api;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class MatchTradesCommand {

    List<TradeDto> trades;

    public MatchTradesCommand(List<TradeDto> trades) {
        this.trades = new ArrayList<>(trades);
    }
}
