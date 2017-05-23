package com.dk.object;

import java.util.ArrayList;
import java.util.List;

public class RoleList {
	private static List<String> opList = new ArrayList<String>();
	
	
	public static List<String> getOpList(){
		return opList;
	}
	
	public static void addList(String str){
		opList.add(str);
	}
	
	
}
