package com.dbInterface;

import java.util.Map;

public interface TableDelete {

	/**根据主键id删除
	 * @param id
	 * @return
	 */
	public int delete(int id);
	/**根据Where语句删除
	 * @param Where
	 * @return
	 */
	public int delete(String Where);
	/**根据多条件where语句删除
	 * @param Where
	 * @return
	 */
	public int delete(Map<String, String> whereMap);
}
