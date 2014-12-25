package org.hao.common;

import java.util.HashMap;

public class LocalSession {

	public static HashMap LOCAL_SESSION = new HashMap();

	public static HashMap getLOCAL_SESSION() {
		return LOCAL_SESSION;
	}
	public static void setAttribute(String attrname, Object value){
		//System.out.println("add data:"+attrname);
		LOCAL_SESSION.put(attrname, value);
		//System.out.println("LOCAL_SESSION size:"+LOCAL_SESSION.size());
		
	}
	public static Object getAttribute(String attrname){
		//System.out.println("get data:"+attrname);
		//System.out.println("LOCAL_SESSION size:"+LOCAL_SESSION.size());
		return LOCAL_SESSION.get(attrname);
	}
	public static void remove(String attrname){
		//System.out.println("remove data:"+attrname);
		LOCAL_SESSION.remove(attrname);
		//System.out.println("LOCAL_SESSION size:"+LOCAL_SESSION.size());
		
	}
}
