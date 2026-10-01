package com.shop.catalog.api;

import java.util.Optional;

public interface ProductCatalog {
    Optional<Product> findByCode(String code);
}

