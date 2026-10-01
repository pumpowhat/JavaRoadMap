# Bài 15: Tổng quan Collections Framework

## 1. Collections Framework là gì?

- Collections Framework là tập hợp các class và interface dùng để lưu trữ, thao tác và truy xuất các nhóm đối tượng hiệu quả.
- Phần lớn nằm trong package `java.util`.
- Các thành phần chính: `List`, `Set`, `Queue`, `Deque` và `Map`.

```java
import java.util.ArrayList;
import java.util.HashMap;
```

## 2. Cây phân cấp Collection

```text
Iterable
└── Collection
    ├── List
    │   ├── ArrayList
    │   ├── LinkedList
    │   ├── Vector
    │   └── Stack
    ├── Set
    │   ├── HashSet
    │   ├── LinkedHashSet
    │   └── TreeSet
    ├── Queue
    │   ├── PriorityQueue
    │   ├── LinkedList
    │   └── ArrayDeque
    └── Deque
        ├── ArrayDeque
        └── LinkedList

Map (không kế thừa Collection)
├── HashMap
├── LinkedHashMap
├── TreeMap
└── Hashtable
```

## 3. List vs Set vs Queue vs Map

| Tiêu chí | List | Set | Queue | Map |
|----------|------|-----|-------|-----|
| Định nghĩa | Tập hợp có thứ tự các phần tử | Tập hợp các phần tử duy nhất | Xử lý phần tử theo thứ tự FIFO | Tập hợp cặp key - value |
| Trùng lặp | Cho phép | Không cho phép | Cho phép | Key: không; Value: có |
| Thứ tự | Giữ đúng thứ tự thêm vào | `HashSet`: không thứ tự; `LinkedHashSet`: thứ tự thêm; `TreeSet`: sắp xếp | FIFO (vào trước ra trước) | `HashMap`: không thứ tự; `LinkedHashMap`: thứ tự thêm; `TreeMap`: sắp xếp theo key |
| Cách truy cập | Theo chỉ số: `get()` / `set()` | Không truy cập theo chỉ số | Theo đầu/cuối: `offer()` / `poll()` / `peek()` | Theo key: `get()` / `put()` |
| Class tiêu biểu | `ArrayList`, `LinkedList`, `Vector`, `Stack` | `HashSet`, `LinkedHashSet`, `TreeSet` | `PriorityQueue`, `ArrayDeque`, `LinkedList` | `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable` |

## 4. Khi nào dùng loại nào?

### List

- Khi thứ tự quan trọng.
- Khi cho phép phần tử trùng lặp.
- Khi cần truy cập theo chỉ số.
- Ví dụ: danh sách sinh viên, tên, sản phẩm.

### Set

- Khi cần tính duy nhất.
- Khi không cần truy cập theo chỉ số.
- Ví dụ: lưu ID duy nhất, email URL đã truy cập.

### Queue

- Khi cần xử lý theo thứ tự FIFO.
- Dùng trong lập lịch, hệ đệm hoặc hàng đợi công việc.
- Ví dụ: hàng đợi máy in, hàng đợi tác vụ.

### Map

- Khi cần lưu dữ liệu theo cặp key - value.
- Khi cần tra cứu nhanh theo key.
- Ví dụ: `userId → user`, `countryCode → country`.

## 5. Ví dụ nhanh

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

List<String> names = new ArrayList<>();
names.add("An");
names.add("An"); // List cho phép trùng lặp

Set<String> emails = new HashSet<>();
emails.add("an@example.com");
emails.add("an@example.com"); // Chỉ lưu một giá trị

Queue<String> jobs = new ArrayDeque<>();
jobs.offer("In tài liệu");
String firstJob = jobs.poll(); // Lấy tác vụ vào đầu tiên

Map<String, String> countries = new HashMap<>();
countries.put("VN", "Việt Nam");
String country = countries.get("VN");
```

## 6. Ghi nhớ

- `Collection` quản lý các phần tử đơn lẻ; `Map` quản lý cặp key - value.
- Chọn đúng collection giúp code nhanh hơn và rõ ràng hơn.
- Hầu hết collection làm việc với đối tượng (kiểu tham chiếu), không dùng trực tiếp kiểu nguyên thủy.
- Khi phỏng vấn, cần nắm điểm khác nhau, tình huống sử dụng và độ phức tạp thời gian của từng collection.
