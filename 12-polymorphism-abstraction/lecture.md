# Bài 12: Đa hình và trừu tượng

## 1. Đa hình (Polymorphism)

## 2. Đa hình lúc biên dịch

- Method overloading

```java
class Calculator{
    int add (int a, int b){
        return a + b;
    }
    int add (int a, int b, int c){
        return a + b + c;
    }
}
```

## 3. Đa hình lúc chạy

- Đạt được nhờ Method Overriding

```java
class Animal{
    void sound(){
        System.out.println("Animal sounds");
    }
}
class Dog extends Animal{
    @Override
    void sound(){
        System.out.println("Bark");
    }
}
Animal a = new Dog();
a.sound();
```

## 4. Abstract class

- Có thể chứa cả phương thức trwuuf tượng lẫn phương thức cụ thể
- Không thể tạo đối tượng trực tiếp
- Dùng để cung cấp phần nền chung và cài đặt một phần

```java
abstract class Shape{
    abstract doible area();
    void display(){
        System.out.println("Đây là 1 hình")'
    }
}
```

## 5. Interface

- Chỉ chứa phương thức trừu tượng (trước java8)
- Từ java8, có thể có phương thức default và static
- Trừu tượng hoàn toàn = đa kế thừa về kiểu

```java
interface Drawable{
    void draw();
    default void info(){
        System.out.println("Drawable interface");
    }
}
```

## 6. Ghi đè phương thức Overriding


