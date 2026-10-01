
/*
         * BÀI TẬP LAMBDA
         *
         * Cho danh sách:
         *     List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
         *
         * Hãy dùng Lambda và Stream API để:
         * 1. Lọc ra các số chẵn.
         * 2. Nhân mỗi số chẵn với 2.
         * 3. In kết quả ra màn hình.
         *
         * Kết quả mong đợi:
         *     4
         *     8
         *     12
         *     16
         *     20
         *
         * Gợi ý: sử dụng stream(), filter(), map() và forEach().
 */

import java.util.List;

public class testing {

    public static void main(String[] args) {
        List<String> names = List.of("An", "Binh", "Chi");
        names.forEach(name -> System.out.println(name));

    }
}
