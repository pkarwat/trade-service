package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchedTradeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MatchedTradeConverter {

    public static List<MatchedTradeDto> convert(List<Product> productDomainList) {
        return productDomainList.stream()
                .map(MatchedTradeConverter::convertItem)
                .toList();
    }

    private static MatchedTradeDto convertItem(Product product) {
        return new MatchedTradeDto(
                null,   //TODO convert additional fields
                product.getName(),
                null,
                null
        );
    }
}
