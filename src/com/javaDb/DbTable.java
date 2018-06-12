package com.javaDb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dbInterface.TablieInsert;
import com.dbInterface.TableDelete;
import com.dbInterface.TableSelect;
import com.dbInterface.TableUpdate;

public class DbTable extends DB implements TableSelect, TablieInsert, TableDelete,TableUpdate{

	protected DbTable(String table) {
		super(table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Map<String, String>> select() {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		return selectBySQL(sql);
	}

	@Override
	public List<Map<String, String>> select(String Where) {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		if (Where != null) {// 如果Where不为空添加Where
			sql += " where " + Where;
		}
		return selectBySQL(sql);
	}

	@Override
	public List<Map<String, String>> select(String[] Where) {
		// 组成SQL语句
		String sql = "select * FROM " + table;
		if (Where != null) {
			sql += " where ";
			for (String WhereItem : Where) {
				sql += WhereItem + " ";
				sql += "AND ";
			}
			// 除去最后一个"AND "
			sql = sql.substring(0, sql.length() - 4);
		}
		return selectBySQL(sql);
	}

	@Override
	public int insert(Map<String, String> insertMap) {
		// 组成SQL语句
		String sql = "INSERT INTO " + table;
		String attr = "(";// 存放要添加的字段
		String VALUES = "(";// 存放字段对应的值

		for (Entry<String, String> insert : insertMap.entrySet()) {
			attr += "`" + insert.getKey() + "`,";
			VALUES += "`" + insert.getValue() + "`,";
		}
		// 去除最后的逗号
		attr = attr.substring(0, attr.length() - 1);
		VALUES = VALUES.substring(0, VALUES.length() - 1);
		// 添加最后的括号
		attr += ")";
		VALUES += ")";
		// 组装成最后的sql
		sql += " " + attr + " VALUES" + VALUES;

		System.out.println(sql);
		return CURD(sql);
	}

	@Override
	public int delete(String Where) {
		// 组成SQL语句
		String sql = "delete FROM " + table;
		if (Where != null) {// 如果Where不为空添加Where
			sql += " where " + Where;
		}
		return CURD(sql);
	}

	@Override
	public int delete(String[] Where) {
		// 组成SQL语句
		String sql = "delete FROM " + table;
		if (Where != null) {
			sql += " where ";
			for (String WhereItem : Where) {
				sql += WhereItem + " ";
				sql += "AND ";
			}
			// 除去最后一个"AND "
			sql = sql.substring(0, sql.length() - 4);
		}
		return CURD(sql);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from " + table + "where id = " + id;
		return CURD(sql);
	}

	@Override
	public int update(Map<String, String> updateMap, String[] Where) {
		// 组成SQL语句
				String sql = "UPDATE " + table;
				String set="";

				for (Entry<String, String> insert : updateMap.entrySet()) {
					set += "`" + insert.getKey() + "`="+insert.getValue() + "`,";
				}
				
				// 去除最后的逗号
				set = set.substring(0, set.length() - 1);
				// 组合sql
				sql += " SET" + set;
				//组合where 
				if (Where != null) {
					sql += " where ";
					for (String WhereItem : Where) {
						sql += WhereItem + " ";
						sql += "AND ";
					}
					// 除去最后一个"AND "
					sql = sql.substring(0, sql.length() - 4);
				}
				return CURD(sql);
	}

}
