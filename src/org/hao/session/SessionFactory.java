package org.hao.session;

public interface SessionFactory {

	/**
	 * sin 2014-09-30
	 * 获得指定的session
	 * @return MYSession 接口的一个实例
	 */
	public MySession getSession(String sessionid);
	/**
	 * sin 2014-09-30
	 * 删除指定session
	 * 
	 */
	public void removeSession(String sessionid);
	/**
	 * sin 2014-09-30
	 * 检测session的有效性
	 * 
	 */
	public void validateSession(String sessionid);
	public String newSession();
	public void initSessionFactory();
	
}
