package base.test;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class MenuExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String centeredText = StringUtils.center("MENU", 60, '-');
        System.out.println(centeredText);
        System.out.println("\u001B[44mVui lòng lựa chọn -->\u001B[0m");

        String menuOptions = "1. Truy cập Học Viên\n2. Truy cập Lớp Học\n3. Truy cập Khóa Học";
        System.out.println(menuOptions);
        System.out.println("Choose: ");
        while (true) {
            int option = sc.nextInt();
            switch (option) {
                case 1: {
                    printSubMenu1("Học Viên");
                    break;
                }
                case 2: {
                    printSubMenu2("Lớp Học");
                    break;
                }
                case 3: {
                    printSubMenu3("Khóa Học");
                    break;
                }
                default:
                    System.out.println("\u001B[31;1mLựa chọn không hợp lệ !!!\u001B[0m");
                    System.out.println("Nhập lại lựa chọn: ");
                    break;
            }
        }
    }

    private static void printSubMenu1(String subMenuTitle) {
        System.out.println(StringUtils.center(subMenuTitle, 60, '-'));
        String subMenuOptions = "1. Thêm học viên\n2. Xem danh sách\n3. Sắp xếp theo tên\n4. Hiển thị thông tin\n5. Xóa theo ID\n6. Cập nhật thông tin";
        System.out.println(subMenuOptions);
    }
    private static void printSubMenu2(String subMenuTitle) {
        System.out.println(StringUtils.center(subMenuTitle, 60, '-'));
        String subMenuOptions = "1. Thêm lớp học\n2. Xem danh sách\n3. Sắp xếp theo tên\n4. Hiển thị thông tin\n5. Xóa theo ID\n6. Cập nhật thông tin";
        System.out.println(subMenuOptions);
    }
    private static void printSubMenu3(String subMenuTitle) {
        System.out.println(StringUtils.center(subMenuTitle, 60, '-'));
        String subMenuOptions = "1. Thêm khoá học\n2. Xem danh sách\n3. Sắp xếp theo tên\n4. Hiển thị thông tin\n5. Xóa theo ID\n6. Cập nhật thông tin";
        System.out.println(subMenuOptions);
    }
}

