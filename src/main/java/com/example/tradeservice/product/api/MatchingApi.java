package com.example.tradeservice.product.api;

import java.util.List;

public interface MatchingApi {

    List<MatchedTradeDto> match(MatchTradesCommand command);

}
