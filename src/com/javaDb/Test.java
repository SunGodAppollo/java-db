package com.javaDb;

import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            DB db=new DB("users");
            List<Map<String, String>> selectrelt = db.select();
            DbTool.printRelt(selectrelt);
            DB db1=new DB("account");
            List<Map<String, String>> selectrelt1 = db1.select();
            DbTool.printRelt(selectrelt1);
	}

}
