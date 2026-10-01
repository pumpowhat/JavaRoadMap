# Bài 1: Giới thiệu về Java

## 1. Java là gì?
- Java là ngôn ngữ lập trình hướng đối tượng, bậc cao, dựa trên class, được Sun Microsystems phát triển (nay thuộc Oracle). Java độc lập nền tảng, bảo mật, manhjm ẽ và được dùng rộng rãi để xây dựng ứng dụng doanh nghiệp, ứng dụng Android, ứng dụng web và nhiều hơn nữa

## 2. Đặc điểm của Java
- Đơn giản
- OOP
- Độc lập nền tảng
- Bảo mật
- Mạnh mẽ
- Đa luồng
- Trung lập kiến trúc
- Khả chuyển
- Hiệu năng cao
- Phân tán
- Dynamic
- Tự động quản lý bộ nhớ

## 3. JDK, JRE, JVM
- JDK (java development kit) cung cấp công cụ phát triển game như javac, java debugger,..
- JRE (java runtime environment) cung cấp thư viện, các thành phần khác để chạy chương trình java
- JVM (java virtual machine) thực thi bytecode và cung cấp môi trường chạy

## 4. Java hoạt động như thế nào ?

filename.java --> .class --> JVM thực thi bytecode --> kết quả

## 5. Luồng biên dịch và thực thi

Viết mã Java (.java) --> biên dịch bằng javac (compiler) --> sinh ra bytecode --> JVM nạp và kiểm tra bytecode --> JVM thực thi bytecode --> kết quả được hiển thị

## 6. CHương trình java đầu tiên

```java
public class HelloWorld{
    public static void main(String[] args){
        System.out.println("Hello, Java");
    }
}
```
- Tên file phải trùng với tên class

