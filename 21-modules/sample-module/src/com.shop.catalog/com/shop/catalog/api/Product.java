package com.shop.catalog.api;

import java.math.BigDecimal;

public record Product(
        String code,
        String name,
        BigDecimal price
) {
}

