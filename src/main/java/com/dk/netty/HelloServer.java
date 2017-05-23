package com.dk.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
//import org.jboss.netty.channel.ChannelHandlerContext;
//import org.jboss.netty.channel.ChannelPipeline;
//import org.jboss.netty.channel.ChannelPipelineFactory;
//import org.jboss.netty.channel.ChannelStateEvent;
//import org.jboss.netty.channel.Channels;
//import org.jboss.netty.channel.ExceptionEvent;
//import org.jboss.netty.channel.MessageEvent;
//import org.jboss.netty.channel.SimpleChannelHandler;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * Netty 服务端代码
 * 
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class HelloServer {

//	public static void main(String args[]) {
//		// Server服务启动器
//		ServerBootstrap bootstrap = new ServerBootstrap(
//				new NioServerSocketChannelFactory(
//						Executors.newCachedThreadPool(),
//						Executors.newCachedThreadPool()));
//		// 设置一个处理客户端消息和各种消息事件的类(Handler)
//		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//			public ChannelPipeline getPipeline() throws Exception {
//				return Channels.pipeline(new HelloServerHandler());
//			}
//		});
//		// 开放8000端口供客户端访问。
//		bootstrap.bind(new InetSocketAddress(8000));
//	}

//	private static class HelloServerHandler extends SimpleChannelHandler {
//
//		/**
//		 * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
//		 * 
//		 * @alia OneCoder
//		 * @author lihzh
//		 */
//		@Override
//		public void channelConnected(ChannelHandlerContext ctx,ChannelStateEvent e) {
//			System.out.println("Hello world, I'm server.");
//		}
//		
//	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {  
//	        String message = (String) e.getMessage().toString();  
//	        System.out.println(message);  
//	       
////	        BigEndianHeapChannelBuffer buffer = new BigEndianHeapChannelBuffer(24);
//	        BigEndianHeapChannelBuffer buffer = (BigEndianHeapChannelBuffer)e.getMessage();
//	        System.out.println(buffer.array().length); 
//	        System.out.println("buffer.getChar(0) :" + buffer.getChar(0));  
//	        System.out.println("buffer.readChar() :" + buffer.readChar());  
//	        for(byte bb : buffer.array()){
//	        	System.out.println(bb);
//	        }
//	        e.getChannel().close();  
//	    }  
//	  
//	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {  
//	        System.out.println("Unexpected exception from downstream."  
//	                + e.getCause());  
//	        e.getChannel().close();  
//	    } 
//		
//	}
}
