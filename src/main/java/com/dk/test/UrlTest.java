package com.dk.test;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("file:///C:/Users/carol/DesktopMaperitive/Tiles");
		System.out.println(url.getQuery());
	}

}
