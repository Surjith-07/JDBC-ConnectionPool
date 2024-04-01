package main;

import dao.JDBCutil;
import db.DbConnection;
import model.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static {
        try {
            DbConnection.setDbConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int id;
        String name;
        String address;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 To View All Students...");
            System.out.println("2 To View Student By Id...");
            System.out.println("3 To Create Student...");
            System.out.println("4 To Update Student...");
            System.out.println("5 To Delete Student");
            System.out.println("6 To Abort...!!!\n");
            int value = scanner.nextInt();
            try {
                switch (value) {
                    case 1:
                        JDBCutil.getAllStudent();
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.println("Enter the Student ID: ");
                        id = scanner.nextInt();
                        JDBCutil.getStudentById(id);
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("Enter the Student Name :");
                        name = scanner.next();
                        System.out.println("Enter the address of Student :");
                        address = scanner.next();
                        Student student = new Student(name, address);
                        JDBCutil.creatStudent(student);
                        System.out.println("\n");
                        break;
                    case 4:
                        System.out.println("Enter the Student ID to Update :");
                        id = scanner.nextInt();
                        System.out.println("Enter the Student Name:");
                        name = scanner.next();
                        System.out.println("Enter the Student address:");
                        address = scanner.next();
                        JDBCutil.updateStudent(id, name, address);
                        System.out.println("\n");
                        break;
                    case 5:
                        System.out.println("Enter the Student ID to delete :");
                        id = scanner.nextInt();
                        JDBCutil.deleteStudent(id);
                        System.out.println("\n");
                        break;
                    case 6:
                        return;

                    default:
                        System.out.println("Enter the correct Option!!!!!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
