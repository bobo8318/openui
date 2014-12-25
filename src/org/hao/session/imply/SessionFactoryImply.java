package org.hao.session.imply;

import java.util.HashMap;

import org.hao.session.MySession;
import org.hao.session.SessionFactory;

public class SessionFactoryImply implements SessionFactory {

	private static HashMap<String,MySession> sessionPool;
	@Override
	public MySession getSession(String sessionid) {
		// TODO Auto-generated method stub
		return sessionPool.get(sessionid);
	}

	@Override
	public void removeSession(String sessionid) {
		// TODO Auto-generated method stub
		sessionPool.remove(sessionid);
	}

	@Override
	public void validateSession(String sessionid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initSessionFactory() {
		// TODO Auto-generated method stub
		if(sessionPool == null)
			sessionPool = new HashMap<String,MySession>();
	}

	@Override
	public String newSession() {
		// TODO Auto-generated method stub
		MySession newSession = new HaoSessionImply();
		newSession.initSession();
		String sessionid = "";
		sessionPool.put(sessionid, newSession);
		return sessionid;
	}

}
