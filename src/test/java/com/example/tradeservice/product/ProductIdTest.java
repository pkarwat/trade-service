package com.example.tradeservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    void constructor_createsNewProductId_whenParamIsValid() {
        // given when
        ProductId productId = new ProductId(1);
        // then
        assertThat(productId.getValue()).isEqualTo(1);
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenParamIsNegative() {
        // when then
        assertThrows(
                IllegalArgumentException.class,
                ()-> new ProductId(-1)
        );
    }
}
