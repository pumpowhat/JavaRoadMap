# Bài 14: Package và Access Modifier trong Java

## 1. Package là gì?

- Package là không gian tên (namespace) dùng để tổ chức các class liên quan.
- Giúp tránh trùng tên class, phân chia mã nguồn rõ ràng và kiểm soát quyền truy cập.
- Tên package thường viết thường và theo tên miền đảo ngược, ví dụ: `com.example.student`.

### Lợi ích

- Tránh trùng tên class.
- Tổ chức code tốt hơn.
- Dễ bảo trì.
- Kiểm soát quyền truy cập.

## 2. Tạo và dùng Package

Ví dụ cấu trúc thư mục:

```text
com/
└── qa/
    └── insights/
        ├── Student.java
        └── MainApp.java
```

Khai báo package phải nằm ở đầu file Java (sau comment nếu có):

```java
package com.qa.insights;

public class Student {
    String name;
}
```

Khi class ở package khác, cần `import` trước khi sử dụng:

```java
import com.qa.insights.Student;

public class MainApp {
    public static void main(String[] args) {
        Student student = new Student();
    }
}
```

## 3. Câu lệnh import

- Dùng để sử dụng class hoặc package khác.
- `java.lang` được Java tự động import, ví dụ: `String`, `System`, `Math`.

```java
import java.util.ArrayList; // Import một class cụ thể
import java.util.*;         // Import tất cả class trong java.util
import static java.lang.Math.*; // Import static member

double canBacHai = sqrt(25);
```

> Nên import class cụ thể khi có thể để code rõ ràng hơn.

## 4. Access Modifier (phạm vi truy cập)

| Modifier | Trong cùng class | Trong cùng package | Class con ở package khác | Ngoài package | Phù hợp nhất cho |
|----------|------------------|--------------------|--------------------------|---------------|------------------|
| `public` | Có | Có | Có | Có | Truy cập tối đa |
| `private` | Có | Không | Không | Không | Che giấu dữ liệu |
| `protected` | Có | Có | Có | Không | Kế thừa |
| *(không ghi gì)* | Có | Có | Không | Không | Truy cập trong package |

Lưu ý: `protected` cho phép class con ở package khác truy cập thông qua cơ chế kế thừa, nhưng không cho một class bất kỳ ở package khác truy cập trực tiếp.

## 5. Ví dụ

```java
class Student {
    public String school = "ABC";       // Có thể truy cập ở mọi nơi
    private int age = 18;                // Chỉ dùng trong Student
    protected String major = "Java";    // Package hiện tại và class con
    String studentID = "SV001";         // default: chỉ trong cùng package

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

```java
class ITStudent extends Student {
    void showMajor() {
        System.out.println(major); // Có thể truy cập protected qua kế thừa
    }
}
```

## 6. Truy cập trong các tình huống

| Tình huống | `public` | `protected` | `default` | `private` |
|------------|----------|-------------|-----------|-----------|
| Cùng class | ✓ | ✓ | ✓ | ✓ |
| Cùng package | ✓ | ✓ | ✓ | ✗ |
| Class con ở package khác | ✓ | ✓ | ✗ | ✗ |
| Package khác, không kế thừa | ✓ | ✗ | ✗ | ✗ |

### Ghi nhớ

- Ưu tiên khai báo thuộc tính là `private` và cung cấp getter/setter khi cần.
- Dùng `public` cho API hoặc hành vi cần gọi từ bên ngoài.
- Dùng `protected` khi muốn class con có thể kế thừa và sử dụng thành phần đó.
- Dùng `default` khi thành phần chỉ nên được dùng trong cùng package.
