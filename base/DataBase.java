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
        try {//��������
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("�������ݿ�����ʧ��");
            e.printStackTrace();
        }
        try {//�������ݿ�
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            System.out.println("���ݿ�������");
        } catch (SQLException e) {
            System.out.println("���ݿ�����ʧ��");
            e.printStackTrace();
        }
    }

    public ResultSet query (String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public int update(String sql) throws SQLException {//������Ӱ�������
        return statement.executeUpdate(sql);
    }

    public void close () throws SQLException {
        statement.close();
        connection.close();
    }

}
