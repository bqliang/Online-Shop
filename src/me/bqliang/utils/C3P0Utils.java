package me.bqliang.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author bqliang
 */
public class C3P0Utils {
	//private static ComboPooledDataSource datasource = new ComboPooledDataSource();
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8";
	static final String USERNAME = "root";
	static final String PASSWORD = "mysql";
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

		static {
			try {
				cpds.setDriverClass(JDBC_DRIVER);
				cpds.setJdbcUrl(DB_URL);
				cpds.setUser(USERNAME);
				cpds.setPassword(PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	public static DataSource getDataSource(){
		return cpds;
	}
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			 con= cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
