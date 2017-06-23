package com.dk.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelServer {
	private static Map<String, ChannelHandlerContext> map = new ConcurrentHashMap<>();
    
    public static void addGatewayChannel(String id, ChannelHandlerContext gateway_channel){
        map.put(id, gateway_channel);
    }
    
    public static Map<String, ChannelHandlerContext> getChannels(){
        return map;
    }

    public static ChannelHandlerContext getGatewayChannel(String id){
        return map.get(id);
    }
    
    public static void removeGatewayChannel(String id){
        map.remove(id);
    }
    
    private static String waitResponse = null;
    
    public static void setString(String str){
    	waitResponse = str;
    }
    
    public static String getString(){
    	return waitResponse;
    }
    
    private static int status = -1;
    
    public static void setInt(int stat){
    	status = stat;
    }
    
    public static int getInt(){
    	return status;
    }
    
    private static boolean isSend = false;
    
    public static void setIsSend(boolean isSent){
    	isSend = isSent;
    }
    
    public static boolean getIsSend(){
    	return isSend;
    }
    
    private static String number = null;
    
    public static void setNumber(String num){
    	number = num;
    }
    
    public static String getNumber(){
    	return number;
    }

}
