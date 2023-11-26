package quanlythongtin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Coures {
    private String maKhoaHoc;
    private String tenKhoaHoc;
    private String moTaKhoaHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public Coures() {

    }

    public Coures(String maKhoaHoc, String tenKhoaHoc, String moTaKhoaHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.moTaKhoaHoc = moTaKhoaHoc;

    }
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getMoTaKhoaHoc() {
        return moTaKhoaHoc;
    }

    public void setMoTaKhoaHoc(String moTaKhoaHoc) {
        this.moTaKhoaHoc = moTaKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    // Scanner
    Scanner input = new Scanner(System.in);

    // Chức năng hiển thị danh sách khoá học
    public void DisplayDSCourses(DatabaseManager dbManager) {
        System.out.println("Hiển thị danh sách khoá học");
        DatabaseManager.DisplayDsCoursesData(dbManager);
    }

    // FUNCTION ADD COURSES
    public void AddCourses(DatabaseManager dbManager) {
        Connection connection;
        System.out.println("\t\tNHẬP THÔNG TIN KHOÁ HỌC");
        System.out.print("Nhập mã khoá học: ");
        int maKhoaHoc = input.nextInt();
        input.nextLine();
        System.out.print("Nhập tên khoá học: ");
        String tenKhoaHoc = input.nextLine();
        System.out.print("Nhập mô tả khoá học: ");
        String moTa = input.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập ngày bắt đầu khoá học(dd/MM/yyyy): ");
        String date = input.nextLine();
        try {
            ngayBatDau = dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Ngày nhập không hợp lệ!");
        }
        System.out.print("Nhập ngày kết thúc khoá học(dd/MM/yyyy): ");
        String date2 = input.nextLine();
        try {
            ngayKetThuc = dateFormat.parse(date2);
        } catch (Exception e) {
            System.out.println("Ngày nhập không hợp lệ!");
        }
        try {
            connection = dbManager.getConnection();
            // Tạo truy vấn SQL để chèn dữ liệu
            String sql = "INSERT INTO khoa_hoc (ma_khoa_hoc,ten_khoa_hoc,mo_ta,ngay_bat_dau,ngay_ket_thuc) VALUES (?,?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            // Đặt các tham số cho truy vấn
            statement.setInt(1, maKhoaHoc);
            statement.setString(2, tenKhoaHoc);
            statement.setString(3, moTa);
            statement.setDate(4, new  java.sql.Date(ngayBatDau.getTime()));
            statement.setDate(5, new java.sql.Date(ngayKetThuc.getTime()));

            // Thực hiện truy vấn chèn dữ liệu
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("\u001B[42m\nSuccess! Thêm Course thành công");
                System.out.println("\u001B[0m");

            } else {
                System.out.println("\u001B[31mError! Thêm Course không thành công");
                System.out.println("\u001B[0m");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        // FUCTION DELETE COURSES
    public void DeleteCourses(DatabaseManager dbManager){
    Connection connection = null;
        try {
        connection = dbManager.getConnection();
        System.out.println("\u001B[47m\u001B[30mNhập mã khoá học cần xoá: ");
        int maKhoaHoc= input.nextInt();
        System.out.println("\u001B[0m");
        input.nextLine(); // Đọc ký tự xuống dòng còn sót lại

        System.out.println("Bạn có chắc chắn không (Y/N): ");
        String check = input.nextLine();

        if (check.equalsIgnoreCase("Y")) {
            String sql = "DELETE FROM khoa_hoc WHERE ma_khoa_hoc = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maKhoaHoc);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\u001B[42mXóa khoá học thành công!\u001B[0m");
            } else {
                System.out.println("\u001B[41mKhông tìm thấy khoá học có mã " + maKhoaHoc+"\u001B[0m");
            }
        } else {
            System.out.println("\u001B[41mYêu cầu đã bị huỷ !!!\u001B[0m");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
  // Chức năng tìm kiếm khoá học theo mã

}
