package com.dk.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.io.input.ReaderInputStream;

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
		
//		String str = "255255201123";
//		StringBuffer number = new StringBuffer();
//		for(int i=1;i<=str.length();i++){
//			number.append(str.charAt(i-1));
//			if(i%3==0){
//				number.append(".");
//			}
//		}
//		System.out.println(number.toString().substring(0,number.toString().length()-1));
		
//		String str = "hanhhhiao";//hanshhiao
////		str = str.replace(str.charAt(0), 's');
//		str = str.substring(0,3)+"s"+str.substring(4,str.length()-1);
//		System.out.println(str);
		
//		String str = "101.37.34.43";
//		for(int i=0;i<str.length();i++){
//			char charStr = str.charAt(i);
//			int intStr = (int)charStr;
//			System.out.println(Integer.toHexString(intStr));
//		}
		
//		System.out.println(Integer.toHexString(36));
	        
//	    System.out.println((char)0);
//	    System.out.println(Integer.valueOf("f",16));
//		char a = 'A';
//		System.out.println((int)a);
//		System.out.println("A".equals('A'));
//		int i = 65;
//		char a = 'A';
//		System.out.println(i==a);
//		String time = "2017-06-26 16:42:59";
//		Timestamp t1 = Timestamp.valueOf(time);
//		Timestamp t1 = new Timestamp(System.currentTimeMillis());
//		Timestamp t2 = new Timestamp(System.currentTimeMillis());
//		System.out.println(t2.getTime()-t1.getTime());
		
		Runtime cmd = Runtime.getRuntime();
//		try {
////			Process p = cmd.exec("mysqldump -t -uroot -phanxiao test score -r D://test1.sql".toString());
//			Process p = cmd.exec("ipconfig");
////			System.out.println("导出成功");
//			byte[] by = new byte[102400000];
//			InputStream input = p.getInputStream();
//			InputStreamReader in = new InputStreamReader(input,"gbk");
//			BufferedReader reader = new BufferedReader(in);
//			while(reader.read()!=-1){
//				System.out.println(reader.readLine());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.MONTH));
		c.setTimeInMillis(System.currentTimeMillis()-1000*60*60*24*365);
		System.out.println(c.getWeekYear());
		
		
	}
	
//	@SuppressWarnings("unused")
//	private static void main(String[] args){
//		System.out.println(1111);
//		
//	}

}
