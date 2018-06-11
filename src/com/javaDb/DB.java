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
	// 条件查询
	public List<Map<String, String>> select() {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		System.out.println(sql);
		return selectBySQL(sql);
	}
	// 条件查询
	public List<Map<String, String>> select(String Where) {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		if (Where != null) {
		sql += " where " + Where;
		}
		System.out.println(sql);
		return selectBySQL(sql);
	}

	// 条件查询
	public List<Map<String, String>> select(String[] Where) {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		if (Where != null) {
			sql += " where ";
			for (int i = 0; i < Where.length; i++) {
				sql += Where[i] + " ";
				if (i < Where.length - 1) {
					sql += "AND ";
				}
			}
		}
		System.out.println(sql);
		return selectBySQL(sql);
	}

	/**
	 * 添加
	 * 样例
	 *  DB db=new DB("user");
	 *  Map<String, String> insertmap=new HashMap<>();
            insertmap.put("A", "1");
            insertmap.put("B", "2");
            insertmap.put("C", "3");
            insertmap.put("D", "4");
         db.add(insertmap);
	 * @param data
	 * @return int 添加影响的行数
	 */
	public int add(Map<String, String> insertMap) {
		// 组成SQL语句
		String sql = "INSERT INTO " + table;
		String attr="(";//存放要添加的字段
		String VALUES="(";//存放字段对应的值
		
		for (Entry<String, String> insert : insertMap.entrySet()) {
			attr+="`"+insert.getKey()+"`,";
			VALUES+="`"+insert.getValue()+"`,";
		}
		//去除最后的逗号
		attr=attr.substring(0,attr.length()-1);
		VALUES=VALUES.substring(0,VALUES.length()-1);
		//添加最后的括号
		attr+=")";
		VALUES+=")";
		//组装成最后的sql
		sql+=" "+attr+" VALUES"+VALUES;
		
		System.out.println(sql);
	  return CURD(sql);
	}
	//删除
	
	public int delete(int no){
		String sql = "delete from " + table +"where no = "+no;
		return CURD(sql);
	}
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
		System.out.println();
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
	
	//CURD
	public int CURD0(String SQL) {
		System.out.println();
		int return_int=0;
		preparedStatement=getPreparedStatement(SQL);
	    try {
	    	preparedStatement.execute();
	    	ResultSet rs = preparedStatement.getGeneratedKeys();
	    	 if (rs.next()) {  
	    		 return_int = (int) rs.getLong(1);   
	            }  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_int;
	}

	// sql查询
	public List<Map<String, String>> selectBySQL(String SQL) {
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
	//sql删除
	public String deletebyno() {
		
		
		return "";
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
