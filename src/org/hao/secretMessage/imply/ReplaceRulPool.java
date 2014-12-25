package org.hao.secretMessage.imply;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sin 2014-12-24
 * 替换规则池
 *
 */
public class ReplaceRulPool {

	private static Map<String,Map<String,String>> pool;
	
	public static Map<String,String> getRul(String name){
		if(pool == null){
			pool = new HashMap<String,Map<String,String>>();
			Map<String,String> defaultRul = new HashMap<String,String>();
			defaultRul.put("a", "z");
			defaultRul.put("b", "y");
			defaultRul.put("c", "x");
			defaultRul.put("d", "w");
			defaultRul.put("e", "v");
			defaultRul.put("f", "u");
			defaultRul.put("g", "t");
			defaultRul.put("h", "s");
			defaultRul.put("i", "r");
			defaultRul.put("j", "q");
			defaultRul.put("k", "p");
			defaultRul.put("l", "o");
			defaultRul.put("m", "n");
			defaultRul.put("n", "m");
			defaultRul.put("o", "l");
			defaultRul.put("p", "k");
			defaultRul.put("q", "j");
			defaultRul.put("r", "i");
			defaultRul.put("s", "h");
			defaultRul.put("t", "g");
			defaultRul.put("u", "f");
			defaultRul.put("v", "e");
			defaultRul.put("w", "d");
			defaultRul.put("x", "c");
			defaultRul.put("y", "b");
			defaultRul.put("z", "a");
			pool.put("default", defaultRul);
			
			Map<String,String> keyboardRul = new HashMap<String,String>();
			keyboardRul.put("a", "11");
			keyboardRul.put("b", "24");
			keyboardRul.put("c", "22");
			keyboardRul.put("d", "13");
			keyboardRul.put("e", "03");
			keyboardRul.put("f", "14");
			keyboardRul.put("g", "15");
			keyboardRul.put("h", "16");
			keyboardRul.put("i", "08");
			keyboardRul.put("j", "17");
			keyboardRul.put("k", "18");
			keyboardRul.put("l", "19");
			keyboardRul.put("m", "25");
			keyboardRul.put("n", "26");
			keyboardRul.put("o", "09");
			keyboardRul.put("p", "10");
			keyboardRul.put("q", "01");
			keyboardRul.put("r", "04");
			keyboardRul.put("s", "12");
			keyboardRul.put("t", "05");
			keyboardRul.put("u", "07");
			keyboardRul.put("v", "23");
			keyboardRul.put("w", "02");
			keyboardRul.put("x", "21");
			keyboardRul.put("y", "06");
			keyboardRul.put("z", "20");
			pool.put("keyboard", keyboardRul);
		}
		return pool.get(name);
	}
	
}
