package com.javaDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dbInterface.TableDelete;
import com.dbInterface.TableInsert;
import com.dbInterface.TableSelect;
import com.dbInterface.TableUpdate;

public class DBSql implements TableDelete, TableSelect, TableInsert, TableUpdate {

	private PreparedStatement pst = null;
	private ResultSet resultSet = null;
	private String table = null;
	public DBSql(String table) {
		super();
		this.table = table;
	}

	@Override
	//多字段修改
	public int update(Map<String, String> updateMap,Map<String, String> whereMap) {
		
		int count = 0;
		String[] values = new String[updateMap.size()+whereMap.size()];
		String sql = "update "+ table +" set ";		
		String set = "";
		for (Entry<String, String> update : updateMap.entrySet()) {
			set += update.getKey()+" = ?,";
			values[count] = update.getValue();
			count++;
		}
		set = set.substring(0, set.length() - 1);
		sql+=set;
		sql+=" where ";
		String get = "";
		for (Entry<String, String> where : whereMap.entrySet()) {
			get += where.getKey()+" = ? and ";
			values[count] = where.getValue();
			count++;
		}
		get = get.substring(0, get.length() - 4);		
		sql+=get;
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			for(int index = 0;index<values.length;index++) {
				pst.setString(index+1, values[index]);
			}
			//pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//多字段添加
	@Override	
	public int insert(Map<String, String> insertMap) {
		
		int count = 0;
		String[] values = new String[insertMap.size()];
		String sql = "insert into "+ table+"(";		
		String set = "";
		String val = "";
		for (Entry<String, String> update : insertMap.entrySet()) {
			set += update.getKey()+",";
			val += "?,";
			values[count] = update.getValue();
			count++;
		}
		set = set.substring(0, set.length() - 1);
		val = val.substring(0, val.length() - 1);
		sql+=set;
		sql+=") values(";
		sql+=val;
		sql+=")";
		System.out.println(sql);
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			for(int index = 0;index<values.length;index++) {
				pst.setString(index+1, values[index]);
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//全部查询
	@Override
	public List<Map<String, String>> select() {
		
		String sql = "select * from "+table;
		List<Map<String, String>> lists = new ArrayList<>();
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			resultSet = pst.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int count = metaData.getColumnCount();
			String countName[] = new String[count];
			for (int index = 1; index <= count; index++) {
				countName[index - 1] = metaData.getColumnName(index);
			}
			while (resultSet.next()) {
				Map<String, String> listMap = new HashMap<>();
				for (String string : countName) {					
					listMap.put(string, resultSet.getString(string));
				}
				lists.add(listMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return lists;
	}

	@Override
	public List<Map<String, String>> select(String Where) {

		String sql = "select * from "+table+" where id = ?";
		List<Map<String, String>> lists = new ArrayList<>();
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			resultSet = pst.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int count = metaData.getColumnCount();
			String countName[] = new String[count];
			for (int index = 1; index <= count; index++) {
				countName[index - 1] = metaData.getColumnName(index);
			}
			while (resultSet.next()) {
				Map<String, String> listMap = new HashMap<>();
				for (String string : countName) {					
					listMap.put(string, resultSet.getString(string));
				}
				lists.add(listMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return lists;
	}

	@Override
	public List<Map<String, String>> select(String[] Where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String Where) {
		
		String sql = "delete from "+table+" where id = ?";
		int result = 0;
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			pst.setString(1, Where);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//删除
	@Override
	public int delete(Map<String, String> whereMap) {
		
		int count = 0;
		String sql = "delete from "+table+" where ";
		String set = "";
		String[] values = new String[whereMap.size()];
		for (Entry<String, String> update : whereMap.entrySet()) {
			set += update.getKey()+" = ? and ";
			values[count] = update.getValue();
			count++;
		}
		set = set.substring(0, set.length() - 4);
		sql+=set;
		System.out.println(sql);
		
		try {
			pst = DBUtils.connection.prepareStatement(sql);
			for(int index = 0;index<values.length;index++) {
				pst.setString(index+1, values[index]);
			}
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
