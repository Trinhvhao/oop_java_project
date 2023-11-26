package quanlythongtin;


import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

public class Classes {
    private int maLopHoc;
    private String tenLopHoc;
    private int soLuong;
    private Date ngayDangKi;
    private boolean tinhTrangLopHoc;
    private String noiDungClass;

    // Constructor không truyền tham số
    public Classes() {

    }

    // Constructor với tham số được truyền vào
    public Classes(int maLopHoc, String tenLopHoc, Date ngayDangKi, int soLuong, boolean tinhTrangLopHoc, String noiDungClass) {
        this.maLopHoc = maLopHoc;
        this.tenLopHoc = tenLopHoc;
        this.ngayDangKi = ngayDangKi;
        this.noiDungClass = noiDungClass;
        this.soLuong = soLuong;
        this.tinhTrangLopHoc = tinhTrangLopHoc;
    }

    public int getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(int maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getNoiDungClass() {
        return noiDungClass;
    }

    public void setNoiDungClass(String noiDungClass) {
        this.noiDungClass = noiDungClass;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public Date getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(Date ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public boolean getTinhTrangLopHoc() {
        return tinhTrangLopHoc;
    }

    public void setTinhTrangLopHoc(boolean tinhTrangLopHoc) {
        this.tinhTrangLopHoc = tinhTrangLopHoc;
    }

    Scanner input = new Scanner(System.in);

    // HIỂN THỊ DANH SÁCH LỚP HỌC
    public void DisplayDSClasses(DatabaseManager dbManager) {
        System.out.println("Hiển thị danh sách lớp học");
        DatabaseManager.DisplayDSClassData(dbManager);
    }

    // CHỨC NĂNG THÊM LỚP HỌC
    public void AddClasses(DatabaseManager dbManager) {
        Connection connection;
        System.out.println("\t\tNHẬP THÔNG TIN LỚP HỌC");
        System.out.println("Nhập mã lớp học: ");
        maLopHoc = input.nextInt();
        input.nextLine(); // Đọc ký tự xuống dòng còn sót lại
        System.out.println("Nhập tên lớp học: ");
        tenLopHoc = input.nextLine();
        System.out.println("Nhập số lượng học viên: ");
        soLuong = input.nextInt();
        input.nextLine();
        System.out.println("Nhập nội dung lớp học: ");
        noiDungClass = input.nextLine();
        System.out.println("Tình trạng lớp học: ");
        tinhTrangLopHoc = input.nextBoolean();
        input.nextLine(); // đọc ký tự xuống dòng còn sót lại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập ngày tạo lớp học (dd/MM/yyyy): ");
        String date = input.nextLine();
        try {
            ngayDangKi = dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Ngày tạo lớp học không hợp lệ!");
        }
        try {
            connection = dbManager.getConnection();
            // Tạo truy vấn SQL để chèn dữ liệu
            String sql = "INSERT INTO lop_hoc (ma_lop_hoc, ten_lop_hoc, so_luong, noi_dung_lop,tinh_trang,ngay_dang_ky) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Đặt các tham số cho truy vấn
            statement.setInt(1, maLopHoc);
            statement.setString(2, tenLopHoc);
            statement.setInt(3, soLuong);
            statement.setString(4, noiDungClass);
            statement.setBoolean(5, tinhTrangLopHoc);
            statement.setDate(6, new java.sql.Date(ngayDangKi.getTime())); //// Chuyển đổi ngàythành java.sql.Date

            // Thực hiện truy vấn chèn dữ liệu
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\u001B[42m\nSuccess! Thêm Class thành công");
                System.out.println("\u001B[0m");

            } else {
                System.out.println("\u001B[31mError! Thêm Class không thành công");
                System.out.println("\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // CHỨC NĂNG XOÁ LỚP HỌC
    public void DeleteClasses(DatabaseManager dbManager) {
        Connection connection = null;
        try {
            connection = dbManager.getConnection();
            System.out.println("\u001B[47m\u001B[30mNhập mã lớp học cần xoá (theo mã học viên): ");
            int maLopHoc = input.nextInt();
            System.out.println("\u001B[0m");
            input.nextLine(); // Đọc ký tự xuống dòng còn sót lại

            System.out.println("Bạn có chắc chắn không (Y/N): ");
            String check = input.nextLine();

            if (check.equalsIgnoreCase("Y")) {
                String sql = "DELETE FROM lop_hoc WHERE ma_lop_hoc = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, maLopHoc);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Xóa Class thành công!");
                } else {
                    System.out.println("Không tìm thấy Class có mã " + maLopHoc);
                }
            } else {
                System.out.println("Yêu cầu đã bị huỷ !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra xem mã lớp học có tồn tại hay không?
    private boolean isExists(Connection connection, int maLopHoc, int maHocVien) throws SQLException {
        // kiểm tra xem mã lớp học có tồn tại trong bảng lop_hoc hay không?
        String lopHocsql = "SELECT 1 FROM lop_hoc WHERE ma_lop_hoc = ?";
        PreparedStatement lopHocStatement = connection.prepareStatement(lopHocsql);
        lopHocStatement.setInt(1, maLopHoc);
        ResultSet lopHocResultSet = lopHocStatement.executeQuery();
        boolean maLopHocExists = lopHocResultSet.next();

        // kiểm tra xem mã học viên có tồn tại trong bảng học viên hay không?
        String hocViensql = "SELECT 1 FROM hoc_vien WHERE ma_hoc_vien = ?";
        PreparedStatement hocVienStatement = connection.prepareStatement(hocViensql);
        hocVienStatement.setInt(1, maHocVien);
        ResultSet hocVienResultSet = hocVienStatement.executeQuery();
        boolean maHocVienExists = hocVienResultSet.next();

        // Trả về true nếu cả hai mã tồn tại, ngược lại trả về false
        return maLopHocExists && maHocVienExists;
    }

    // CHỨC NĂNG THÊM HỌC VIÊN VÀO LỚP HỌC
    public void AddStudentToClass(DatabaseManager dbManager) {
        Connection connection;
        try {
            connection = dbManager.getConnection();
            // Dữ liệu học viên và lớp học
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Nhập mã lớp học: ");
            int maLopHoc = input.nextInt();
            System.out.println("Nhập mã học viên cần thêm: ");
            int maHocVien = input.nextInt();
            // Thêm học viên vào lớp học
            if (isExists(connection, maLopHoc, maHocVien)) {
                String sql = "INSERT INTO danh_sach_lop_hoc (ma_hoc_vien, ma_lop_hoc) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, maHocVien);
                preparedStatement.setInt(2, maLopHoc);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("\u001B[42mSuccess: Thêm học viên vào lớp học thành công.");
                    System.out.println("\u001B[0m");
                } else {
                    System.out.println("\u001B[41mError: Thêm học viên vào lớp học thất bại.");
                }
            } else {
                System.out.println("\u001B[41mError: Thêm học viên vào lớp học thất bại!!!");
                System.out.println("\u001B[0m");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CHỨC NĂNG HIỂN THỊ CÁC HỌC VIÊN CỦA LỚP HỌC
    public void DisplayDsHocVienOfClass(DatabaseManager dbManager) {
        Connection connection;
        try {
            connection = dbManager.getConnection();
            System.out.print("Nhập mã lớp học cần hiển thị danh sách học viên: ");
            int maLopHoc = input.nextInt();
            if (connection != null) {
                String sqlQuery = "SELECT hv.ma_hoc_vien, hv.ho_ten, lh.ma_lop_hoc " +
                        "FROM danh_sach_lop_hoc dslh " +
                        "INNER JOIN hoc_vien hv ON dslh.ma_hoc_vien = hv.ma_hoc_vien " +
                        "INNER JOIN lop_hoc lh ON dslh.ma_lop_hoc = lh.ma_lop_hoc " +
                        "WHERE lh.ma_lop_hoc = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                // Sử dụng preparedStatement.setInt(1, maLopHoc) để thêm giá trị maLopHoc vào truy vấn SQL.
                preparedStatement.setInt(1, maLopHoc);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("Danh sách lớp học với mã lớp học: " + maLopHoc);
                while (resultSet.next()) {
                    String maHocVien = resultSet.getString("ma_hoc_vien");
                    String hoTenHocVien = resultSet.getString("ho_ten");
                    System.out.println("Mã học viên: " + maHocVien);
                    System.out.println("Họ tên học viên: " + hoTenHocVien);
                    System.out.println();
                }
            }
            else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

