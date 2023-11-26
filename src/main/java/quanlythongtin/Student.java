package quanlythongtin;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Student {
    private int maHocVien;
    private String tenHocVien;
    private Date ngaySinh;
    private int soDienThoai;
    private String diaChi;
    private boolean gioiTinh;

    //Constructor không truyền tham số.(default constructor )
    public Student() {

    }

    // khởi tại hàm constructor với tham số
    public Student(int maHocVien, String tenHocVien, Date ngaySinh, int soDienThoai, String diaChi, boolean gioiTinh) {
        this.maHocVien = maHocVien;
        this.tenHocVien = tenHocVien;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;

    }

    //-----------Getter và Setter--------------------
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    // Scanner
    Scanner input = new Scanner(System.in);

    // Khởi tạo phương thức DisplayInfoStudent() theo mã học viên
    public void DisplayInfoStudent() {
        System.out.println("Họ và tên: " + getTenHocVien() + "\nMã học viên: " + getMaHocVien() + "\nNgày sinh: " + getNgaySinh() + "\nĐiện thoại: " + getSoDienThoai() + "\nĐịa chỉ: " + getDiaChi());

    }

    public void DisplayDSStudent(DatabaseManager dbManager) {
        System.out.println("--->Hiển thị danh sách");
        DatabaseManager.DisplayHocVienData(dbManager);
    }

    @Override
    public String toString() {
        return "Student{" +
                "maHocVien=" + maHocVien +
                ", tenHocVien='" + tenHocVien + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", soDienThoai=" + soDienThoai +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }

    // Khởi tạo phương thức InputInfoStudent();
    public void InputInfoStudent() {

        System.out.println("\t\tNHẬP THÔNG TIN HỌC VIÊN");
        System.out.println("Nhập mã học viên: ");
        maHocVien = input.nextInt();
        input.nextLine(); // Đọc ký tự xuống dòng còn sót lại
        System.out.println("Nhập tên học viên: ");
        tenHocVien = input.nextLine();
        System.out.println("Nhập địa chỉ học viên: ");
        diaChi = input.nextLine();
        System.out.println("Giới tính:");
        gioiTinh = input.nextBoolean();
        System.out.println("Nhập số điện thoại: ");
        soDienThoai = input.nextInt();
        input.nextLine(); // đọc ký tự xuống dòng còn sót lại
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        String date = input.nextLine();
        try {
            ngaySinh = dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Ngày sinh không hợp lệ!");
        }
        try {
            // kết nối đến cơ sở dữ liệu
            SQLServerDataSource ds = new SQLServerDataSource();
            // Thông tin kết nối cơ sở dữ liệu
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Test";
            String username = "trinhhao142";
            String password = "123";
            ds.setTrustServerCertificate(true); // Bật chế độ tin tưởng chứng chỉ tự ký
            ds.setURL(url);
            ds.setUser(username);
            ds.setPassword(password);
            // Kết nối tới cơ sở dữ liệu
            SQLServerConnection connection = (SQLServerConnection) ds.getConnection();

            // Tạo truy vấn SQL để chèn dữ liệu
            String sql = "INSERT INTO hoc_vien (ma_hoc_vien, ho_ten, ngay_sinh, so_dien_thoai,dia_chi,gioi_tinh) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Đặt các tham số cho truy vấn
            statement.setInt(1, maHocVien);
            statement.setString(2, tenHocVien);
            statement.setDate(3, new java.sql.Date(ngaySinh.getTime())); //// Chuyển đổi ngày sinh thành java.sql.Date
            statement.setInt(4, soDienThoai);
            statement.setString(5, diaChi);
            statement.setBoolean(6, gioiTinh);

            // Thực hiện truy vấn chèn dữ liệu
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\u001B[42m\nSuccess! Thêm học viên thành công");
                System.out.println("\u001B[0m");

            } else {
                System.out.println("\u001B[31mError! Thêm học viên không thành công");
                System.out.println("\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Phương thức xoá thông tin học viên theo mã học viên.
    public void DeleteStudent(DatabaseManager dbManager) {
        Connection connection;
        try {
            connection = dbManager.getConnection();
            System.out.println("\u001B[47m\u001B[30mNhập học viên cần xoá (theo mã học viên): ");
            maHocVien = input.nextInt();
            System.out.println("\u001B[0m");
            input.nextLine(); // Đọc ký tự xuống dòng còn sót lại
            // Kết nối đến cơ sở dữ liệu
            // xóa dữ liệu (DELETE) trên bảng "hoc_vien"
            String sql = "DELETE FROM hoc_vien WHERE ma_hoc_vien = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maHocVien);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa học viên thành công!");
            } else {
                System.out.println("Không tìm thấy học viên có mã " + maHocVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UpdateInfoStudent(DatabaseManager dbManager) {
        Connection connection;
        ResultSet resultSet;
        try {
            connection = dbManager.getConnection();
            String sqlQuery = "SELECT * FROM hoc_vien WHERE ma_hoc_vien = ?";
            String updateQuery = "UPDATE hoc_vien SET ho_ten = ?, ngay_sinh = ?, gioi_tinh=?, so_dien_thoai=?, dia_chi=? WHERE ma_hoc_vien = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            System.out.println("Nhập mã học viên của học viên cần cập nhật thông tin: ");
            int targetId = input.nextInt();
            input.nextLine();
            // Thiết lập tham số trong truy vấn
            preparedStatement.setInt(1, targetId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Học viên tồn tại, cho phép người dùng nhập thông tin
                System.out.println("Thông báo: Học viên đã được tim thấy !");
                System.out.println("Nhập tên học viên mới: ");
                String newNameHV = input.nextLine();
                System.out.println("Nhập số điêện thoại mới: ");
                int newPhone = input.nextInt();
                input.nextLine();
                System.out.println("Nhập địa chỉ mơới: ");
                String newAddress = input.nextLine();
                System.out.println("Giới tính:");
                Boolean newgioiTinh = input.nextBoolean();
                input.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                String newdate = input.nextLine();
                try {
                    ngaySinh = dateFormat.parse(newdate);
                } catch (Exception e) {
                    System.out.println("Ngày sinh không hợp lệ!");
                }
                // Thiết lập tham số cho truy vấn cập nhật
                updateStatement.setString(1, newNameHV);
                updateStatement.setDate(2, new java.sql.Date(ngaySinh.getTime()));
                updateStatement.setBoolean(3, newgioiTinh);
                updateStatement.setInt(4, newPhone);
                updateStatement.setString(5, newAddress);
                updateStatement.setInt(6, targetId);
                // Thực hiện truy vấn cập nhật
                int updatedRows = updateStatement.executeUpdate();
                if (updatedRows > 0) {
                    System.out.println("\u001B[42mSuccess! Thông tin học viên đã được cập nhật thành công.\u001B[0m");
                } else {
                    System.out.println("\u001B[31mError! Không có bản ghi nào được cập nhật.");
                }
            } else {
                System.out.println("\u001B[31mError! Mã học viên không tồn tại trong cơ sở dữ liệu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String str1, String str2) {
            String[] words1 = str1.split(" ");
            String[] words2 = str2.split(" ");
            String lastWord1 = words1[words1.length - 1];
            String lastWord2 = words2[words2.length - 1];
            return lastWord1.compareTo(lastWord2);
        }
    };
    // Chức năng sắp xếp học vieên theo tên
    public void SortStudentByName(DatabaseManager dbManager) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = dbManager.getConnection();
            String sql = "SELECT ho_ten FROM hoc_vien";
            PreparedStatement statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            ArrayList<String> wordArray = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("ho_ten");
                wordArray.add(name);
            }
            Collections.sort(wordArray, comparator);

            System.out.println("\u001B[42mDanh sách học viên sau khi sắp xếp:");
            for (String str : wordArray) {
                System.out.println(str);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông tin lỗi
            throw new RuntimeException("\u001B[31mError sorting students by name.", e);
        } finally {
            // Đảm bảo rằng tài nguyên được đóng đúng cách
            try {
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // In ra thông tin lỗi
                throw new RuntimeException("\u001B[31mError closing resources.", e);
            }
        }
    }
    // Chức năng tìm kiếm học viên theo mã học viên
    public void SearchStudentByName(DatabaseManager dbManager) {
        Connection connection;
        ResultSet resultSet;
        try {
            connection = dbManager.getConnection();
            String sqlQuery = "SELECT * FROM hoc_vien WHERE ho_ten = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            System.out.println("Nhập tên học viên cần tìm kiếm: ");
            String targetName = input.nextLine();
            // Thiết lập tham số trong truy vấn
            preparedStatement.setString(1, targetName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    // Đọc thông tin học viên từ resultSet.getString và hiển thị kết quả
                    int maHV = resultSet.getInt("ma_hoc_vien");
                    String tenHV = resultSet.getString("ho_ten");
                    String ngaySinh = resultSet.getString("ngay_sinh");
                    boolean boolGioiTinh = resultSet.getBoolean("gioi_tinh");
                    String gioiTinh = boolGioiTinh ? "Nam" : "Nữ";
                    String soDienthoai = resultSet.getString("so_dien_thoai");
                    String diaChi = resultSet.getString("dia_chi");

                    // In thông tin học viên
                    System.out.println("\u001B[30m\u001B[42mSuccess!\u001B[0m");
                    System.out.println("Mã học viên: " + maHV);
                    System.out.println("Tên học viên: " + tenHV);
                    System.out.println("Ngày sinh: " + ngaySinh);
                    System.out.println("Giới tính: " + gioiTinh);
                    System.out.println("Số điện thoại: " + soDienthoai);
                    System.out.println("Địa chỉ: " + diaChi);
                } while (resultSet.next());
            } else {
                // Không có kết quả nào
                System.out.println("\u001B[30m\u001B[41mThông báo: Không có kết quả nào cho học viên có tên: " + targetName + "\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Chức năng in danh sách học viên ra file.
    public void saveToTextFile(DatabaseManager dbManager, String filePath) {
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(filePath)))) {
            // Thực hiện kết nối đến cơ sở dữ liệu
            Connection connection;
            connection = dbManager.getConnection();
            // Tạo đối tượng Statement để thực thi câu truy vấn
            Statement statement = connection.createStatement();
            // Thực hiện câu truy vấn SQL và nhận kết quả
            String sql = "SELECT * FROM hoc_vien";
            // Thực thi truy vấn và nhận kết quả
            ResultSet resultSet = statement.executeQuery(sql);
            writer.write("----------------------------------------------------------------------------\n");
            writer.write("|   ID   |       Name       |   Ngày sinh   | Số điện thoại  |   Địa chỉ   |   \n");
            writer.write("----------------------------------------------------------------------------\n");

            while (resultSet.next()) {
                String id = resultSet.getString("ma_hoc_vien");
                String name = resultSet.getString("ho_ten");
                String age = resultSet.getString("ngay_sinh");
                String phone = resultSet.getString("so_dien_thoai");
                String address = resultSet.getString("dia_chi");
                // Ghi dữ liệu vào file
                writer.write(String.format("|   %3s  |  %-15s |   %7s  |   %8s   |   %7s   |   \n", id, name, age, phone, address));

                // Dòng kết thúc
                writer.write("------------------------------------------------------------------------\n");

            }
            System.out.println("\n\u001B[30m\u001B[42mSuccess:Dữ liệu đã được xuất ra file thành công!\u001B[0m");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

}