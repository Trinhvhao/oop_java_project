package quanlythongtin;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class DatabaseManager {
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        establishConnection();
    }

    private void establishConnection() {
        try {
            // CONNECT TO DATABASE
            // Tạo đối tượng SQLServerDataSource để cấu hình kết nối cơ sở dữ liệu
            SQLServerDataSource dataB = new SQLServerDataSource();
            dataB.setTrustServerCertificate(true);
            dataB.setURL(url);
            dataB.setUser(username);
            dataB.setPassword(password);
            // Tạo kết nối mới
            connection = dataB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error establishing database connection.", e);
        }
    }

    // Lấy kết nối đến cơ sở dữ liệu
    public Connection getConnection() {
        try {
            // Kiểm tra kết nối, nếu đã đóng hoặc chưa khởi tạo thì tạo kết nối mới
            if (connection == null || connection.isClosed()) {
                // Tạo kết nối mới nếu chưa tồn tại hoặc đã đóng
                establishConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        // Trả về kết nối hiện tại ( đã tồn tại hoặc tạo mới )
        return connection;
    }

    public static void DisplayHocVienData(DatabaseManager dbManager) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // Câu truy vấn SQL
        String sql = "SELECT * FROM hoc_vien";

        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM hoc_vien");
            // Duyệt qua kết quả và hiển thị dữ liệu
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("\u001B[1m\u001B[44m|   ID   |       Name       |   Ngày sinh   | Số điện thoại  |   Địa chỉ   |   ");
            System.out.println("\u001B[0m");
            while (resultSet.next()) {
                String id = resultSet.getString("ma_hoc_vien");
                String name = resultSet.getString("ho_ten");
                String age = resultSet.getString("ngay_sinh");
                String phone = resultSet.getString("so_dien_thoai");
                String address = resultSet.getString("dia_chi");

                // hiển thị danh sách học viên
                // Dòng dữ các dòng
                System.out.printf("|   %3s  |  %-15s |   %7s  |   %8s   |   %7s   |   %n", id, name, age, phone, address);

                // Dòng kết thúc
                System.out.println("---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DisplayDSClassData(DatabaseManager dbManager) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // Câu truy vấn SQL
        String sql = "SELECT * FROM lop_hoc";

        try {
            connection = dbManager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM lop_hoc");

            // Duyệt qua kết quả và hiển thị dữ liệu
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("|   Mã lớp học   |     Tên lớp học     |    Nội dung lớp   |     Số lượng     | Ngày đăng kí  |   Tình trạng   |   ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String maLopHoc = resultSet.getString("ma_lop_hoc");
                String tenLopHoc = resultSet.getString("ten_lop_hoc");
                String noiDungClass = resultSet.getString("noi_dung_lop");
                String soLuong = resultSet.getString("so_luong");
                String ngayDangKi = resultSet.getString("ngay_dang_ky");
                String tinhTrang = resultSet.getString("tinh_trang");
                String check = "";
                if (tinhTrang.equals("1")) {
                    check = "Thiếu";
                } else {
                    check = "Full";
                }

                // hiển thị danh sách học viên
                // Dòng dữ liệu cần chỉnh lại định dạng
                System.out.printf("|        %-6s  |  %-18s |   %-13s  |   %-10s   |   %-10s   |   %-10s   |%n", maLopHoc, tenLopHoc, noiDungClass, soLuong, ngayDangKi, check);
                // Dòng kết thúc
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DisplayDsCoursesData(DatabaseManager dbManager) {
        Connection connection = null;
        // Câu truy vấn SQL
        String sql = "SELECT * FROM khoa_hoc";

        try {
            connection = dbManager.getConnection();

            // Tạo đối tượng Statement để thực thi câu truy vấn
            Statement statement = connection.createStatement();

            // Thực thi truy vấn và nhận kết quả
            ResultSet resultSet = statement.executeQuery(sql);

            // Duyệt qua kết quả và hiển thị dữ liệu
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            System.out.println("\u001B[1m\u001B[44m|   Mã khoá học   |     Tên khoá học     |    Mô tả   |     Ngày bắt đầu     | Ngày kết thúc  |");
            System.out.println("\u001B[0m");
            while (resultSet.next()) {
                String maKhoaHoc = resultSet.getString("ma_khoa_hoc");
                String tenKhoaHoc = resultSet.getString("ten_khoa_hoc");
                String moTa = resultSet.getString("mo_ta");
                String ngayBatDau = resultSet.getString("ngay_bat_dau");
                String ngayKetThuc = resultSet.getString("ngay_ket_thuc");
                System.out.printf("|    %-6s  |  %-18s |   %-13s  |   %-10s   |   %-10s   |%n", maKhoaHoc, tenKhoaHoc, moTa, ngayBatDau, ngayKetThuc);

                // Dòng kết thúc
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối sau khi hoàn thành công việc
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

