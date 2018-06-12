package com.dbInterface;

import java.util.Map;

public interface TablieInsert {
	/**
	 * 添加
	 * @param data
	 * @return int 添加影响的行数
	 */
	public int insert(Map<String, String> insertMap);
}
