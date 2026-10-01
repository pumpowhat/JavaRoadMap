# Sample Java Modules

## Bài toán

Ứng dụng bán hàng cần tính tổng tiền đơn hàng.

- Module com.shop.catalog quản lý danh sách sản phẩm.
- Module com.shop.order tạo đơn hàng và tính tổng tiền.
- Module order không được truy cập package internal của catalog.
- Module order chỉ làm việc thông qua ProductCatalog và Product.

## Cấu trúc

~~~text
sample-module
├── src
│   ├── com.shop.catalog
│   │   ├── module-info.java
│   │   └── com/shop/catalog
│   │       ├── api
│   │       └── internal
│   └── com.shop.order
│       ├── module-info.java
│       └── com/shop/order/Main.java
└── README.md
~~~

## Ý nghĩa module-info.java

Module catalog:

~~~java
module com.shop.catalog {
    exports com.shop.catalog.api;
}
~~~

Module catalog chỉ công khai package com.shop.catalog.api. Package com.shop.catalog.internal không được exports nên module khác không thể import trực tiếp implementation InMemoryProductCatalog.

Module order:

~~~java
module com.shop.order {
    requires com.shop.catalog;
}
~~~

Module order cần com.shop.catalog để sử dụng các class được module catalog exports.

## Biên dịch

Chạy các lệnh sau trong thư mục 21-modules:

~~~bash
rm -rf sample-module/out
javac -d sample-module/out \
  --module-source-path sample-module/src \
  -m com.shop.catalog,com.shop.order
~~~

## Chạy chương trình

~~~bash
java --module-path sample-module/out \
  -m com.shop.order/com.shop.order.Main
~~~

## Kết quả dự kiến

~~~text
=== CHI TIẾT ĐƠN HÀNG ===
Java Course x 2 = 240.00
Java Book x 1 = 35.50
USB Drive x 3 = 36.00
-------------------------
TỔNG CỘNG: 311.50
~~~

## Thử nghiệm tính đóng gói

Trong Main.java, thử thêm:

~~~java
import com.shop.catalog.internal.InMemoryProductCatalog;
~~~

Compiler sẽ báo lỗi vì package internal không được exports trong module-info.java.

Đây là lợi ích chính của module: code bên ngoài chỉ nhìn thấy API công khai, còn implementation bên trong được che giấu.

