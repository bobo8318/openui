package org.hao.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParse {
	
	/*
	 * 过滤掉html输入内容中的html标签等内容
	 */
	public static String Html2Text(String inputString) {
		if(null == inputString) return null;
        String htmlStr = inputString; //含html标签的字符串
            String textStr ="";
      java.util.regex.Pattern p_script;
      java.util.regex.Matcher m_script;
      java.util.regex.Pattern p_style;
      java.util.regex.Matcher m_style;
      java.util.regex.Pattern p_html;
      java.util.regex.Matcher m_html;
   
      try {
       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
      
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
          m_script = p_script.matcher(htmlStr);
          htmlStr = m_script.replaceAll(""); //过滤script标签

          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
          m_style = p_style.matcher(htmlStr);
          htmlStr = m_style.replaceAll(""); //过滤style标签
      
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
          m_html = p_html.matcher(htmlStr);
          htmlStr = m_html.replaceAll(""); //过滤html标签
      
       textStr = htmlStr;
      
      }catch(Exception e) {
               System.err.println("Html2Text: " + e.getMessage());
      }
   
      return textStr;//返回文本字符串
       }   
	
	/*
	 *根据参数过滤标签
	 */
	public static String Html2Text(String inputString,int[] parameter) {
        String htmlStr = inputString; //含html标签的字符串
            String textStr ="";
      java.util.regex.Pattern p_script;
      java.util.regex.Matcher m_script;
      java.util.regex.Pattern p_style;
      java.util.regex.Matcher m_style;
      java.util.regex.Pattern p_html;
      java.util.regex.Matcher m_html;
   
      try {
       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
          if(parameter.length != 3) return inputString;
          if(parameter[0] == 1){
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
          m_script = p_script.matcher(htmlStr);
          htmlStr = m_script.replaceAll(""); //过滤script标签
          }
          if(parameter[1] == 1){
          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
          m_style = p_style.matcher(htmlStr);
          htmlStr = m_style.replaceAll(""); //过滤style标签
          }
          if(parameter[2] == 1){
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
          m_html = p_html.matcher(htmlStr);
          htmlStr = m_html.replaceAll(""); //过滤html标签
          }
       textStr = htmlStr;
      
      }catch(Exception e) {
               System.err.println("Html2Text: " + e.getMessage());
      }
   
      return textStr;//返回文本字符串
       }   
	
	
	
	 public static String dateToString(Date date, String type) {
	        String str = null;
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        if (type.equals("SHORT")) {
	            // 07-1-18
	            format = DateFormat.getDateInstance(DateFormat.SHORT);
	            str = format.format(date);
	        } else if (type.equals("MEDIUM")) {
	            // 2007-1-18
	            format = DateFormat.getDateInstance(DateFormat.MEDIUM);
	            str = format.format(date);
	        } else if (type.equals("FULL")) {
	            // 2007年1月18日 星期四
	            format = DateFormat.getDateInstance(DateFormat.FULL);
	            str = format.format(date);
	        }
	        return str;
	    }
	 
	 
	    public static Date stringToDate(String str) {
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = null;
	        try {
	            // Fri Feb 24 00:00:00 CST 2012
	            date = format.parse(str); 
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        // 2012-02-24
	        date = java.sql.Date.valueOf(str);
	                                             
	        return date;
	    }
	    
	    //将 1,2,3 变为数组
	    public static List getInts(String str){
	    	if(str.length() <= 0) return null;
	    	List result = new ArrayList();
	    	while(str.length()>0){
	    		//result
	    	}
	    	return result;
	    }
	    public static void showList(List list){
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().toString());
			}
		}
		
		public static String ListToString(List list){
			StringBuffer sb = new StringBuffer();
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				sb.append(iterator.next().toString());
				sb.append(",");
			}
			return sb.toString();
		}
		/**
		 * sin 2013-8-13
		 * 屏蔽关键字
		 * @param source
		 * @return 经过处理的字符串
		 */
		public static String StringParser(String source){
			String[] strban = Parameter.WORDSBAN.split(",");
			for(int i=0;i<strban.length;i++){
				source.replaceAll(strban[i], "**");
			}
			return source;
		}
		/**
		 * sin 2013-9-4
		 * 取时间  用于文件重命名
		 */
		public static String FileRenameByTime(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String filename = sdf.format(new Date());
			return filename;
		}
	
}
