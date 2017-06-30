package com.dk.util;

/** 
* @ClassName: myutils 
* @Description: TODO(RoyShen工具类采集稳定版) 
* @author RoyShen
* @date 2017年6月23日 下午1:00:36 
*  
*/ 
public class myutils {
	
	 /** 
	* @Title: mystring 
	* @Description: TODO(String校验不为空，以及不为空字符串，则返回true否为false) 
	* @param @param t
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	*/ 
	public static boolean mystring(String t){
		 if (t!=null && !t.isEmpty()) {
			 return true;	
		}else{
			return false;
		}
		 
	 }
}
