package org.hao.session.imply;

import java.util.HashMap;

import org.hao.session.MySession;

public class HaoSessionImply implements MySession {

	private static double activeTime = 0;
	private static HashMap<String,Object> valuePool;
	
	@Override
	public Object getAttribute(String attrname) {
		// TODO Auto-generated method stub
		return valuePool.get(attrname);
	}

	@Override
	public void remove(String attrname) {
		// TODO Auto-generated method stub
		valuePool.remove(attrname);
	}

	@Override
	public void setAttribute(String attrname, Object value) {
		// TODO Auto-generated method stub
		valuePool.put(attrname,value);
	}

	@Override
	public double getActiveTime() {
		// TODO Auto-generated method stub
		return activeTime;
	}

	@Override
	public void setActiveTime(double time) {
		// TODO Auto-generated method stub
		this.activeTime = time;
	}

	@Override
	public void initSession() {
		// TODO Auto-generated method stub
		if(valuePool==null)
			valuePool = new HashMap<String,Object>();
	}

}
