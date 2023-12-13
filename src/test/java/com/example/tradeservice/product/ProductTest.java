package com.example.tradeservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void constructor_returnNewProduct_ForValidParams() {
        // given when
        Product product = new Product(1, "Mark");
        // then
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("Mark");
    }
}
