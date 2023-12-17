package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingTradesDomainService {

    List<MatchedTradeDto> match(List<UnmatchedTradeDto> unmatchedTradeDtos, List<ProductDao> products) {

        return unmatchedTradeDtos.stream()
                .map(trade -> {
                    ProductDao first = products.stream().filter(p -> p.getId() == trade.getProductId())
                            .findFirst()
                            .orElse(null);

                    return new MatchedTradeDto(trade.getDate(), first.getName(), trade.getCurrency(), trade.getPrice());
                })
                .toList();
    }
}
