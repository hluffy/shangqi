package com.dk.netty;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelServer {
	private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();
    
    public static void addGatewayChannel(String id, SocketChannel gateway_channel){
        map.put(id, gateway_channel);
    }
    
    public static Map<String, SocketChannel> getChannels(){
        return map;
    }

    public static SocketChannel getGatewayChannel(String id){
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

}
