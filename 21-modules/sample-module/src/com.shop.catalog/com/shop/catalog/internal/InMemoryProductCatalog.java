package com.shop.catalog.internal;

import com.shop.catalog.api.Product;
import com.shop.catalog.api.ProductCatalog;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public final class InMemoryProductCatalog implements ProductCatalog {
    private final Map<String, Product> products = Map.of(
            "JAVA", new Product("JAVA", "Java Course", new BigDecimal("120.00")),
            "BOOK", new Product("BOOK", "Java Book", new BigDecimal("35.50")),
            "USB", new Product("USB", "USB Drive", new BigDecimal("12.00"))
    );

    @Override
    public Optional<Product> findByCode(String code) {
        return Optional.ofNullable(products.get(code));
    }
}

