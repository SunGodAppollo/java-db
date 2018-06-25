package com.javaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	static Connection connection = null;
	private static final String URL = "jdbc:mysql://localhost:3306/project";// 连接参数
	private static final String USER = "root";// 用户名
	private static final String PASSWORD = "566584";// 密码

	
	static {		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获得数据库连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
