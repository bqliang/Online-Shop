package me.bqliang.test;

import java.sql.*;

/**
 * @author bqliang
 */
public class JDBC {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8";
    static final String USERNAME = "root";
    static final String PASSWORD = "mysql";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        // 打开链接
        Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT name, sex, email, telephone FROM user;");
        while (rs.next()){
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String email = rs.getString("email");
            String telephone = rs.getString("telephone");
            System.out.println(name + "  " + sex + "  " + telephone + "  " + email);
        }
    }

}
