package com.javaDb;

import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            DB db=new DB("user");
            List<Map<String, String>> selectrelt = db.select();
            DbTool.printRelt(selectrelt);
	}

}
