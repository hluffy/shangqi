package com.dk.controller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.netty.ChannelServer;
import com.dk.object.LocalizerInfo;
import com.dk.object.LoraInfo;
import com.dk.result.Result;
import com.dk.service.LocalizerService;
import com.dk.util.Date;

@Controller
@RequestMapping("remote")
/**
 * 远程控制
 * @author carol
 *
 */
public class RemoteController {
	@Resource
	private LocalizerService localService;
	
	/**
	 * 重启LORA模块 ok
	 * @param str
	 * @return
	 */
	@RequestMapping("restartloramac.ll")
	@ResponseBody
	public Result restartLoraMac(@RequestBody LocalizerInfo local){
		Result result = new Result();
		ChannelServer.setString(null);
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(local.getSv());
		if(ctx==null){
			result.setStates(false);
			result.setMessage("连接不存在，请稍后再试");
			return result;
		}
		ByteBuf resp = Unpooled.copiedBuffer(getByte(restartLora(local)));
		ctx.writeAndFlush(resp);
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>5000){
				break;
			}
		}
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
			
		}else{
			result.setStates(true);
			result.setMessage(ChannelServer.getString());
			ChannelServer.setString(null);
		}
		return result;
	}
	
	/**
	 * 重启LORA基站 ok
	 * @return
	 */
	@RequestMapping("restartlora.ll")
	@ResponseBody
	public Result restartLora(@RequestBody LoraInfo lora){
		Result result = new Result();
		ChannelServer.setString(null);
		if(lora.getNumberDef()==null){
			result.setMessage("连接不存在");
			return result;
		}
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(lora.getNumberDef());
		if(ctx==null){
			result.setMessage("连接不存在，请稍后再试");
			return result;
		}
		ByteBuf resp = Unpooled.copiedBuffer(getByte(sendLoraStr(lora)));
		ctx.writeAndFlush((resp));
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>5000){
				break;
			}
		}
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else{
			result.setStates(true);
			result.setMessage(ChannelServer.getString());;
			ChannelServer.setString(null);
		}
		return result;
	}
	
	/**
	 * 同步时间
	 */
	@RequestMapping("synctime.ll")
	@ResponseBody
	public Result syncTime(@RequestBody LoraInfo info){
		Result result = new Result();
		ChannelServer.setString(null);
		if(info.getNumberDef()==null){
			result.setStates(false);
			result.setMessage("连接不存在");
			return result;
		}
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(info.getNumberDef());
		if(ctx==null){
			result.setStates(false);
			result.setMessage("连接不存在，请稍后再试");
			return result;
		}
		ByteBuf resp = Unpooled.copiedBuffer(getByte(syncTimeStr(info)));
		ctx.writeAndFlush((resp));
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>5000){
				break;
			}
			
		}
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else{
			result.setStates(true);
			result.setMessage(ChannelServer.getString());
			ChannelServer.setString(null);
		}
		return result;
	}
	
	//同步参数 ok
	@RequestMapping("loadparame.ll")
	@ResponseBody
	public Result loadLocal(@RequestBody LocalizerInfo info){
		Result result = new Result();
		ChannelServer.setString(null);
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(info.getSv());
		if(ctx==null){
			result.setStates(false);
			result.setMessage("连接不存在,请稍后再试");
			return result;
		}
		ByteBuf resp = Unpooled.copiedBuffer(getByte(loadStr(info)));
		ctx.writeAndFlush(resp);
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>5000){
				break;
			}
		}
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else{
			result.setStates(true);
			result.setMessage(ChannelServer.getString());
			ChannelServer.setString(null);
		}
		return result;
	}
	
	private String syncTimeStr(LoraInfo info){
		StringBuffer syncTimeStr = new StringBuffer();
		syncTimeStr.append("7b");
		syncTimeStr.append("0004");
		syncTimeStr.append(info.getNumberDef());
		syncTimeStr.append("89");
		syncTimeStr.append("0008");
		syncTimeStr.append("03");
		syncTimeStr.append("06");
		syncTimeStr.append(Date.getDate());
		syncTimeStr.append("7d");
		System.out.println(syncTimeStr.toString());
		return syncTimeStr.toString();
	}
	
	//同步lora模块参数
	private String loadStr(LocalizerInfo info){
		StringBuffer loadStr = new StringBuffer();
		loadStr.append("7b");
		loadStr.append("0004");
		loadStr.append(info.getSv());
		loadStr.append("87");
		loadStr.append("0018");
		loadStr.append("03");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("04");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("05");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("06");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("7d");
		System.out.println("loadStr:"+loadStr.toString());
		return loadStr.toString();
	}
	
	//重启lora模块
	private String restartLora(LocalizerInfo info){
		StringBuffer restartStr = new StringBuffer();
		restartStr.append("7b");
		restartStr.append("0004");
		restartStr.append(info.getSv());
		restartStr.append("89");
		restartStr.append("0006");
		restartStr.append("01");
		restartStr.append("04");
		restartStr.append(numberToHex(info.getNumber()));
		restartStr.append("7d");
		return restartStr.toString();
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
	
	//将设备号按位转为16进制
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
	
	//重启lora网关
	private String sendLoraStr(LoraInfo info){
		StringBuffer sendStr = new StringBuffer();
		sendStr.append("7b");
		sendStr.append("0004");
		sendStr.append(info.getNumberDef());
		sendStr.append("89");
		sendStr.append("0003");
		sendStr.append("02");
		sendStr.append("01");
		sendStr.append("01");
		sendStr.append("7d");
		System.out.println(sendStr.toString());
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
