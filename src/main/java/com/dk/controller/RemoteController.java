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

import sun.security.util.Length;

import com.dk.netty.ChannelServer;
import com.dk.object.LocalizerInfo;
import com.dk.object.LoraInfo;
import com.dk.result.Result;
import com.dk.service.LocalizerService;
import com.dk.service.LoraService;
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
	
	@Resource
	private LoraService loraServer;
	
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
		if(local.getSv()==null){
			result.setMessage("连接不存在");
			return result;
		}
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
			if(end-start>20000){
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
	 * 设置定位设备参数
	 */
	@RequestMapping("setEquipPara.ll")
	@ResponseBody
	public Result setEquipPara(@RequestBody LocalizerInfo info){
		Result result = new Result();
		ChannelServer.setString(null);
		ChannelServer.setNumber(null);
		ChannelServer.setIsSend(false);
		if(info.getSv()==null||info.getSv().isEmpty()){
			result.setMessage("连接不存在");
			return result;
		}
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(info.getSv());
		if(ctx==null){
			result.setMessage("连接不存在，请稍后再试");
			return result;
		}
		
		System.out.println(setEquipmentPara(info));
		String equipmentPara = setEquipmentPara(info);
		ChannelServer.setNumber(info.getNumber());
		ByteBuf resp = Unpooled.copiedBuffer(getByte(equipmentPara));
		long sendStart = System.currentTimeMillis();
//		while(!ChannelServer.getIsSend()){
//			System.out.println(ChannelServer.getIsSend());
//			long sendEnd = System.currentTimeMillis();
//			if(sendEnd-sendStart>1000*60){
//				break;
//			}
//		}
		ctx.writeAndFlush(resp);
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>20000){
				break;
			}
			
		}
//		equipmentPara = equipmentPara.substring(0,28)+"0"+equipmentPara.substring(29,equipmentPara.length());
//		if(ChannelServer.getString()==null){
//			result.setMessage("连接超时，请稍后再试");
//			return result;
//		}else if(equipmentPara.subSequence(6, equipmentPara.length()).equals(ChannelServer.getString().substring(6, ChannelServer.getString().length()))){
//			result = localService.updateInfo(info);
//			result.setMessage("设置成功，重启后生效");
////			ChannelServer.removeGatewayChannel(lora.getNumberDef());
//			ChannelServer.setString(null);
//			ctx.close();
//		}else{
//			result.setStates(false);
//			result.setMessage("请检查返回值");
//		}
		
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else if("成功".equals(ChannelServer.getString())){
			result.setStates(true);
			result.setMessage("更新成功");
		}else{
			result.setStates(false);
			result.setMessage("更新失败");
		}
		ChannelServer.setIsSend(false);
		return result;
	}
	
	/**
	 * 设置lora基站发送的服务器地址和端口
	 */
	@RequestMapping("setIpAndPort.ll")
	@ResponseBody
	public Result setIpAndPort(@RequestBody LoraInfo lora){
		Result result = new Result();
		if(lora.getRegistAddr()==null||lora.getRegistAddr().isEmpty()
				||lora.getRegistPort()==null||lora.getRegistPort().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
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
		
		String ipPortData = setLoraIpPort(lora);
		System.out.println(ipPortData);
		
		ByteBuf resp = Unpooled.copiedBuffer(getByte(ipPortData));
		ctx.writeAndFlush(resp);
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>20000){
				break;
			}
		}
//		ipPortData = ipPortData.replace(ipPortData.charAt(30), '0');
		ipPortData = ipPortData.substring(0,28)+"0"+ipPortData.substring(29,ipPortData.length());
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else if(ipPortData.subSequence(6, ipPortData.length()).equals(ChannelServer.getString().substring(6, ChannelServer.getString().length()))){
			result = loraServer.updateInfo(lora);
			result.setMessage("设置成功，重启后生效");
//			ChannelServer.removeGatewayChannel(lora.getNumberDef());
			ChannelServer.setString(null);
			ctx.close();
		}else{
			result.setStates(false);
			result.setMessage("请检查返回值");
		}
		return result;
	}
	
	/**
	 * 重启LORA基站 ok
	 * @return
	 * 1
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
			if(end-start>20000){
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
			ctx.close();
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
			if(end-start>20000){
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
	
	//同步参数 
	@RequestMapping("loadparame.ll")
	@ResponseBody
	public Result loadLocal(@RequestBody LocalizerInfo info){
		Result result = new Result();
		ChannelServer.setString(null);
		ChannelServer.setNumber(null);
		ChannelServer.setIsSend(false);
		if(info.getSv()==null){
			result.setMessage("连接不存在");
			return result;
		}
		ChannelHandlerContext ctx = ChannelServer.getGatewayChannel(info.getSv());
		if(ctx==null){
			result.setStates(false);
			result.setMessage("连接不存在,请稍后再试");
			return result;
		}
		ChannelServer.setNumber(info.getNumber());
		ByteBuf resp = Unpooled.copiedBuffer(getByte(loadStr(info)));
		long sendStart = System.currentTimeMillis();
		while(!ChannelServer.getIsSend()){
			System.out.println(ChannelServer.getIsSend());
			long sendEnd = System.currentTimeMillis();
			if(sendEnd-sendStart>1000*60){
				break;
			}
		}
		ctx.writeAndFlush(resp);
		long start = System.currentTimeMillis();
		while(ChannelServer.getString()==null){
			System.out.println(ChannelServer.getString());
			long end = System.currentTimeMillis();
			if(end-start>20000){
				break;
			}
		}
//		if(ChannelServer.getString()==null){
//			result.setStates(false);
//			result.setMessage("连接超时，请稍后再试");
//		}else{
//			result.setStates(true);
//			result.setMessage(ChannelServer.getString());
//			ChannelServer.setString(null);
//		}
		if(ChannelServer.getString()==null){
			result.setStates(false);
			result.setMessage("连接超时，请稍后再试");
		}else if("成功".equals(ChannelServer.getString())){
			result.setStates(true);
			result.setMessage("更新成功");
		}else{
			result.setStates(false);
			result.setMessage("失败");
		}
		return result;
	}
	
	
	//设置定位器参数
	private String setEquipmentPara(LocalizerInfo info){
		StringBuffer equipPara = new StringBuffer();
		equipPara.append("7b");
		equipPara.append("0001");
		equipPara.append(info.getSv());
		equipPara.append("87");
		equipPara.append("0030");
		equipPara.append("10");
		equipPara.append("06");
		String number = info.getNumber();
		String[] numbers = number.split("\\.");
		StringBuffer numberStr = new StringBuffer();
		for(int i=0;i<numbers.length;i++){
			String hexNumber = Integer.toHexString(Integer.parseInt(numbers[i]));
			if(hexNumber.length()<2){
				hexNumber = "0"+hexNumber;
			}
			numberStr.append(hexNumber);
		}
		equipPara.append(numberStr.toString());
		String staticTime = info.getStaticTime();
		String hexStaticTime = Integer.toHexString(Integer.parseInt(staticTime));
		for(int i=hexStaticTime.length();i<4;i++){
			hexStaticTime = "0"+hexStaticTime;
		}
		equipPara.append(hexStaticTime);
		equipPara.append("11");
		equipPara.append("06");
		equipPara.append(numberStr.toString());
		String runTime = info.getRunTime();
		String hexRunTime = Integer.toHexString(Integer.parseInt(runTime));
		for(int i=hexRunTime.length();i<4;i++){
			hexRunTime = "0"+hexRunTime;
		}
		equipPara.append(hexRunTime);
		equipPara.append("12");
		equipPara.append("06");
		equipPara.append(numberStr.toString());
		String timeOut = info.getGpsTimeOut();
		String hexTimeOut = Integer.toHexString(Integer.parseInt(timeOut));
		for(int i=hexTimeOut.length();i<4;i++){
			hexTimeOut = "0"+hexTimeOut;
		}
		equipPara.append(hexTimeOut);
		equipPara.append("13");
		equipPara.append("06");
		equipPara.append(numberStr.toString());
		String sleepTime = info.getLoraSleepTime();
		String hexSleepTime = Integer.toHexString(Integer.parseInt(sleepTime));
		for(int i=hexSleepTime.length();i<4;i++){
			hexSleepTime = "0"+hexSleepTime;
		}
		equipPara.append(hexSleepTime);
		
		equipPara.append("14");
		equipPara.append("06");
		equipPara.append(numberStr.toString());
		String ibeaconEffectNum = info.getIbeaconEffectNum();
		String hexEffectNum = Integer.toHexString(Integer.parseInt(ibeaconEffectNum));
		for(int i=hexEffectNum.length();i<4;i++){
			hexEffectNum = "0"+hexEffectNum;
		}
//		if(hexEffectNum.length()<2){
//			hexEffectNum = "0" + hexEffectNum;
//		}
		equipPara.append(hexEffectNum);
		
		equipPara.append("15");
		equipPara.append("06");
		equipPara.append(numberStr.toString());
		String ibeaconTimeOut = info.getIbeaconTimeOut();
		String hexIbeaconTimeOut = Integer.toHexString(Integer.parseInt(ibeaconTimeOut));
		for(int i=hexIbeaconTimeOut.length();i<4;i++){
			hexIbeaconTimeOut = "0"+hexIbeaconTimeOut;
		}
		equipPara.append(hexIbeaconTimeOut);
		
		equipPara.append("7d");
		return equipPara.toString().toUpperCase();
	}
	
	//设置lora ip和端口
	private String setLoraIpPort(LoraInfo info){
		StringBuffer loraIpPort = new StringBuffer();
		StringBuffer loraData = new StringBuffer();
		StringBuffer loraLength = new StringBuffer();
		loraIpPort.append("7b");
		loraIpPort.append("0004");
		loraIpPort.append(info.getNumberDef());
		loraIpPort.append("87");
//		loraIpPort.append("0017");
//		loraIpPort.append("30");
//		loraIpPort.append("04");
		loraData.append("30");
		String ip = info.getRegistAddr();
		for(int i=0;i<ip.length();i++){
			char charIp = ip.charAt(i);
			int intIp = ((int)charIp);
			String hexIp = Integer.toHexString(intIp);
			for(int j=hexIp.length();j<2;j++){
				hexIp = "0"+hexIp;
			}
//			loraIpPort.append(hexIp);
//			loraData.append(hexIp);
			loraLength.append(hexIp);
		}
		String length30 = Integer.toHexString(loraLength.toString().length()/2);
		if(length30.length()<2){
			length30 = "0"+length30;
		}
		loraData.append(length30);
		loraData.append(loraLength.toString());
//		String[] splitIp = ip.split("\\.");
//		for (String str : splitIp) {
//			String hexStr = Integer.toHexString(Integer.parseInt(str));
//			if(hexStr.length()<2){
//				hexStr = "0"+str;
//			}
//			loraIpPort.append(hexStr);
//		}
		loraData.append("31");
		loraData.append("02");
//		loraIpPort.append("31");
//		loraIpPort.append("02");
		String hexPort = Integer.toHexString(Integer.parseInt(info.getRegistPort()));
		for(int i=hexPort.length();i<4;i++){
			hexPort = "0"+hexPort;
		}
		loraData.append(hexPort);
		loraData.append("33");
		loraData.append("0b");
		loraData.append(info.getNumberDef());
		
		String length = Integer.toHexString(loraData.toString().length()/2);
//		if(length.length()<2){
//			length = "0"+length;
//		}
		for(int i=length.length();i<4;i++){
			length = "0"+length;
		}
		loraIpPort.append(length);
		loraIpPort.append(loraData.toString());
		loraIpPort.append("7d");
		
//		loraIpPort.append(Integer.toHexString(Integer.parseInt(info.getRegistPort())));
		return loraIpPort.toString().toUpperCase();
	}
	
	//同步时间
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
		loadStr.append("0024");
		loadStr.append("10");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("11");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("12");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("13");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("14");
		loadStr.append("04");
		loadStr.append(numberToHex(info.getNumber()));
		loadStr.append("15");
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
