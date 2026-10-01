# Bài 2: JDK, JRE, JVM

## 1. JDK (Java Development Kit)

- Bộ công cụ đầy đủ để phát triển java
- Bao gồm JRE, Các công cụ để phát triển
- DÙng để viết, biên dịch và chạy chương trình JAVA
- Bao gồm:
    * JRE
    * Trình biên dịch (javac)
    * Công cụ (javadoc, jar, jdb,...)

## 2. JRE (Java runtime environment)

- Cung cấp thư viện, JVM và các thành phần khác để chạy ứng dụng java
- Không bao gồm công cụ phát tieenr
- Bao gồm
    - JVM
    - Thư viện lõi (rt.jar,..)
## 3. JVM (Java Virtual Machine)
- Trái tim của java
- Thực thi butecode
- Độc lập nền tảng - Write once, run everywhere
- Quản lí bộ nhớ, luồng, bảo mật và nhiều hơn nữa

## 4. Quá trình biên dịch
Hello.java --> javac --> Hello.class --> JVM --> kết quả

## 5. Bytecode

- Kết quả đầu ra của trình biên dịch javac
- Độc lập nền tảng
- Được JVM thực thi, không chạy trực tiếp trên hệ điều hành
- Phần mở rộng: .class