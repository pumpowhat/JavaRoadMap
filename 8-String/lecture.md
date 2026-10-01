# Bài 8: String

## 1. String

## 2. String Pool

- Các chuỗi literal được lưu trong String Pool
- Nếu Literal đã tồn tại, Java trả về cùng 1 tham chiếu
- Giúp tối ưu bộ nhớ

```java
String a = "Java";
String b = "Java";
```

--> Chỉ tạo 1 đối tượng Lưu trong String Pool, a và b trỏ cùng tới 1 tham chiếu

## 3. String là bất biến

- Đối tượng String không thể thay đổi sau khi tạo
- Mọi thao tác sửa chuỗi thực chất đều tạo ra 1 đối tượng mới

```java
String s1 = "Hello";
String s2 = s1.concat(" World")
System.out.println(s1) // Hello
System.out.println(s2) // Hello World
```

## 4. StringBuilder

- Lớp có thể thay đổi, dùng để chỉnh sửa chuỗi
- Không đồng bộ --> Không an toàn với đa luồng
- Hiệu quả hơn String khi chạy đơn luồng

```java
StringBuilder sb = new StringBuilder("Java")
sb.append(" Programming");
sb.insert(5, " is");
sb.delete(0,1); // Xóa 'J'
System.out.println(sb) // ava is Programming
```

## 5. StringBuffer

- Lớp có thể thay đổi, tương tự StringBuilder
- Được đồng bộ, an toàn với đa luồng
- Chậm hơn StringBuilder nhưng an toàn khi đa luồng

```java
StringBuffer sb = new StringBuffer("Java");
sb.append(" Programming");
sb.reverse();
System.out.println(sb); //Chuỗi bị đảo ngược
```

## 6. Các phương thức String thường dùng

| Phương thức | Mô tả | Ví dụ | Kết quả |
|-------------|-------|-------|---------|
| `length()` | Trả về độ dài chuỗi | `"Java".length()` | `4` |
| `charAt(index)` | Trả về ký tự tại vị trí `index` | `"Java".charAt(1)` | `'a'` |
| `substring(begin, end)` | Cắt một phần chuỗi từ `begin` đến trước `end` | `"Java".substring(1, 3)` | `"av"` |
| `equals(str)` | So sánh nội dung chuỗi | `"Java".equals("java")` | `false` |
| `equalsIgnoreCase(str)` | So sánh, bỏ qua chữ hoa/thường | `"Java".equalsIgnoreCase("java")` | `true` |
| `contains(str)` | Kiểm tra có chuỗi con | `"Java Programming".contains("Prog")` | `true` |
| `indexOf(str)` | Vị trí xuất hiện đầu tiên | `"Java".indexOf('v')` | `2` |
| `replace(old, new)` | Thay thế mọi ký tự khớp | `"Java".replace('a', 'o')` | `"Jovo"` |
| `toUpperCase()` | Chuyển thành chữ in hoa | `"Java".toUpperCase()` | `"JAVA"` |
| `trim()` | Xóa khoảng trắng ở hai đầu | `" Java ".trim()` | `"Java"` |
