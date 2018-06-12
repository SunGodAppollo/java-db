package com.javaDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DB {
	protected static  Connection connection = null;
	protected static  Statement statement = null;
	protected static  ResultSet resultSet = null;
	protected static  PreparedStatement preparedStatement = null;

	protected  String table;

	protected DB(String table) {
		this.table = "`" + table + "`";
	}
	
	
	
	
	/**修改
	 * 
	 * @param set
	 * @param Where
	 * @return
	 */
	public int edit(String set,String Where) {
		// 组成SQL语句
		String sql = "UPDATE " + table;
	    sql += " SET " + set;
	    sql += " WHERE " + Where;
		return CURD(sql);
	}
	

	/**
	 * 对数据库的增删改操作
	 * @param SQL 需要执行的sql语句
	 * @return
	 */
	public int CURD(String SQL) {
		System.out.println(SQL);
		int return_int=0;
		statement = getStatement();
		try {
			return_int=statement.executeUpdate(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_int;
	}
	

	// sql查询
	public List<Map<String, String>> selectBySQL(String SQL) {
		System.out.println(SQL);
		// 查询操作
		Map<String, String> rowMap;
		List<Map<String, String>> lists = new ArrayList<>();
		statement = getStatement();
		try {
			resultSet = statement.executeQuery(SQL);
			resultSet.next();// 第一行位置
			ResultSetMetaData metaData = resultSet.getMetaData();
			int count = metaData.getColumnCount();
			String countName[] = new String[count];
			for (int index = 1; index <= count; index++) {
				countName[index - 1] = metaData.getColumnName(index);
			}
			resultSet.beforeFirst();
			while (resultSet.next()) {
				rowMap = new HashMap<>();
				for (String string : countName) {
					rowMap.put(string, resultSet.getString(string));
				}
				lists.add(rowMap);
			}

			closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return lists;

	}

	
	// 得到Statement
	protected Statement getStatement() {
		try {
			if (connection == null||connection.isClosed()) {
				connection = JdbcConnection.getConnection();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		  statement =  connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	// 得到PreparedStatement
	protected PreparedStatement getPreparedStatement(String SQL) {
		try {
			if (connection == null||connection.isClosed()) {
				connection = JdbcConnection.getConnection();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	// 关闭全部连接
	protected void closeAll() {
		try {
			resultSet.close();
			statement.close();
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
