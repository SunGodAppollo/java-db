package com.javaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	private static Connection connection = null;
	private static final String URL = "jdbc:mysql://localhost:3306/project";// 连接参数
	private static final String USER = "root";// 用户名
	private static final String PASSWORD = "566584";// 密码

	static {
		if (connection == null) {
			// 1.加载驱动程序
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// 2. 获得数据库连接
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.err.println(e.getMessage());
			} catch (SQLException e) {
			   throw new RuntimeException("数据库连接失败");
			}
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
