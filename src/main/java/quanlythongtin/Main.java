package quanlythongtin;
import quanlythongtin.LoginAdmin;
import java.util.Scanner;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.commons.lang3.StringUtils;


public class Main {
    public static void main(String[] args) throws SQLServerException {
        LoginAdmin loginAdmin = new LoginAdmin();

        // Tạo đối tượng để sử dụng nhập liệu.
        Scanner sc = new Scanner(System.in);
        // Thông tin kết nối cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Test";
        String username = "trinhhao142";
        String password = "123";
        LoginAdmin admin = new LoginAdmin();
        DatabaseManager dbManager = new DatabaseManager(url, username, password);

        // Khởi tạo vai trò ADMIN
        Student AdminHV = new Student();
        Classes AdminCL = new Classes();
        Coures AdminCO = new Coures();
        if( loginAdmin.authenticateUser(dbManager)) {
            while (true) {
                System.out.println("\n\n");
                String centeredText = StringUtils.center("QUẢN LÝ HỌC VIÊN", 80, '-');
                System.out.println(centeredText);
                System.out.println("\u001B[44mVui lòng lựa chọn -->\u001B[0m");
                System.out.println("+--------------------------------------------------------------------------------+");
                System.out.printf("%-3s  |  %-18s |%n", "1.", "\u2713 TRUY CẬP QUẢN LÝ HỌC VIÊN");
                System.out.println("+--------------------------------------------------------------------------------+");
                System.out.printf("%-3s  |  %-18s |%n", "2.", "\u2713 TRUY CẬP QUẢN LÝ LỚP HỌC");
                System.out.println("+--------------------------------------------------------------------------------+");
                System.out.printf("%-3s  |  %-18s |%n", "3.", "\u2713 TRUY CẬP QUẢN LÝ KHOÁ HỌC");
                System.out.println("+--------------------------------------------------------------------------------+");
                System.out.print("\u001B[42mNhập lựa chọn chính : ");
                System.out.print("\u001B[0m");
                int mainOption = sc.nextInt();

                switch (mainOption) {
                    case 1: {
                        String textOfoption1 = new String("1. Thêm học viên\n2. In danh sách học viên\n3. Sắp xếp danh sách học viên theo tên\n4. Xóa học viên theo ID\n5. Cập nhật thông tin học viên\n6. Tìm học viên theo tên \n7. Xuất thông tin học viên vào file \n8. Thoát");
                        String title1 = StringUtils.center("Đang truy cập chức năng quản lý học viên", 80, '-');
                        System.out.println(title1);
                        System.out.println(textOfoption1);
                        System.out.println("-----------------------");
                        System.out.print("\u001B[42mNhập lựa chọn:  ");
                        System.out.print("\u001B[0m");
                        int subOption = sc.nextInt();
                        switch (subOption) {
                            case 1: {
                                AdminHV.InputInfoStudent();
                                System.out.println("Thông tin sinh viên vừa nhập");
                                AdminHV.DisplayInfoStudent();
                                break;
                            }
                            case 2: {
                                AdminHV.DisplayDSStudent(dbManager);
                                break;
                            }
                            case 3: {
                                AdminHV.SortStudentByName(dbManager);
                                break;
                            }
                            case 4: {
                                AdminHV.DeleteStudent(dbManager);
                                break;
                            }
                            case 5: {
                                AdminHV.UpdateInfoStudent(dbManager);
                                break;
                            }
                            case 6: {
                                AdminHV.SearchStudentByName(dbManager);
                                break;
                            }
                            case 7: {
                                AdminHV.saveToTextFile(dbManager, "C:\\Users\\Admin\\IdeaProjects\\QuanLyHocVienJAVA\\data.txt");
                                break;
                            }
                            case 8: {
                                System.out.println("\u001B[43mThoát chức năng quản lý học viên\u001B[0m");
                                break;
                            }
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                        break;
                    }
                    case 2: {

                        String textOfoption2 = new String("1. Thêm lớp học\n2. Hiển thị danh sách lớp học\n3. Xoá lớp học\n4.Thêm học viên vào lớp học\n5. Truy cập danh sách học viên từng lớp học \n6. Thoát");
                        String title2 = StringUtils.center("Đang truy cập chức năng quản lý lớp học", 80, '-');
                        System.out.println(title2);
                        System.out.println(textOfoption2);
                        System.out.println("-----------------------");
                        System.out.print("Nhập lựa chọn-->");
                        int subOption = sc.nextInt();
                        switch (subOption) {
                            case 1: {
                                AdminCL.AddClasses(dbManager);
                                break;
                            }
                            case 2: {
                                AdminCL.DisplayDSClasses(dbManager);
                                break;
                            }
                            case 3: {
                                AdminCL.DeleteClasses(dbManager);
                                break;
                            }
                            case 4: {
                                AdminCL.AddStudentToClass(dbManager);
                                break;
                            }
                            case 5: {
                                AdminCL.DisplayDsHocVienOfClass(dbManager);
                                break;
                            }
                            case 6: {
                                System.out.println("\u001B[43mThoát chức năng quản lý lớp học \u001B[0m");
                                break;
                            }
                            default: {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                            }
                        }
                        break;
                    }
                    case 3: {
                        String textOfoption3 = new String("1. Thêm khóa học\n2. Hiển thị thông tin khóa học\n3. Xóa khóa học\n4. Truy cập danh sách khóa học\n5.Thoát");
                        String title3 = StringUtils.center("Đang truy cập chức năng quản lý khoá học", 80, '-');
                        System.out.println(title3);
                        System.out.println(textOfoption3);
                        System.out.println("-----------------------");
                        System.out.print("Nhập lựa chọn-->");
                        int subOption = sc.nextInt();
                        switch (subOption) {
                            case 1: {
                                AdminCO.AddCourses(dbManager);
                                break;
                            }
                            case 2: {
                                // Hiển thị thông tin khoá học theo tên
                                break;
                            }
                            case 3: {
                                AdminCO.DeleteCourses(dbManager);
                                break;
                            }
                            case 4: {
                                AdminCO.DisplayDSCourses(dbManager);
                                break;
                            }
                            case 5: {
                                System.out.println("\u001B[43mThoát chức năng quản lý khoá học! \u001B[0m");
                                break;
                            }
                            default: {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("\u001B[31;1mLựa chọn không hợp lệ !!! \u001B[0m");
                }


//        AdminHV.InputInfoStudent();
//        AdminHV.DisplayInfoStudent();
//        AdminHV.DisplayDSStudent(dbManager);
//        AdminHv.DeleteStudent(dbManager);
//        AdminHV.UpdateInfoStudent(dbManager);
//        AdminHV.SortStudentByName(dbManager);
//        AdminCL.AddClasses(dbManager);
//        AdminCL.DisplayDSClasses(dbManager);
//        AdminCL.DeleteClasses(dbManager);
//        admin.authenticateUser(dbManager);
//        AdminCL.AddStudentToClass(dbManager);
//        AdminCL.DisplayDsHocVienOfClass(dbManager);
            }
        }
        else {
            System.out.println("Thông báo: Không có quyền truy cập!!!");
        }
    }
}
