package model.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.sql.Statement;

public class DBUtil {
    private static String DB_URL = "jdbc:mysql://localhost:3306/cnw_cv_project";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    static private DBUtil _instance;
    static public DBUtil getInstance() {
        if(_instance == null)
            _instance = new DBUtil();
        return _instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        System.out.println("Making connection to: " + DB_URL);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        System.out.println("Connect successfully");
        return connection;
    }

    public Optional<ResultSet> execute(Connection connection, String query) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Executing: " + query);
            boolean resultType = statement.execute(query);
            if(resultType)
                return Optional.of(statement.getResultSet());
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<ResultSet> execute(Connection connection, Query<?> query) throws Exception {
        return execute(connection, query.makeQueryString());
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
