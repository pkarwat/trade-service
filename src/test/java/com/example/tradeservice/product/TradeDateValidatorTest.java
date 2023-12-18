package com.example.tradeservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TradeDateValidatorTest {

    @Test
    void isValid_returnsTrue_whenDateFormatIsYYYYMMDD() {
        // when
        boolean result = TradeDateValidator.isValid("20231218");
        // then
        assertThat(result).isTrue();
    }

    @Test
    void isValid_returnsFalse_whenDateFormatIsNoYYYYMMDD() {
        // when
        boolean result = TradeDateValidator.isValid("20231318");
        // then
        assertThat(result).isFalse();
    }
}
