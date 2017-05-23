package com.dk.util;

import java.util.Calendar;

public class Date {
	public static void main(String[] args) {
		System.out.println(getDate());
	}
	
	public static String getDate(){
		StringBuffer dateStr = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		String yy = String.valueOf(calendar.get(Calendar.YEAR)).substring(2,4);
		dateStr.append(formatStr(Integer.toHexString(Integer.parseInt(yy))));
		String MM = Integer.toHexString(calendar.get(Calendar.MONTH)+1);
		dateStr.append(formatStr(MM));
		String dd = Integer.toHexString(calendar.get(Calendar.DAY_OF_MONTH));
		dateStr.append(formatStr(dd));
		String hh = Integer.toHexString(calendar.get(Calendar.HOUR_OF_DAY));
		dateStr.append(formatStr(hh));
		String mm = Integer.toHexString(calendar.get(Calendar.MINUTE));
		dateStr.append(formatStr(mm));
		String ss = Integer.toHexString(calendar.get(Calendar.SECOND));
		dateStr.append(formatStr(ss));
		return dateStr.toString();
	}
	
	public static String formatStr(String str){
		if(str.length()<2){
			str = "0"+str;
		}
		return str;
	}

}
