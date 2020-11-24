package base;

import java.sql.*;

public class DataBase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/java_design?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "root";
    Connection connection;
    Statement statement;


    public DataBase () {
        try {//加载驱动
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("载入数据库驱动失败");
            e.printStackTrace();
        }
        try {//连接数据库
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            System.out.println("数据库已连接");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    public ResultSet query (String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public int update(String sql) throws SQLException {//返回受影响的行数
        return statement.executeUpdate(sql);
    }

    public void close () throws SQLException {
        statement.close();
        connection.close();
    }

}
