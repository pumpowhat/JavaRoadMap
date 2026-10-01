package com.shop.order;

import com.shop.catalog.api.Product;
import com.shop.catalog.api.ProductCatalog;
import com.shop.catalog.api.ProductCatalogFactory;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductCatalog catalog = ProductCatalogFactory.createDefault();

        List<OrderLine> order = List.of(
                new OrderLine("JAVA", 2),
                new OrderLine("BOOK", 1),
                new OrderLine("USB", 3)
        );

        BigDecimal total = BigDecimal.ZERO;

        System.out.println("=== CHI TIẾT ĐƠN HÀNG ===");

        for (OrderLine line : order) {
            Product product = catalog.findByCode(line.productCode())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Không tìm thấy sản phẩm: " + line.productCode()
                    ));

            BigDecimal lineTotal =
                    product.price().multiply(BigDecimal.valueOf(line.quantity()));

            total = total.add(lineTotal);

            System.out.printf(
                    "%s x %d = %s%n",
                    product.name(),
                    line.quantity(),
                    lineTotal
            );
        }

        System.out.println("-------------------------");
        System.out.println("TỔNG CỘNG: " + total);
    }

    private record OrderLine(String productCode, int quantity) {
    }
}

