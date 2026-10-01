package com.shop.catalog.api;

import com.shop.catalog.internal.InMemoryProductCatalog;

public final class ProductCatalogFactory {
    private ProductCatalogFactory() {
    }

    public static ProductCatalog createDefault() {
        return new InMemoryProductCatalog();
    }
}

