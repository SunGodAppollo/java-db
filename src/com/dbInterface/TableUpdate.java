package com.dbInterface;

import java.util.Map;

public interface TableUpdate {
	public int update(Map<String, String> updateMap,String[] Where);
}
