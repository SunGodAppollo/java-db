package com.dbInterface;

import java.util.List;
import java.util.Map;

public interface TableSelect {

	/**默认查询全部
	 * @return
	 */
	public List<Map<String, String>> select();
	/**根据Where语句查询
	 * @param Where
	 * @return
	 */
	public List<Map<String, String>> select(String Where);
	/**根据多条件Where语句查询
	 * @param Where
	 * @return
	 */
	public List<Map<String, String>> select(String[] Where);
	
}
