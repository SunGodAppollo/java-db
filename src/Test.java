import java.util.List;
import java.util.Map;

import com.javaDb.DB;
import com.javaDb.DbTool;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            DB db=new DB("user");
            List<Map<String, String>> selectrelet = db.select();
            DbTool.printRelt(selectrelet);
	}

}
