package base.test;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

    public class TestConnection {
        public static void main(String[] args) {

            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("trinhhao142"); // thiết lập user
            ds.setPassword("123");  // password
            ds.setServerName("LAPTOP-3CSTMP8\\SQLEXPRESS");
            ds.setPortNumber(1433); //port tcp/ip
            ds.setDatabaseName("Test"); //database name
            ds.setTrustServerCertificate(true); // Bật chế độ tin tưởng chứng chỉ tự ký
            try(Connection conn = ds.getConnection()){
                System.out.println("Connection success");
                System.out.println(conn.getMetaData());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }



