# Bài 7: Mảng

## 1. Mảng 1 chiều

```java
int[] numbers = new int[5]
```

## 2. Mảng 2 chiều

```java
int[][] matrix = new int[2][3]
```

## 3. Các thao tác với mảng

- Khai báo
- Truy cập
- Cập nhật
- Lấy tổng số phần tử arr.length;
- Duyệt mảng

## 4. Vòng lặp for each

```java
int[] arr = {10, 20, 30, 40, 50}
for (int num : arr)
{
    System.out.println(num);
}
```

## 5. Các phương thức cung cấp sẵn cho mảng

- Arrays.toString(arr) : trả về chuỗi biểu diễn mảng
- Arrays.sort(arr) : sắp xếp mảng tăng dần
- Arrays.fill(arr,val): điền toàn bộ mnagr cùng 1 giá trị
- Arrays.copyOf(arr,len): sao chép mảng sang độ dài mới
- Arrays.equals(a1, a2): so sánh 2 mảng
