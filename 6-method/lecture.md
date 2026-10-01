## Bài 6: Phương thức trong Java(method)

## 1. Cú pháp phương thức

```java
accessModifier returnType methodName (parameterList){
    return value;
}
```

## 2. Parameters

```java
public int add(int a, int b){
    return a+b;
}
```

## 3. Kiểu trả về (return type)

## 4. Nạp chồng phương thức

- Cùng tên phương thức nhưng khác danh sách tham số
- Giúp code dễ đọc
```java
int add (int a, int b){
    return a+b;
}
int add (int a, int b, int c){
    return a + b + c;
}
```
## 5. Đệ quy (recursion)

## 6. Phạm vi của biến (Scope)

- Biến cục bộ: phạm vi trong khối lệnh
- Biến thể hiện: Trong class, ngoài các phương thức
- Biến Static : Toàn bộ class

## 7. Ví dụ hoàn chỉnh

```java
public class Demo{
    public static int multiply(int a, int b){
        return a * b;
    }
    public static void main(String[] args){
        int resule = multiply(5,4)
        System.out.println("Kết quả: " + result);
    }
}
```