package com.dk.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<String> lists = new ArrayList<String>(2);
		lists.add("1212");
		lists.add("2323");
		lists.add("45443");
		while(true){
			lists.add("11");
			System.out.println(lists.size());
		}
	}

}
