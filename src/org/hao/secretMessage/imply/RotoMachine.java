package org.hao.secretMessage.imply;

import java.util.Map;

/**
 * 
 * @author sin 2014-12-24
 * 用于置换加密算法的转子机
 * 根据置换方式 将不同的编码置换成新码
 *
 */
public class RotoMachine {

	private Map<String,String> replaceRul;
	
	public void setRule(Map<String,String> rul){
		replaceRul = rul;
	}
	/**
	 * sin 2014-12-24
	 * @param 源码
	 * @return 置换后的码
	 */
	public String replace(char source){
		if(replaceRul==null){
			System.out.println("replaceRul is null");
			return "";
		}
		return replaceRul.get(""+source);
	}
}
