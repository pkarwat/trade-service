package com.example.tradeservice.shared;

import lombok.Value;

/**
 * Id field wrapper.
 */
@Value
public class ProductId {
    int value;

    public ProductId(int value) {
        if (value < 0) throw new IllegalArgumentException("Id must be positive");   // TODO validation can be replaced with apache-commons.lang3, javax or own implementation
        this.value = value;
    }
}
