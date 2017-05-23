package com.dk.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class HsqlDB {
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","sa","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
