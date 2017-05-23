package com.dk.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 该类用于管理数据库连接
 * @author Administrator
 *
 */
public class DBUtil {
	
	//数据库连接池
	private static BasicDataSource ds;
	
	static{
		//初始化静态属性
		//1加载配置文件
		/*
		 * java.util.Properties
		 * 用来读取.properties文件，并解析其中
		 * 每一行内容，然后以key-value的形式保存
		 * 在当前实例中。
		 */
		Properties prop = new Properties();
		try {
			//FileInputStream默认从项目根目录下读取文件，
			//但现在是web项目，文件放在resource下，编译时
			//被编译到classes下，所以需要从类路径（classes）
			//下读取此文件。
			//prop.load(new  FileInputStream("config.properties"));
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			String className = prop.getProperty("classname");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait = Integer.parseInt(prop.getProperty("maxwait"));
			//初始化连接池
			ds = new BasicDataSource();
			//将JDBC建立连接所需要的信息设置到连接池中
			
			//Class.forName(...)
			ds.setDriverClassName(className);
			
			//DriverManager.getConnection(...)
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//设置连接池最大连接数
			ds.setMaxActive(maxActive);
			//设置最大等待时间
			ds.setMaxWait(maxWait);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//2根据配置文件初始化
		
		
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		/*
		 * 连接池提供的方法：
		 * Connection getConnection()
		 * 该方法可以返回一个连接池中可用连接。
		 * 这是一个阻塞方法，当连接池中有空闲连接
		 * 可以使用时会立刻返回，若当前连接池没有
		 * 可用连接时，会进入阻塞，阻塞时间由创建
		 * 连接池时通过setMaxWait设置的时间为准
		 * 在等待期间若有空闲连接，则立即返回，当
		 * 超过最大等待时间仍没有可用连接时，该
		 * 方法会抛出超时异常。
		 */
		return ds.getConnection();
	}
	
	/**
	 * 关闭给定的连接
	 * @param conn
	 * @throws SQLException 
	 */
	public static void closeConnection(Connection conn){
		try {
			//conn.setAutoCommit(true);
			/*
			 * 若该连接是通过连接池获取的，那么调用
			 * 这个连接的close方法并不是与数据库断开
			 * 连接了，而仅仅是将该连接还给连接池。
			 */
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
		DBUtil.closeConnection(conn);
	}
}











