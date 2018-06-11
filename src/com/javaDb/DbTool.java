package com.javaDb;

import java.util.List;
import java.util.Map;



/**
 * 方便DB操作的工具方法
 *
 */
public class DbTool {

	
	
	/**
	 * 打印查询到的结果集
	 * @param list
	 */
	public static void printRelt(List<Map<String, String>> list) {
		if (list==null) {
			throw new RuntimeException("输出内容不能为null");	
		}
		for (Map<String, String> map : list) {
			System.out.println(map);
		}
	}
}
