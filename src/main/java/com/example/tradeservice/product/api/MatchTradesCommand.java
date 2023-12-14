package com.example.tradeservice.product.api;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class MatchTradesCommand {

    List<UnmatchedTradeDto> trades;

    public MatchTradesCommand(List<UnmatchedTradeDto> trades) {
        this.trades = new ArrayList<>(trades);
    }
}
