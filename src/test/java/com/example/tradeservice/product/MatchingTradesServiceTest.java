package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchTradesCommandFixture;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MatchingTradesServiceTest {

    @Autowired
    private MatchingTradesService productService;

    @Test
    void match_returnsListOfAllMatchedTrades_whenPassingAllMatchingTrades() {
        // given
        MatchTradesCommand command = MatchTradesCommandFixture.sampleMatchedTrades();
        // when
        List<MatchedTradeDto> result = productService.match(command);
        // then
        assertThat(result).hasSize(10);
    }

    @Test
    void match_returnsEmptyList_whenCommandContainsEmptyList() {
        // when
        List<MatchedTradeDto> result = productService.match(new MatchTradesCommand(List.of()));
        // then
        assertThat(result).isEmpty();
    }

    @Test
    void match_returnListWithTrade_whenPassingNonMatchingTrade() {
        // given
        List<UnmatchedTradeDto> nonMatchingTrade = List.of(new UnmatchedTradeDto(11, "20221218", "EUR", BigDecimal.valueOf(10)));
        MatchTradesCommand command = new MatchTradesCommand(nonMatchingTrade);
        // when
        List<MatchedTradeDto> result = productService.match(command);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).contains(
                new MatchedTradeDto("20221218", "Missing Product Name", "EUR", BigDecimal.valueOf(10))
        );
    }
}
