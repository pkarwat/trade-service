package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchedTradeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MatchingServiceTest {

    @Autowired
    private MatchingService productService;

    @Test
    void match_returnListOfProducts_whenParamIsAny() {
        // when
        List<MatchedTradeDto> result = productService.match(new MatchTradesCommand(List.of()));
        // then
        assertThat(result).hasSize(10);
    }
}
