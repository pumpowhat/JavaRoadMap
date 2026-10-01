# Bài 24: Concurrency Basics trong Java

Concurrency là khả năng chương trình thực hiện nhiều công việc có thể tiến triển xen kẽ trong cùng một khoảng thời gian.

Trong Java, concurrency thường được thực hiện thông qua Thread.

Bài này tập trung vào các khái niệm nền tảng:

- Process và Thread.
- Tạo và chạy Thread.
- Thread lifecycle.
- start() và run().
- sleep() và join().
- Thread interruption.
- Daemon Thread.
- Đặt tên và theo dõi Thread.

## 1. Process và Thread

Process là một chương trình đang chạy.

Ví dụ:

~~~text
Ứng dụng Java đang chạy = một process
~~~

Thread là một luồng thực thi bên trong process.

Một process có thể có nhiều thread:

~~~text
Process
├── Main Thread
├── Worker Thread 1
├── Worker Thread 2
└── Worker Thread 3
~~~

Các thread trong cùng một process thường chia sẻ:

- Heap memory.
- Object.
- Biến static.
- Tài nguyên của process.

Mỗi thread có riêng:

- Stack.
- Program counter.
- Trạng thái thực thi.

## 2. Concurrency và Parallelism

Concurrency là việc nhiều task cùng tiến triển, có thể bằng cách chuyển đổi qua lại giữa các thread.

Parallelism là nhiều task thực sự chạy cùng lúc trên nhiều CPU core.

~~~text
Concurrency → nhiều task cùng tiến triển
Parallelism → nhiều task chạy đồng thời thật sự
~~~

Một chương trình có thể có concurrency nhưng không nhất thiết chạy parallelism nếu máy chỉ có một CPU core.

## 3. Main Thread

Mỗi chương trình Java bắt đầu với một main thread.

~~~java
public class Main {
    public static void main(String[] args) {
        Thread current = Thread.currentThread();

        System.out.println(current.getName());
        System.out.println(current.getState());
    }
}
~~~

Kết quả thường là:

~~~text
main
RUNNABLE
~~~

Lấy thread hiện tại:

~~~java
Thread currentThread = Thread.currentThread();
~~~

Đặt tên cho thread:

~~~java
currentThread.setName("Main-Thread");
~~~

## 4. Tạo Thread bằng cách kế thừa Thread

Có thể tạo thread bằng cách kế thừa class Thread.

~~~java
class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("Worker đang chạy");
    }
}
~~~

Khởi chạy:

~~~java
public class Main {
    public static void main(String[] args) {
        WorkerThread worker = new WorkerThread();
        worker.start();
    }
}
~~~

Method run() chứa công việc thread cần thực hiện.

Method start() tạo thread mới và sau đó gọi run() trên thread mới đó.

## 5. Tạo Thread bằng Runnable

Runnable là cách linh hoạt hơn để mô tả công việc cần chạy.

~~~java
class PrintTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task đang chạy");
    }
}
~~~

~~~java
Thread thread = new Thread(new PrintTask());
thread.start();
~~~

Một class có thể implements nhiều interface nhưng chỉ extends được một class. Vì vậy, dùng Runnable thường linh hoạt hơn extends Thread.

## 6. Tạo Thread bằng Lambda

Runnable là Functional Interface nên có thể dùng lambda:

~~~java
Runnable task = () -> {
    System.out.println("Task đang chạy");
};

Thread thread = new Thread(task);
thread.start();
~~~

Có thể viết ngắn hơn:

~~~java
Thread thread = new Thread(
        () -> System.out.println("Task đang chạy")
);

thread.start();
~~~

## 7. start() và run() khác nhau

Dùng start():

~~~java
Thread thread = new Thread(() ->
        System.out.println(Thread.currentThread().getName())
);

thread.start();
~~~

Kết quả thường là tên thread khác main.

Dùng run():

~~~java
thread.run();
~~~

run() chỉ là method thông thường. Nó chạy trực tiếp trên thread hiện tại và không tạo thread mới.

So sánh:

| Cách gọi | Kết quả |
|----------|---------|
| start() | Tạo thread mới |
| run() | Chạy như method bình thường |

Không được gọi start() hai lần trên cùng một Thread:

~~~java
thread.start();
thread.start(); // IllegalThreadStateException
~~~

Một object Thread chỉ được start một lần.

## 8. Thread Lifecycle

Thread trong Java có các trạng thái chính:

| State | Ý nghĩa |
|-------|---------|
| NEW | Đã tạo nhưng chưa start |
| RUNNABLE | Sẵn sàng hoặc đang chạy |
| BLOCKED | Đang chờ lock |
| WAITING | Chờ vô thời hạn một thread hoặc signal |
| TIMED_WAITING | Chờ trong một khoảng thời gian |
| TERMINATED | Đã kết thúc |

Ví dụ kiểm tra state:

~~~java
Thread thread = new Thread(() -> {
    System.out.println("Đang chạy");
});

System.out.println(thread.getState()); // NEW

thread.start();

System.out.println(thread.getState());
~~~

Trạng thái thay đổi theo thời điểm, nên không nên dùng getState() để điều khiển logic concurrency phức tạp.

## 9. sleep()

Thread.sleep() tạm dừng thread hiện tại trong một khoảng thời gian.

~~~java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
~~~

1000 milliseconds tương đương 1 giây.

Ví dụ đếm ngược:

~~~java
public class Countdown {
    public static void main(String[] args) {
        for (int i = 3; i >= 1; i--) {
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Start");
    }
}
~~~

sleep() không giải phóng lock mà thread đang giữ.

## 10. join()

join() bắt thread hiện tại chờ thread khác kết thúc.

~~~java
Thread worker = new Thread(() -> {
    System.out.println("Worker bắt đầu");

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    System.out.println("Worker kết thúc");
});

worker.start();
worker.join();

System.out.println("Main chạy sau worker");
~~~

Nếu không có join(), main có thể in trước khi worker kết thúc.

join() có thể nhận timeout:

~~~java
worker.join(500);
~~~

Main chỉ chờ tối đa 500 milliseconds.

join() có thể ném InterruptedException vì thread đang chờ cũng có thể bị interrupt.

## 11. Thread interruption

Interrupt là cơ chế yêu cầu thread dừng hoặc thay đổi hành vi một cách hợp tác.

Interrupt không bắt buộc thread dừng ngay lập tức.

Gửi interrupt:

~~~java
worker.interrupt();
~~~

Kiểm tra cờ interrupt:

~~~java
if (Thread.currentThread().isInterrupted()) {
    return;
}
~~~

Không nên dùng method stop() để dừng thread vì stop() không an toàn và đã deprecated.

## 12. Xử lý InterruptedException

Một số method như sleep() và join() ném InterruptedException.

Cách xử lý thông thường:

~~~java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return;
}
~~~

Việc gọi lại:

~~~java
Thread.currentThread().interrupt();
~~~

giúp khôi phục trạng thái interrupt để code phía trên có thể biết thread đã bị yêu cầu dừng.

Không nên bắt exception rồi bỏ qua:

~~~java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    // Không làm gì
}
~~~

Cách này làm mất thông tin interrupt.

## 13. Ví dụ dừng task an toàn

~~~java
class DownloadTask implements Runnable {
    @Override
    public void run() {
        for (int part = 1; part <= 10; part++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Download bị hủy");
                return;
            }

            System.out.println("Đang tải phần " + part);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Download bị hủy");
                return;
            }
        }

        System.out.println("Download hoàn tất");
    }
}
~~~

Sử dụng:

~~~java
Thread download = new Thread(new DownloadTask());
download.start();

Thread.sleep(1000);
download.interrupt();
~~~

Task kiểm tra interrupt và tự kết thúc một cách an toàn.

## 14. Thread name

Đặt tên thread giúp đọc log và debug dễ hơn.

~~~java
Thread worker = new Thread(
        () -> System.out.println("Đang xử lý"),
        "Order-Worker"
);

worker.start();
~~~

Lấy tên:

~~~java
System.out.println(worker.getName());
~~~

Đặt tên sau khi tạo:

~~~java
worker.setName("Payment-Worker");
~~~

## 15. Thread priority

Mỗi thread có priority từ 1 đến 10.

~~~java
thread.setPriority(Thread.MAX_PRIORITY);
~~~

Các hằng số:

~~~text
Thread.MIN_PRIORITY    = 1
Thread.NORM_PRIORITY   = 5
Thread.MAX_PRIORITY    = 10
~~~

Priority chỉ là gợi ý cho scheduler, không đảm bảo thread có priority cao sẽ luôn chạy trước.

Không nên dùng priority để xây dựng logic chính xác.

## 16. Daemon Thread

Daemon thread là thread chạy nền hỗ trợ các thread khác.

Nếu tất cả non-daemon thread đã kết thúc, JVM có thể kết thúc dù daemon thread vẫn đang chạy.

~~~java
Thread background = new Thread(() -> {
    while (true) {
        System.out.println("Background task");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
});

background.setDaemon(true);
background.start();
~~~

Phải gọi setDaemon(true) trước start():

~~~java
background.setDaemon(true);
background.start();
~~~

Không được gọi sau khi thread đã chạy.

Không nên dùng daemon thread cho công việc cần hoàn thành hoặc cần lưu dữ liệu quan trọng.

## 17. Uncaught Exception

Nếu exception thoát ra khỏi method run(), thread có thể kết thúc.

~~~java
Thread thread = new Thread(() -> {
    throw new RuntimeException("Có lỗi trong worker");
});

thread.setUncaughtExceptionHandler((threadObject, exception) -> {
    System.out.println(
            threadObject.getName() + ": " + exception.getMessage()
    );
});

thread.start();
~~~

UncaughtExceptionHandler giúp ghi log hoặc xử lý exception chưa được bắt trong thread.

## 18. Thread-safe và non-thread-safe

Một class thread-safe có thể được sử dụng an toàn từ nhiều thread theo đúng contract của nó.

Một class non-thread-safe có thể xảy ra lỗi nếu nhiều thread truy cập đồng thời.

Ví dụ biến đếm đơn giản:

~~~java
class Counter {
    private int value;

    void increment() {
        value++;
    }

    int getValue() {
        return value;
    }
}
~~~

Trong các bài sau sẽ học race condition và cách bảo vệ dữ liệu dùng chung.

## 19. Thread và shared memory

Các thread trong cùng process có thể cùng truy cập object trên heap.

~~~java
class SharedData {
    int value;
}
~~~

~~~java
SharedData data = new SharedData();

Thread first = new Thread(() -> data.value = 10);
Thread second = new Thread(() -> System.out.println(data.value));

first.start();
second.start();
~~~

Kết quả không nên được giả định nếu chưa có cơ chế đồng bộ. Những vấn đề này sẽ được phân tích trong bài Race Condition và Java Memory Model.

## 20. Không nên tạo quá nhiều Thread thủ công

Có thể tạo thread trực tiếp cho ví dụ nhỏ:

~~~java
new Thread(() -> doWork()).start();
~~~

Nhưng ứng dụng thực tế có thể cần xử lý hàng nghìn task. Tạo một thread cho mỗi task có thể gây:

- Tốn memory.
- Tốn thời gian tạo và hủy thread.
- Context switching nhiều.
- Khó quản lý lifecycle.

Các bài sau sẽ giới thiệu ExecutorService, Thread Pool và Virtual Threads.

## 21. Ví dụ tổng hợp

~~~java
public class ThreadExample {
    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Worker bị interrupt");
                    return;
                }

                System.out.println(
                        Thread.currentThread().getName()
                                + " - bước " + i
                );

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Worker-1");

        worker.start();
        worker.join();

        System.out.println("Main kết thúc");
    }
}
~~~

## 22. Điểm chính

- Process là chương trình đang chạy; Thread là luồng thực thi bên trong process.
- Một process có thể có nhiều thread.
- Dùng start() để tạo thread mới.
- Gọi run() trực tiếp không tạo thread mới.
- Một Thread object chỉ được start một lần.
- sleep() tạm dừng thread hiện tại.
- join() chờ thread khác kết thúc.
- interrupt() là yêu cầu thread dừng một cách hợp tác.
- Nên khôi phục interrupt status sau khi bắt InterruptedException.
- Daemon thread chạy nền và không giữ JVM sống khi các thread chính đã kết thúc.
- Không nên dùng Thread.stop().

## 23. Thực hành tốt

- Dùng Runnable để tách task khỏi Thread.
- Đặt tên thread có ý nghĩa.
- Luôn xử lý InterruptedException đúng cách.
- Không bỏ qua interrupt.
- Dùng join() khi cần chờ worker hoàn thành.
- Không dùng sleep() để đồng bộ hóa chính xác.
- Không dựa vào thread priority để đảm bảo thứ tự.
- Không dùng daemon thread cho công việc quan trọng.
- Không truy cập dữ liệu dùng chung mà chưa nghĩ đến synchronization.
- Với nhiều task, dùng ExecutorService thay vì tự tạo quá nhiều Thread.

## 24. Các lỗi thường gặp

| Lỗi | Nguyên nhân |
|-----|-------------|
| Thread không chạy song song | Gọi run() thay vì start() |
| IllegalThreadStateException | Gọi start() hai lần trên cùng Thread |
| Main kết thúc quá sớm | Chưa dùng join() khi cần chờ worker |
| Thread không dừng | Không kiểm tra hoặc xử lý interrupt |
| Mất interrupt status | Bắt InterruptedException nhưng không gọi interrupt() lại |
| Kết quả không ổn định | Nhiều thread truy cập shared data |
| JVM kết thúc khi task chưa xong | Dùng daemon thread cho công việc quan trọng |
| Tốn tài nguyên | Tạo quá nhiều thread thủ công |

## 25. Ghi nhớ

~~~text
Thread task → Runnable
Tạo thread → new Thread(...)
Chạy thread → start()
Chờ thread → join()
Tạm dừng → sleep()
Yêu cầu dừng → interrupt()
~~~

Ví dụ ngắn:

~~~java
public static void main(String[] args) throws InterruptedException {
    Thread worker = new Thread(() -> {
        System.out.println("Task đang chạy");
    });

    worker.start();
    worker.join();
}
~~~

Ở các bài tiếp theo, sẽ học cách xử lý race condition, visibility, atomicity và dữ liệu dùng chung giữa nhiều thread.
