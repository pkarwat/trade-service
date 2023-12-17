package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MatchingTradesDomainServiceTest {

    @Autowired
    private MatchingTradesDomainService service;

    @Test
    void match_returnMatchedCollectionByProductId_forNonEmptyCollections() {
        // given
        List<UnmatchedTradeDto> unmatchedTradeDtos = List.of(
                new UnmatchedTradeDto(1, "20230110", "EUR", BigDecimal.ONE),
                new UnmatchedTradeDto(2, "20230210", "USD", BigDecimal.valueOf(2)));
        List<ProductDao> productDaoList = List.of(
                new ProductDao(1, "A"),
                new ProductDao(2, "B"));
        // when
        List<MatchedTradeDto> result = service.match(unmatchedTradeDtos, productDaoList);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(new MatchedTradeDto("20230110", "A", "EUR", BigDecimal.ONE));
        assertThat(result.get(1)).isEqualTo(new MatchedTradeDto("20230210", "B", "USD", BigDecimal.valueOf(2)));
    }
}
