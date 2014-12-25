package org.hao.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tools {

	public static String getToday(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	
	public static String getFewTodaysAgo(String pattern, int days){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE)-days);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(now.getTime());
	}
}
