package me.bqliang.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class C3P0 {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8";
    static final String USERNAME = "root";
    static final String PASSWORD = "mysql";

    public static void main(String[] args) throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(JDBC_DRIVER);
        cpds.setJdbcUrl(DB_URL);
        cpds.setUser(USERNAME);
        cpds.setPassword(PASSWORD);
        Connection connection = cpds.getConnection();
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
