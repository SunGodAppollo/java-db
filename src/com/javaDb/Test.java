package com.javaDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbInterface.TableSelect;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          TableSelect select=new DbTable("user");
          String[] where= {"id=1","type=0"};
          List<Map<String, String>> select2 = select.select(where);
          DbTool.printRelt(select2);  
	}

}
