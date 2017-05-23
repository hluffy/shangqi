package com.dk.controller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.netty.ChannelServer;
import com.dk.object.LocalizerInfo;
import com.dk.object.LoraInfo;
import com.dk.result.Result;

@Controller
@RequestMapping("remote")
/**
 * 远程控制
 * @author carol
 *
 */
public class RemoteController {
	
	/**
	 * 重启LORA模块
	 * @param str
	 * @return
	 */
	@RequestMapping("restartloramac.ll")
	@ResponseBody
	public Result restartLoraMac(@RequestBody LocalizerInfo local){
		Result result = new Result();
		Map<String,SocketChannel> map = ChannelServer.getChannels();
		SocketChannel ctx = null;
		ByteBuf resp = Unpooled.copiedBuffer(getByte(sendLoraMacStr(local.getNumber()).toUpperCase()));
		for(String key:map.keySet()){
			ctx = map.get(key);
			ctx.writeAndFlush(resp);
		}
		while(ChannelServer.getString()==null){
			
		}
		
		result.setMessage(ChannelServer.getString());
		ChannelServer.setString(null);
		return result;
	}
	
	/**
	 * 重启LORA基站
	 * @return
	 */
	@RequestMapping("restartlora.ll")
	@ResponseBody
	public Result restartLora(@RequestBody LoraInfo lora){
		Result result = new Result();
		Map<String,SocketChannel> map = ChannelServer.getChannels();
		SocketChannel ctx = null;
		ByteBuf resp = Unpooled.copiedBuffer(getByte(sendLoraStr(lora.getNumber()).toUpperCase()));
		for(String key:map.keySet()){
			ctx = map.get(key);
			ctx.writeAndFlush((resp));
		}
		while(ChannelServer.getString()==null){
			
		}
		result.setMessage(ChannelServer.getString());
		ChannelServer.setString(null);
		return result;
	}
	
	/**
	 * 同步时间
	 */
	@RequestMapping("synctime.ll")
	@ResponseBody
	public Result syncTime(){
		Result result = new Result();
		Map<String,SocketChannel> map = ChannelServer.getChannels();
		SocketChannel ctx = null;
		ByteBuf resp = Unpooled.copiedBuffer(getByte(""));
		for(String key:map.keySet()){
			ctx = map.get(key);
			ctx.writeAndFlush((resp));
		}
		while(ChannelServer.getString()==null){
			
		}
		result.setMessage(ChannelServer.getString());
		ChannelServer.setString(null);
		return result;
	}
	
	private String sendLoraMacStr(String str){
		StringBuffer sendStr = new StringBuffer();
		sendStr.append("7b");
		sendStr.append("0004");
		sendStr.append("414c545f4c6f5261303031");
		sendStr.append("89");
		sendStr.append("0006");
		sendStr.append("01");
		sendStr.append("04");
		sendStr.append(numberToHex(str));
		sendStr.append("7d");
		return sendStr.toString();
	}
	
	
	private String numberToHex(String str){
		String[] strs = str.split("\\.");
		StringBuffer number = new StringBuffer();
		for(String s:strs){
			String hexStr = Integer.toHexString(Integer.valueOf(s));
			if(hexStr.length()<2){
				hexStr = "0"+hexStr;
			}
			number.append(hexStr);
		}
		
		return number.toString();
	}
	
	private String sendLoraStr(String str){
		StringBuffer sendStr = new StringBuffer();
		sendStr.append("7b");
		sendStr.append("0004");
//		sendStr.append(str);
		sendStr.append("414c545f4c6f5261303031");
		sendStr.append("89");
		sendStr.append("0003");
		sendStr.append("02");
		sendStr.append("01");
		sendStr.append("01");
		sendStr.append("7d");
		return sendStr.toString();
	}
	
	
	private byte[] getByte(String str){
		str = str.toUpperCase();
		int length = str.length() / 2;   
	  byte[] d = new byte[length];  
	  int k = 0;
	  for (int i = 0; i < length; i++) {   
	        byte high = (byte) (Character.digit(str.charAt(k), 16) & 0xff);
	    	  byte low = (byte) (Character.digit(str.charAt(k + 1), 16) & 0xff);
	    	  d[i] = (byte) (high << 4 | low);
	    	  
	    	  k += 2;
	  } 
	  return d;
	}
	
	
	

}
