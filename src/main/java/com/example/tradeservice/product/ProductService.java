package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.ProductApi;
import com.example.tradeservice.product.api.ProductDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService implements ProductApi {

    @Override
    public List<ProductDto> match(MatchTradesCommand command) {
        throw new NotImplementedException("To be continued");
    }
}
