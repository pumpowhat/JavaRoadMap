# Bài 11: Đóng gói và kế thừa trong Java

## 1. Đóng gói

## 2. Getter và Setter

## 3. Kế thừa (inheritance)

## 4. Các loại kế thừa

- Đơn
- Nhiều tầng
- Phân cấp
- Đa kế thừa
- Lai

```java
class Animal{
    String type = "Animal";
    Animal(){
        System.out.println("Animal Constructor");
    }
}
class Dog extends Animal{
    String type = "Dog";
    Dog(){
        super(); //Gọi constructor cha
        System.out.println("Dog Constructor");   
    }
    void show(){
        System.out.println(super.type)
    }
}
```