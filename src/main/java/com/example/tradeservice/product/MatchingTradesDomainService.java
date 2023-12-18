package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class MatchingTradesDomainService {

    @Value("${trade-service.matching.missing-product-productname-placeholder}")
    private String missingProductName;

    List<MatchedTradeDto> match(List<UnmatchedTradeDto> unmatchedTradeDtos,
                                List<ProductDao> products) {

        return unmatchedTradeDtos.stream()
                .map(trade -> {
                    if (!TradeDateValidator.isValid(trade.getDate())) {
                        log.error("Invalid date <{}> for trade with product_id <{}>", trade.getDate(), trade.getProductId());
                        return null;
                    }

                    ProductDao productDao = getProductFor(products, trade.getProductId());

                    return new MatchedTradeDto(
                            trade.getDate(),
                            productDao != null ? productDao.getName() : missingProductName,
                            trade.getCurrency(),
                            trade.getPrice());
                })
                .filter(correctDateTrade -> correctDateTrade != null)
                .toList();
    }

    private ProductDao getProductFor(List<ProductDao> products, int productId) {
        return products.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElseGet(() -> {
                    log.error("Cannot found product for productId: <{}>", productId);
                    return null;
                });
    }
}
