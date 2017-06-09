package com.dk.test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test {
	public static void main(String[] args){
//		UserService userService = new UserServiceImpl();
//		Result result = userService.getUserInfos();
//		System.out.println(result);
//		JSONObject json = JSONObject.fromObject(result);
//		System.out.println(json.get("data"));
//		String str = "2017-04-27 00:00:00";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf.p);
//		Date date = new Date();
//		System.out.println(date<(sdf.parse(str)));
		
//		StringBuffer str = new StringBuffer();
//		str.setLength(0);
//		System.out.println(str.toString().toUpperCase());
//		System.out.println(str.toString().isEmpty());
//		System.out.println(111);
//		System.out.println((char)30);
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("name", "han");
//		String name = map.get("name");
//		System.out.println(name);
//		String age = map.get("age");
//		System.out.println(age);
//		String str = "abcdefg";
//		System.out.println(str.substring(0,2));
//		System.out.println("414c545f4c6f5261303031".toUpperCase());
//		String str = "0406C0A80001000A0306C0A80001001E0506C0A80001001E0606C0A80001000A";
//		System.out.println(str.length()/16);
//		for(int i=0;i<str.length()/16;i++){
//			String loadStr = str.substring(i*16,i*16+16);
//			if("04".equals(loadStr.substring(0,2))){
//				int runTime = Integer.parseInt(loadStr.substring(13,16),16);
//				System.out.println(runTime);
//			}else if("03".equals(loadStr.substring(0,2))){
//				int staticTime = Integer.parseInt(loadStr.substring(13,16),16);
//				System.out.println(staticTime);
//			}else if("05".equals(loadStr.substring(0,2))){
//				int gpsTimeOut = Integer.parseInt(loadStr.substring(13,16),16);
//				System.out.println(gpsTimeOut);
//			}else if("06".equals(loadStr.subSequence(0, 2))){
//				int loraSleepTime = Integer.parseInt(loadStr.substring(13,16),16);
//				System.out.println(loraSleepTime);
//			}else{
//			}
//		}
//		long start = System.currentTimeMillis();
//		while(true){
//			long end = System.currentTimeMillis();
//			if(end-start>5000){
//				break;
//			}else{
//				System.out.println(System.currentTimeMillis());
//			}
//		}
//		System.out.println("end");
//		String str = "ALT_LORA001";
//		char[] chars = str.toCharArray();
//		for (char c : chars) {
//			System.out.println(Integer.toHexString((int)c));
//		}
//		String sn = "414C545F4C6F5261303031";
//		List<String> lists = new ArrayList<String>();
//		for(int i=0;i<sn.length()/2;i++){
//			String snStr = sn.substring(i*2,i*2+2);
//			lists.add(snStr);
//		}
//		StringBuffer SN = new StringBuffer();
//		for(String str:lists){
//			char c = (char) Integer.parseInt(str,16);
//			SN.append(c);
//		}
//		System.out.println(SN.toString());
//		int a = 16;
//		System.out.println(Integer.toHexString(a));
//		
//		StringTokenizer token=new StringTokenizer("192.168.0.1",".");  
//		List<Object> list=new ArrayList<>();
//        while(token.hasMoreElements()){ 
//        	int parseInt = Integer.parseInt(token.nextToken());
//        	String hexString = Integer.toHexString(parseInt); 
//        	list.add(hexString);
//        }
//        for (Object object : list) {
//			System.out.println(object);
//		}
		
		String str = "255255201123";
		StringBuffer number = new StringBuffer();
		for(int i=1;i<=str.length();i++){
			number.append(str.charAt(i-1));
			if(i%3==0){
				number.append(".");
			}
		}
		System.out.println(number.toString().substring(0,number.toString().length()-1));
	        
	      
	}

}
