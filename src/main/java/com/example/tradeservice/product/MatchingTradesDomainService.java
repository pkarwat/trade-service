package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class MatchingTradesDomainService {

    @Value("${trade-service.matching.missing-product-productname-placeholder}")
    private String missingProductName;

    List<MatchedTradeDto> match(List<UnmatchedTradeDto> unmatchedTradeDtos,
                                List<ProductDao> products) {

        return unmatchedTradeDtos.stream()
                .map(trade -> {
                    ProductDao first = products.stream().filter(p -> p.getId() == trade.getProductId())
                            .findFirst()
                            .orElse(null);

                    return new MatchedTradeDto(
                            trade.getDate(),
                            first != null ? first.getName() : missingProductName,
                            trade.getCurrency(),
                            trade.getPrice());
                })
                .toList();
    }
}
