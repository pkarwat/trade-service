package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchTradesCommandFixture;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        long nonMatchingId = 11;
        List<UnmatchedTradeDto> nonMatchingTrade = List.of(new UnmatchedTradeDto(nonMatchingId, "20221218", "EUR", BigDecimal.valueOf(10)));
        MatchTradesCommand command = new MatchTradesCommand(nonMatchingTrade);
        // when
        List<MatchedTradeDto> result = productService.match(command);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).contains(
                new MatchedTradeDto("20221218", "Missing Product Name", "EUR", BigDecimal.valueOf(10))
        );
    }

    @Test
    void match_throwsNullPointerException_whenParamIsNull() {
        // when
        assertThatThrownBy(() -> productService.match(null))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage("MatchTradesCommand must not be null");
    }

    @Test
    void match_skipIncorrectDateElements() {
        //given
        final String invalidDate = "20221311";
        MatchTradesCommand command = new MatchTradesCommand(List.of(
                new UnmatchedTradeDto(1, invalidDate, "EUR", BigDecimal.valueOf(10)),
                new UnmatchedTradeDto(2, "20221218", "EUR", BigDecimal.valueOf(10))));
        // when
        List<MatchedTradeDto> result = productService.match(command);
        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(new MatchedTradeDto("20221218", "Corporate Bonds Domestic", "EUR", BigDecimal.valueOf(10)));
    }
}
