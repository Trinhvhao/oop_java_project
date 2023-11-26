package quanlythongtin;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdmin {
    Scanner input = new Scanner(System.in);

    public boolean authenticateUser(DatabaseManager dbManager) {
        // Kết nối cơ sở dữ liệu và kiểm tra thông tin đăng nhập
        String centeredText = StringUtils.center("LOGIN ADMIN", 80, '-');
        System.out.println(centeredText);
        Connection connection;
        System.out.println("\u001B[45mNhập tên tài khoản: \u001B[0m");
        String username = input.nextLine();
        System.out.println("------------------------------");
        System.out.println("\u001B[45mNhập password: \u001B[0m");
        String password = input.nextLine();
        try {
            connection = dbManager.getConnection();
            String sql = "SELECT * FROM login WHERE admin = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // Đăng nhập thành công
                System.out.println("\u001B[42mSuccess: Đăng nhập thành công!\u001B[0m");
                return true;
            } else {
                System.out.println("\u001B[31mThông báo: Đăng nhập thất bại !!!\u001B[0m");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

