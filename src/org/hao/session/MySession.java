package org.hao.session;

public interface MySession {

	public void setAttribute(String attrname, Object value);
	public Object getAttribute(String attrname);
	public void remove(String attrname);
	public void setActiveTime(double time);
	public double getActiveTime();
	public void initSession();
}
