package com.dk.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dk.test.KillServer;

public class NettyListener implements ServletContextListener{
	private static MyThread myThread;
	
//	Thread thread;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("distroy");
		// TODO Auto-generated method stub
//		KillServer kill = new KillServer();
//		kill.start(6262);
//		myThread.interrupt();
		try {
			myThread.interrupt();
//			Thread.currentThread().interrupt();
		} catch (Exception e) {
			// TODO: handle exception
			Thread.currentThread().interrupt();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println("nettyListener Startup!"); 
//		new Thread(){  
//            @Override  
//            public  void run(){  
//                try {  
//                    new TimeServer().bind(6262);  
//                } catch (Exception e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//        }.start();  
		myThread = new MyThread();
		myThread.start();
		
	}
	
	class MyThread extends Thread{
		public void run(){
			try {
				new TimeServer().bind(6262);
			} catch (Exception e) {
				// TODO: handle exception
//				e.printStackTrace();
			}
		}
	}

}


