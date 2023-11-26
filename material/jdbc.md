# JDBC(Java Database Connectivity) 
-là một API(Application Programming Interface) trong ngôn ngữ lập trinhf Java được sử dụng để tương tác với cơ sở dữ liệu
-JDBC cho phép bạn kết nối với cơ sở dữ liệu, thực hiện truy vấn SQL, cập nhật dữ liệu và quản lý kết nối cơ sở dữ liệu. 
-Nó cung cấp một cách tiêu chuẩn để truy cập các cơ sở dữ liệu quan hệ như MySQL, SQL Server

## Dưới đây, tôi sẽ mở rộng về các khái niệm và thành phần chính của JDBC:

- Driver JDBC: JDBC sử dụng driver để kết nối với cơ sở dữ liệu. Driver là một phần mềm cụ thể cho từng loại cơ sở dữ liệu, cung cấp cách giao tiếp với cơ sở dữ liệu đó. Có bốn loại driver chính:
- JDBC-ODBC Bridge Driver: Sử dụng ODBC (Open Database Connectivity) để kết nối đến cơ sở dữ liệu thông qua giao diện ODBC. Tuy nhiên, driver này không còn được khuyến nghị sử dụng và thường chỉ được sử dụng cho mục đích thử nghiệm.
- Native-API Driver: Sử dụng thư viện và API cơ sở dữ liệu native của từng hệ thống quản lý cơ sở dữ liệu cụ thể. Driver này nhanh hơn so với JDBC-ODBC Bridge, nhưng yêu cầu sự cài đặt trên máy chủ cơ sở dữ liệu.
- Network Protocol Driver: Sử dụng giao thức mạng để kết nối với cơ sở dữ liệu. Driver này được sử dụng trong môi trường mạng và yêu cầu cài đặt trên máy chủ cơ sở dữ liệu.
- Thin Driver: Driver này giao tiếp trực tiếp với cơ sở dữ liệu thông qua giao thức mạng, mà không yêu cầu cài đặt trên máy chủ cơ sở dữ liệu. Nó là lựa chọn phổ biến và linh hoạt cho nhiều loại cơ sở dữ liệu.
- Connection (Kết nối): Đối tượng Connection đại diện cho kết nối đến cơ sở dữ liệu. Nó được sử dụng để thiết lập kết nối, quản lý giao tiếp với cơ sở dữ liệu và đóng kết nối sau khi hoàn thành công việc. Connection chứa thông tin về địa chỉ cơ sở dữ liệu, thông tin đăng nhập và các thuộc tính khác của kết nối.
- Statement (Lệnh): Đối tượng Statement được sử dụng để thực hiện truy vấn SQL đến cơ sở dữ liệu. Có ba loại Statement:
- Statement: Sử dụng cho các truy vấn tĩnh không chứa tham số.
- PreparedStatement: Sử dụng cho các truy vấn có tham số, giúp tránh tình trạng SQL injection và tối ưu hóa hiệu suất.
- CallableStatement: Sử dụng cho các truy vấn gọi thủ tục lưu trữ.
- ResultSet (Kết quả truy vấn): Đối tượng ResultSet chứa kết quả truy vấn từ cơ sở dữ liệu. Nó cho phép bạn lặp qua các bản ghi kết quả, lấy dữ liệu và xử lý nó.
- DriverManager (Quản lý driver): Lớp DriverManager được sử dụng để quản lý các driver kết nối đến cơ sở dữ liệu. Nó cho phép bạn đăng ký driver và tạo kết nối đến cơ sở dữ liệu.
- Exceptions (Ngoại lệ): JDBC định nghĩa nhiều loại ngoại lệ liên quan đến lỗi trong quá trình kết nối và thực hiện truy vấn. Bạn cần xử lý các ngoại lệ này để xử lý các tình huống lỗi.
- JDBC cung cấp một cách tiêu chuẩn và mạnh mẽ để làm việc với cơ sở dữ liệu trong ứng dụng Java, cho phép bạn tương tác với nhiều loại cơ sở dữ liệu một cách linh hoạt.

## Statement trong JDBC(Java Database Connectivity)
- Là một giao diện Java được sử dụng để thực hiện các truy vấn SQL đến cơ sở dữ liệu. Statement cung cấp các phương thức để thực hiện truy vấn đọc (SELECT) và ghi (INSERT, UPDATE, DELETE) dữ liệu.
- Nó là một trong các cách cơ bản để tương tác với cơ sở dữ liệu trong Java.
- Một số điểm quan trọng về Statement:
+ Không an toàn về SQL Injection: Sử dụng Statement để thực hiện truy vấn SQL có thể làm cho ứng dụng dễ bị tấn công SQL Injection nếu không kiểm tra và làm sạch các giá trị đầu vào một cách cẩn thận. Bạn phải tự quản lý việc ánh xạ giá trị vào truy vấn SQL.
+ Có thể dùng cho các truy vấn không có tham số: Statement thường được sử dụng cho các truy vấn mà không có tham số hoặc giá trị đầu vào động, ví dụ: "SELECT * FROM students". Truy vấn này không cần tham số đầu vào.
+ Thiết kế đơn giản: Statement là một cách đơn giản để thực hiện các truy vấn SQL và thường được sử dụng trong các tình huống đơn giản khi không cần quá nhiều tính năng mạnh mẽ của PreparedStatement.

## PreparedStatement 
- Là một giao diện JDBC(Java DataBase Connectivity) được sử dung để thực hiện các truy vấn SQl đối với cơ sở dữ liệu
- PreparedStatement được thiết kế cho việc thực hiện các truy  vấn SQL an toàn hơn và hiệu quả hơn bằng cách sử dụng tham số thay vì nội dung truy vấn được cung cấp trực tiếp. Điều này giúp chặn cuộc tấn công SQL Ịnection và tối ưu ho truy vấn SQL
- Các đặc điểm chính của PreparedStatement bao gồm:
+ Sử dụng tham số: Trong một PreparedStatement, bạn có thể xác định các tham số (placeholders) trong truy vấn SQL bằng cách sử dụng dấu chấm hỏi (?) hoặc tên tham số (đối với một số loại cơ sở dữ liệu)
+ Sau đó, bạn có thể gán giá trị cho các tham số này trước khi thực hiện truy vấn.
+ An toàn về SQL Injection: PreparedStatement tự động kiểm tra và làm sạch giá trị được gán cho các tham số, ngăn chặn cuộc tấn công SQL Injection. Điều này đảm bảo rằng dữ liệu nhập từ người dùng không thể thực hiện các truy vấn độc hại đến cơ sở dữ liệu.
+ Caching và hiệu suất: PreparedStatement có thể được biên dịch trước và tối ưu hóa lại sử dụng nhiều lần. Điều này cải thiện hiệu suất cho các truy vấn mà bạn thực hiện nhiều lần với các giá trị tham số khác nhau.
+ Loại truy vấn đa dạng: Bạn có thể sử dụng PreparedStatement để thực hiện các loại truy vấn khác nhau, bao gồm SELECT, INSERT, UPDATE và DELETE.