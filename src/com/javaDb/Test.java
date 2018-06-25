package com.javaDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbInterface.TableDelete;
import com.dbInterface.TableInsert;
import com.dbInterface.TableSelect;
import com.dbInterface.TableUpdate;

public class Test {

	public static void main(String[] args) {
		
		
		/*TableInsert insert = new DBSql("users");
		Map<String,String> map = new HashMap<>();
		map.put("id", "1");
		map.put("user", "tang");
		map.put("password", "111");
		insert.insert(map);	*/
		
		
		/*TableSelect select = new DBSql("users");
		List<Map<String, String>> lists = select.select();
		System.out.println(lists.toString());*/
		
		/*TableUpdate update = new DBSql("users");
		Map<String,String> whereMap = new HashMap<>();
		Map<String,String> updateMap = new HashMap<>();
		updateMap.put("user","qwe");
		updateMap.put("password","777");
		whereMap.put("id","1");
		whereMap.put("qw","2");
		update.update(updateMap, whereMap);*/
		
		TableDelete delete = new DBSql("users") ;
		Map<String,String> whereMap = new HashMap<>();
		whereMap.put("id", "2");
		whereMap.put("user", "tang");
		delete.delete(whereMap);
	}

}
