package db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static BasicDataSource dataSource;
    private static final String url = "jdbc:mysql://localhost:3306/Myconnection";
    private static final String userName = "root";
    private static final String password = "surjith";

    public static void setDbConnection() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}


/*
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static DataSource dataSource;

    static {
        BasicDataSource ds = new BasicDataSource();

        ds.setUrl(JDBC_URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);

        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        ds.setMaxIdle(5);
        ds.setMinIdle(2);

        dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
*/