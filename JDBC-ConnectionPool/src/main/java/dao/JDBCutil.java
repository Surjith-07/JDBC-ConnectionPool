package dao;

import db.DbConnection;
import model.Student;

import java.sql.*;

public class JDBCutil {
    private static final String getAllquery = "select * from students";
    private static final String getIndividual = "select id,name,address from students where id = ?";
    private static final String createQuery = "insert into students values(default,?,?)";
    private static final String deletQuery = "delete from students where id = ?";
    public static final String updateQuery = "update students set name = ? , address= ? where id = ?";

    public static void getAllStudent() throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllquery)) {
            System.out.println("id\tname\taddress");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }
        }
    }

    public static void getStudentById(int id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getIndividual);) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println("id\tname\taddress");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public static void creatStudent(Student student) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createQuery);) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully inserted...!" : "Somthing wrong...!");
        }
    }

    public static void updateStudent(int id, String name, String address) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, id);
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully Updated...!" : "Somthing wrong...!");
        }
    }

    public static void deleteStudent(int id) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deletQuery);) {
            preparedStatement.setInt(1, id);
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully Deleted...!" : "Somthing wrong...!");
        }
    }
}
