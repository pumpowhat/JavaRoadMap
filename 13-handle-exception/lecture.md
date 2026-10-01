# Bài 13: Xử lý ngoại lệ

## 1. Ngoại lệ là gì

## 2. Cây phân cấp Exception

## 3. try - catch - finally

```java
try{
    int result = 10/0;
} catch(ArithmeticException e){
    System.out.println("Divided by 0")
} finally{
    System.out.println("Khối finally luôn chạy");
}
```

## 4. Từ khóa throw

- Dùng để chủ đônjg ném ra 1 ngoại lệ
- Có thể ném bất kỳ đối tượng nào là lớp con của Throwable

```java
public void validate(int age){
    if (age < 18){
        throw new IllegalArgumentException("Tuổi phải từ 18 trở lên)
    }
}
```

## 5. Từ khóa throws
- DÙng trong chữ ký phương thức để khai báo ngoại lệ
- chuyển trách nhiệm xử lý cho nơi gọi

```java
public void readFile() throws IOException{
    FileReader fr = new FileReader("file.txt");
}
```

## 6. Ngoại lệ tự định nghĩa

```java
class InvalidAgeException extends Exception{
    public InvalidAgeException(String msg){
        super(msg);
    }
}
// Cách dùng
if (age < 0){
    throw new InvalidAgeException
}
```

## 7. Các ngoại lệ thường gặp

| Exception | Mô tả | Loại |
|-----------|-------|------|
| `ArithmeticException` | Chia cho 0, phép toán không hợp lệ | Unchecked |
| `NullPointerException` | Gọi phương thức trên đối tượng `null` | Unchecked |
| `ArrayIndexOutOfBoundsException` | Chỉ số mảng không hợp lệ | Unchecked |
| `NumberFormatException` | Chuyển `String` sang số không hợp lệ | Unchecked |
| `IOException` | Lỗi liên quan tới nhập / xuất | Checked |
| `ClassNotFoundException` | Không tìm thấy class lúc chạy | Checked |
