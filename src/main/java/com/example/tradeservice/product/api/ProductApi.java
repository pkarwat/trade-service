package com.example.tradeservice.product.api;

import java.util.List;

public interface ProductApi {

    List<ProductDto> match(MatchTradesCommand command);

}
