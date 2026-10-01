
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Kết nối và chạy query trên database MariaDB đã tồn tại.
 *
 * Trước khi chạy, cấu hình biến môi trường DB_PASSWORD cho đúng máy của bạn.
 * File này chỉ chạy SELECT, không thay đổi dữ liệu.
 */
public class MariaDbConnectionExample {

    // Database có sẵn trong MySQL Workbench của bạn.
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/QuanLyDeTai";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        try (Connection connection
                = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD)) {

            System.out.println("Kết nối database thành công!");

            DatabaseMetaData metadata = connection.getMetaData();
            System.out.println("Database: " + metadata.getDatabaseProductName());
            System.out.println("Version: " + metadata.getDatabaseProductVersion());
            System.out.println("URL: " + metadata.getURL());

            runQuery(connection, "SELECT 1", "Kiểm tra kết nối");

            runQuery(connection, """
                    SELECT MAGV, HOTEN, PHAI, NGSINH
                    FROM GIAOVIEN
                    ORDER BY MAGV
                    LIMIT 10
                    """, "Danh sách 10 giáo viên đầu tiên");

            runQuery(connection, """
                    SELECT BM.MABM, BM.TENBM, COUNT(GV.MAGV) AS SO_LUONG_GIAO_VIEN
                    FROM BOMON BM
                    LEFT JOIN GIAOVIEN GV ON GV.MABM = BM.MABM
                    GROUP BY BM.MABM, BM.TENBM
                    ORDER BY BM.MABM
                    """, "Số lượng giáo viên theo bộ môn");

            runQuery(connection, """
                    SELECT GV.MAGV, GV.HOTEN, TG.MADT, CV.TENCV
                    FROM GIAOVIEN GV
                    JOIN THAMGIADT TG ON TG.MAGV = GV.MAGV
                    JOIN CONGVIEC CV ON CV.MADT = TG.MADT AND CV.SOTT = TG.STT
                    ORDER BY GV.MAGV, TG.MADT, CV.SOTT
                    LIMIT 20
                    """, "Công việc giáo viên đã tham gia");
        } catch (SQLException e) {
            System.out.println("Không thể kết nối database.");
            System.out.println("Kiểm tra lại URL, port, username, password và JDBC Driver.");
            e.printStackTrace();
        }
    }

    private static void runQuery(Connection connection, String sql, String title)
            throws SQLException {
        System.out.println("\n--- " + title + " ---");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int column = 1; column <= columnCount; column++) {
                    String columnName = resultSet.getMetaData().getColumnLabel(column);
                    Object value = resultSet.getObject(column);
                    System.out.print(columnName + "=" + value);

                    if (column < columnCount) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
            }
        }
    }
}
