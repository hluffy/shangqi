package com.dk.netty;

import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.dk.gis.client.ApiException;
import com.dk.gis.client.api.IBeaconApi;
import com.dk.gis.client.model.BeaconGroupInput;
import com.dk.gis.client.model.GISLocation;
import com.dk.object.IbeaconInfo;
import com.dk.object.LocalizerInfo;
import com.dk.result.Result;
import com.dk.service.LocalizerService;
import com.dk.serviceImpl.LocalizerServiceImpl;
import com.dk.util.IsInArea;
import com.dk.util.MyComparator;
import com.dk.util.SelectList;

public class StringAnalysis {
	public static String stringAnalysis(String[] args,ChannelHandlerContext ctx){
		LocalizerService localService = new LocalizerServiceImpl();
		Positioning positioning = new Positioning();
		LocalizerInfo localInfo = new LocalizerInfo();
		List<IBeaconSignal> signals = new ArrayList<IBeaconSignal>();
//		positioning.setEquipmentNum("20161231");
		StringBuffer returnString  = new StringBuffer();
		returnString.append("7b");
		
		String[] strs = args;
		DataForString dataString = new DataForString();
		GPS gps = new GPS();
		LoRa lora = new LoRa();
		boolean is7b = strs[0].equals("7B");
		boolean is7d = strs[strs.length-1].equals("7D");
		if(is7b&&is7d){
//			int count = Integer.parseInt(strs[1],16)+Integer.parseInt(strs[2],16);
//			System.out.println(count);
			String count = strs[1]+strs[2];
			dataString.setJishu(count);
			returnString.append(count);
			StringBuffer sn = new StringBuffer();
			StringBuffer snStr = new StringBuffer();
			for(int i = 3;i<14;i++){
				sn.append(strs[i]);
				char a = (char) Integer.parseInt(strs[i], 16);
				snStr.append(String.valueOf(a));
			}
			dataString.setSn(sn.toString());
			localInfo.setSv(sn.toString().toUpperCase());
			localInfo.setSvStr(snStr.toString());
			returnString.append(sn);
			String instructions = strs[14];
			System.out.println(instructions);
			
			//保存连接
			ChannelServer.addGatewayChannel(localInfo.getSv(), ctx);
			
			switch (instructions) {
			case "05":
				returnString.append("85");
//				int length = Integer.parseInt(strs[15]+strs[16],16);
				String length = "0003";
				dataString.setDataLength(length);
				returnString.append(length);
				String TNum = strs[17];
				System.out.println(TNum);
				int LLength = Integer.parseInt(strs[18],16);
				System.out.println(LLength);
				StringBuffer mac = new StringBuffer();
				mac.append(Integer.parseInt(strs[19],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[20],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[21],16));
				mac.append(".");
				mac.append(Integer.parseInt(strs[22],16));
				lora.setIp(mac.toString());
				positioning.setEquipmentNum(mac.toString());
				localInfo.setNumber(mac.toString());
				System.out.println("mac:"+mac.toString());
				int quantity = Integer.parseInt(strs[23]+strs[24],16);
				lora.setQuantity(quantity);
				positioning.setElectricity(quantity);
				localInfo.setEle(quantity);
				
				
				
				String T = strs[25];
				System.out.println("t:"+T);
				switch (T) {
				case "05":
					positioning.setPositioningMode("iBeacon");
					System.out.println("IBeacon---------------------------------------------------------------------------");
					StringBuffer Vdatas = new StringBuffer();
					for(int i =25;i<strs.length-1;i++){
						Vdatas.append(strs[i]);
					}
					System.out.println("length:"+(Vdatas.toString().length()/50));
					List<String> lists = new ArrayList<String>();
					for(int i = 0;i<Vdatas.toString().length()/50;i++){
						String ss = Vdatas.toString().substring(i*50,i*50+50);
						lists.add(ss.substring(2));
					}
					String[] datas = (String[])lists.toArray(new String[lists.size()]);
					System.out.println("datas------------------");
					StringBuffer strdata = new StringBuffer();
					for(int i=0;i<datas.length;i++){
						strdata.append(datas[i]);
					}
					System.out.println(strdata.toString());
					
					StringBuffer strData = new StringBuffer();
					
//					String[] datas = Vdatas.toString().split(T);
					for(String s:datas){
						int dataLength = Integer.parseInt(s.substring(0, 2),16);
						int dataActiveLength = s.substring(2,s.length()).length()/2;
						if(dataLength==dataActiveLength){
							IBeaconSignal signal = new IBeaconSignal();
							IbeaconInfo ibeaconInfo = new IbeaconInfo();
							String data = s.substring(2,s.length());
							String uuid = data.substring(0,32);
//							signal.setUuid(uuid);
//							System.out.println("uuid:"+uuid);
							int maior = Integer.parseInt(data.substring(32,36),16);
							System.out.println("maior:"+maior);
							signal.setMaior(maior);
							strData.append("maior:"+maior+" ");
							int minor = Integer.parseInt(data.substring(36,40),16);
							System.out.println("minor:"+minor);
							signal.setMinor(minor);
							strData.append("minor:"+minor+" ");
							int rssi = Integer.parseInt(data.substring(40,42),16);
							System.out.println("rssi:"+(rssi-255));
							signal.setRssi((double)(rssi-255));
							strData.append("rssi:"+(double)(rssi-255)+" ");
							int power = Integer.parseInt(data.substring(42,44),16);
							System.out.println("power:"+power);
							signal.setPower(power);
							strData.append("power:"+power+" ");
							int bat = Integer.parseInt(data.substring(44,46),16);
							System.out.println("bat:"+bat);
							signal.setBat(bat);
							ibeaconInfo.setEle(bat);
							strData.append("bat:"+bat+" ");
							
							String maiorStr;
							if((maiorStr=String.valueOf(maior)).length()<2){
								maiorStr = "0"+maiorStr;
							}
							String minorStr = String.valueOf(minor);
							if(minorStr.length()==1){
								minorStr = "00"+minorStr;
							}
							if(minorStr.length()==2){
								minorStr = "0"+minorStr;
							}
							signal.setUuid("IBCSAIC-"+maiorStr+"-"+minorStr);
							System.out.println("uuid------IBCSAIC-"+maiorStr+"-"+minorStr);
							ibeaconInfo.setUuid("IBCSAIC-"+maiorStr+"-"+minorStr);
							strData.append("uuid:IBCSAIC-"+maiorStr+"-"+minorStr+" ");
							
//							HsqldbUtil.updateIbeacon(ibeaconInfo);
//							if(signals.size()<3){
							signals.add(signal);
//							}
						}
					}
					
					writeStr(positioning.getEquipmentNum(),signals);
					if(signals.size()<3){
						return returnFail(localInfo.getSv());
					}
					
					positioning.setDefData(strData.toString());
					
					Collections.sort(signals,new MyComparator());//排序，根据rssi的值排序，最小的在前
					
					List<List<IBeaconSignal>> listSignals = SelectList.getList(signals, 3);
					List<GISLocation> giss = new ArrayList<GISLocation>();
					for(int i=0;i<listSignals.size();i++){
						IBeaconApi client = new IBeaconApi();
						BeaconGroupInput inputGroup = new BeaconGroupInput();
						IBeacon ib1 = HsqldbUtil.getIbeacon(listSignals.get(i).get(0).getUuid());
						IBeacon ib2 = HsqldbUtil.getIbeacon(listSignals.get(i).get(1).getUuid());
						IBeacon ib3 = HsqldbUtil.getIbeacon(listSignals.get(i).get(2).getUuid());
						inputGroup.setRssi1(listSignals.get(i).get(0).getRssi());
						inputGroup.setTxPower1(listSignals.get(i).get(0).getPower());
						inputGroup.setLatitude1(ib1.getLatitude());
						inputGroup.setLongitude1(ib1.getLongitude());
						
						inputGroup.setRssi2(listSignals.get(i).get(1).getRssi());
						inputGroup.setTxPower2(listSignals.get(i).get(1).getPower());
						inputGroup.setLatitude2(ib2.getLatitude());
						inputGroup.setLongitude2(ib2.getLongitude());
						
						inputGroup.setRssi3(listSignals.get(i).get(2).getRssi());
						inputGroup.setTxPower3(listSignals.get(i).get(2).getPower());
						inputGroup.setLatitude3(ib3.getLatitude());
						inputGroup.setLongitude3(ib3.getLongitude());
						
						inputGroup.setCoefficient1(59.0);
						inputGroup.setCoefficient2(2.0);
						
						try {
							GISLocation location = client.obtainLocationUsingPOST(inputGroup);
							giss.add(location);
						} catch (ApiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(giss.size()>0){
						double latsum = 0;
						double logsum = 0;
						for (GISLocation gis : giss) {
							latsum += gis.getLatitude();
							logsum += gis.getLongitude();
						}
						positioning.setLatitude(latsum/giss.size());
						positioning.setLongitude(logsum/giss.size());
						positioning.setPositioningTime(new Timestamp(System.currentTimeMillis()));
//						HsqldbUtil.updatePositioning(positioning);
						
						String area = IsInArea.whereArea(logsum/giss.size(), latsum/giss.size());
						positioning.setArea(area);
						localInfo.setArea(area);
						
//						positioning.setArea(IsInArea.whereArea(location.getLongitude(), location.getLatitude()));
//						localInfo.setArea(IsInArea.whereArea(location.getLongitude(), location.getLatitude()));
						HsqldbUtil.addPositioning(positioning);
//						HsqldbUtil.updateLocalizerArea(localInfo);
						System.out.println("sn------------------------"+localInfo.getSv());
						localService.updateLocalInfo(localInfo);//更新区域和电量信息
						System.out.println("ibeacon保存成功");
					}
					
					
//					IBeaconApi client =  new IBeaconApi();
//					BeaconGroupInput inputGroup = new BeaconGroupInput();
//					IBeacon ib1 = HsqldbUtil.getIbeacon(signals.get(0).getUuid());
//					IBeacon ib2 = HsqldbUtil.getIbeacon(signals.get(1).getUuid());
//					IBeacon ib3 = HsqldbUtil.getIbeacon(signals.get(2).getUuid());
//					inputGroup.setRssi1(signals.get(0).getRssi());
//					inputGroup.setTxPower1(signals.get(0).getPower());
////					inputGroup.setTxPower1(1);
//					inputGroup.setLatitude1(ib1.getLatitude());
//					inputGroup.setLongitude1(ib1.getLongitude());
//					inputGroup.setRssi2(signals.get(1).getRssi());
//					inputGroup.setTxPower2(signals.get(1).getPower());
////					inputGroup.setTxPower2(1);
//					inputGroup.setLatitude2(ib2.getLatitude());
//					inputGroup.setLongitude2(ib2.getLongitude());
//					inputGroup.setRssi3(signals.get(2).getRssi());
//					inputGroup.setTxPower3(signals.get(2).getPower());
////					inputGroup.setTxPower3(1);
//					inputGroup.setLatitude3(ib3.getLatitude());
//					inputGroup.setLongitude3(ib3.getLongitude());
//					
//					inputGroup.setCoefficient1(59.0);//59
//					inputGroup.setCoefficient2(2.0);//2
//					
//					System.out.println("rssi:"+inputGroup.getRssi1()+"/"+inputGroup.getRssi2()+"/"+inputGroup.getRssi3());
//					System.out.println("txpower:"+inputGroup.getTxPower1()+"/"+inputGroup.getTxPower2()+"/"+inputGroup.getTxPower3());
//					System.out.println("longitude:"+inputGroup.getLongitude1()+"/"+inputGroup.getLongitude2()+"/"+inputGroup.getLongitude3());
//					System.out.println("latitude:"+inputGroup.getLatitude1()+"/"+inputGroup.getLatitude2()+"/"+inputGroup.getLatitude3());
//					
//					try {
//						GISLocation location = client.obtainLocationUsingPOST(inputGroup);
//						System.out.println(location.getLatitude()+":" + location.getLongitude());
//						positioning.setLatitude(location.getLongitude());
//						positioning.setLongitude(location.getLatitude());
//						positioning.setPositioningTime(new Timestamp(System.currentTimeMillis()));
////						HsqldbUtil.updatePositioning(positioning);
//						positioning.setArea(IsInArea.whereArea(location.getLongitude(), location.getLatitude()));
//						localInfo.setArea(IsInArea.whereArea(location.getLongitude(), location.getLatitude()));
//						HsqldbUtil.addPositioning(positioning);
////						HsqldbUtil.updateLocalizerArea(localInfo);
//						System.out.println("sn------------------------"+localInfo.getSv());
//						localService.updateLocalInfo(localInfo);//更新区域和电量信息
//						System.out.println("ibeacon保存成功");
//					} catch (ApiException e) {
//						e.printStackTrace();
//					}
					
					
					
					break;
					
				case "06":
					positioning.setPositioningMode("北斗");
					System.out.println("GPS---------------------------------------------------------------------------------");
					int dataLength = Integer.parseInt(strs[26],16);
					Vdatas = new StringBuffer();
					for(int i =27;i<strs.length-1;i++){
						Vdatas.append(strs[i]);
					}
					int dataActiveLength = Vdatas.toString().length()/2;
					if(dataLength==dataActiveLength){
						String data = Vdatas.toString();
						String isLocation = data.substring(0,2);
						gps.setIsLocation(isLocation);
						System.out.println("isLocation:"+isLocation);
						
						
//						Date date = new Date();
//						date.setTime(Integer.parseInt(data.substring(2,14),16));
//						System.out.println("date:"+date);
						
						
						String we = data.substring(14,16);
						double longitude = 0.0;//经度
//						String longitudeDD = data.substring(16,18);
						int longitudeDD = Integer.parseInt(data.substring(16,18),16);
//						String longitudeMM = data.substring(18,24);
//						int longitudeMM = Integer.parseInt(data.substring(18,24),16);
						int mm = Integer.parseInt(data.substring(18,20),16);
						int mm1 = Integer.parseInt(data.substring(20,22),16);
						int mm2 = Integer.parseInt(data.substring(22,24),16);
						double longitudeMM = Double.parseDouble(mm+"."+mm1+mm2)/60.0;
						longitude = longitudeDD+longitudeMM;
//						longitude = Double.parseDouble(longitudeDD+"."+longitudeMM);
//						gps.setLongitude(longitude);
						positioning.setLongitude(longitude);
						System.out.println("longitude:"+longitude);
						
//						System.out.println("longitude:"+we+":"+longitude);
						double latitude = 0.0;//纬度
						String ns = data.substring(24,26);
//						String latitudeDD = data.substring(26,28);
						int latitudeDD = Integer.parseInt(data.substring(26,28),16);
//						String latitudeMM = data.substring(28,34);
//						int latitudeMM = Integer.parseInt(data.substring(28,34),16);
						int lamm = Integer.parseInt(data.substring(28,30),16);
						int lamm1 = Integer.parseInt(data.substring(30,32),16);
						int lamm2 = Integer.parseInt(data.substring(32,34),16);
						double latitudeMM = Double.parseDouble(lamm+"."+lamm1+lamm2)/60.0;
						latitude = latitudeDD + latitudeMM;
//						latitude = Double.parseDouble(latitudeDD+"."+latitudeMM);
//						gps.setLatitude(latitude);
						positioning.setLatitude(latitude);
						System.out.println("latiude:"+latitude);
						
//						System.out.println("latitude:"+ns+":"+latitude);
						int altitude = Integer.parseInt(data.substring(34,38),16);//海拔
						System.out.println("altitude:"+altitude+"m");
						int direction = Integer.parseInt(data.substring(38,40),16);//方向:正北为0,为世纪芳香的/2
						gps.setDirection(direction);
						System.out.println("direction:"+direction);
						int speed = Integer.parseInt(data.substring(40,42),16);
						gps.setSpeed(speed);
						System.out.println("speed:"+speed+"KM/H");
						positioning.setPositioningTime(new Timestamp(System.currentTimeMillis()));
						localInfo.setArea(IsInArea.whereArea(positioning.getLongitude(), positioning.getLatitude()));
						
						positioning.setArea(IsInArea.whereArea(positioning.getLongitude(), positioning.getLatitude()));
						
//						HsqldbUtil.updatePositioning(positioning);
						HsqldbUtil.addPositioning(positioning);//添加位置信息
//						HsqldbUtil.updateLocalizerArea(localInfo);//更新定位器区域信息
						localService.updateLocalInfo(localInfo);//更新区域和电量信息
						System.out.println("gps保存成功");
						
						
					}
					break;
					
				default:
					break;
				}
				
				returnString.append("090101");
				returnString.append("7d");
				
				break;

			case "01":
					//注册/心跳数据
					returnString.append("81");
					dataString.setDataLength("0003");
					returnString.append("0003");
					returnString.append("090101");
					returnString.append("7d");
					break;
				
				
			case "07":
				String loadLength = strs[15]+strs[16];
				if("0030".equals(loadLength)){
					LocalizerInfo info = new LocalizerInfo();
					StringBuffer loadData = new StringBuffer();
					for(int i=17;i<=64;i++){
						loadData.append(strs[i]);
					}
					String number = null;
					System.out.println(loadData.toString());
					
					for(int i=0;i<loadData.toString().length()/16;i++){
						String loadStr = loadData.toString().substring(i*16,i*16+16);
						if("0010".equals(loadStr.substring(0,2))){
							number = loadStr.substring(4,13);
							int runTime = Integer.parseInt(loadStr.substring(13,17),16);
							info.setRunTime(String.valueOf(runTime));
						}else if("0011".equals(loadStr.substring(0,2))){
							int staticTime = Integer.parseInt(loadStr.substring(13,17),16);
							info.setStaticTime(String.valueOf(staticTime));
						}else if("0012".equals(loadStr.substring(0,2))){
							int gpsTimeOut = Integer.parseInt(loadStr.substring(13,17),16);
							info.setGpsTimeOut(String.valueOf(gpsTimeOut));
						}else if("0013".equals(loadStr.substring(0,2))){
							int loraSleepTime = Integer.parseInt(loadStr.substring(13,17),16);
							info.setLoraSleepTime(String.valueOf(loraSleepTime));
						}else if("0014".equals(loadStr.substring(0,2))){
							int ibeaconEffectNum = Integer.parseInt(loadStr.substring(13,17),16);
							info.setIbeaconEffectNum(String.valueOf(ibeaconEffectNum));
						}else if("0015".equals(loadStr.subSequence(0, 2))){
							int ibeaconTimeOut = Integer.parseInt(loadStr.substring(13,17),16);
							info.setIbeaconTimeOut(String.valueOf(ibeaconTimeOut));
						}else{
							
						}
						StringBuffer numberStr = new StringBuffer();
						if(number!=null){
							numberStr.append(Integer.parseInt((number.substring(0,2)),16));
							numberStr.append(".");
							numberStr.append(Integer.parseInt((number.substring(2,4)),16));
							numberStr.append(".");
							numberStr.append(Integer.parseInt((number.substring(4,6)),16));
							numberStr.append(".");
							numberStr.append(Integer.parseInt((number.substring(6,8)),16));
						}
						info.setNumber(numberStr.toString());
						Result result = localService.updateInfo(info);
						if(result.isStates()){
							ChannelServer.setString("成功");
						}else{
							ChannelServer.setString("失败");
						}
					}
				}
				
				
//				if("0020".equals(loadLength)){
//					LocalizerInfo info = new LocalizerInfo();
//					StringBuffer loadData = new StringBuffer();
//					for(int i=17;i<=48;i++){
//						loadData.append(strs[i]);
//					}
//					String number = null;
//					System.out.println(loadData.toString());
//					for(int i=0;i<loadData.toString().length()/16;i++){
//						String loadStr = loadData.toString().substring(i*16,i*16+16);
//						if("04".equals(loadStr.substring(0,2))){
//							number = loadStr.substring(4,12);
//							int runTime = Integer.parseInt(loadStr.substring(13,16),16);
//							info.setRunTime(String.valueOf(runTime));
//							continue;
//						}else if("03".equals(loadStr.substring(0,2))){
//							int staticTime = Integer.parseInt(loadStr.substring(13,16),16);
//							info.setStaticTime(String.valueOf(staticTime));
//							continue;
//						}else if("05".equals(loadStr.substring(0,2))){
//							int gpsTimeOut = Integer.parseInt(loadStr.substring(13,16),16);
//							info.setGpsTimeOut(String.valueOf(gpsTimeOut));
//							continue;
//						}else if("06".equals(loadStr.subSequence(0, 2))){
//							int loraSleepTime = Integer.parseInt(loadStr.substring(13,16),16);
//							info.setLoraSleepTime(String.valueOf(loraSleepTime));
//							continue;
//						}else{
//							continue;
//						}
//					}
//					StringBuffer numberStr = new StringBuffer();
//					if(number!=null){
//						numberStr.append(Integer.parseInt((number.substring(0,2)),16));
//						numberStr.append(".");
//						numberStr.append(Integer.parseInt((number.substring(2,4)),16));
//						numberStr.append(".");
//						numberStr.append(Integer.parseInt((number.substring(4,6)),16));
//						numberStr.append(".");
//						numberStr.append(Integer.parseInt((number.substring(6,8)),16));
//					}
//					info.setNumber(numberStr.toString());
//					System.out.println(info.getNumber());
//					System.out.println(info.getRunTime());
//					System.out.println(info.getStaticTime());
//					System.out.println(info.getGpsTimeOut());
//					System.out.println(info.getLoraSleepTime());
//					System.out.println(info.getIbeaconEffectNum());
//					System.out.println(info.getIbeaconTimeOut());
//					Result result = localService.updateInfo(info);
//					System.out.println(result.isStates());
//					System.out.println(result.getMessage());
//					if(result.isStates()){
//						ChannelServer.setString("同步成功");
//					}
//					
//				}else{
//					//服务器读取/修改参数
//					if(strs[17].equals("09")&&strs[19].equals("04")){
//						ChannelServer.setString("修改失败");
//					}else if(strs[17].equals("09")&&strs[19].equals("03")){
//						ChannelServer.setString("修改失败,未知的IBeacon");
//					}else if(strs[17].equals("09")&&strs[19].equals("02")){
//						ChannelServer.setString("修改失败,未知的Lora模块");
//					}else if(strs[17].equals("09")&&strs[19].equals("05")){
//						ChannelServer.setString("修改失败,系统忙");
//					}else if(strs[17].equals("09")&&strs[19].equals("01")){
//						ChannelServer.setString("设置成功");
//					}else{
////						ChannelServer.setString("读取成功");
//						StringBuffer return07 = new StringBuffer();
//						for(int i=0;i<args.length;i++){
//							return07.append(args[i]);
//						}
//						ChannelServer.setString(return07.toString());
//					}
//				}
				
				
				returnString.setLength(0);
				
				break;
			case "09":
				//远程控制
				if(strs[17].equals("09")&&strs[19].equals("01")){
					ChannelServer.setString("设置成功");
					System.out.println(ChannelServer.getString());
				}else if(strs[17].equals("09")&&strs[19].equals("04")){
					ChannelServer.setString("设置失败");
				}else{
					ChannelServer.setString("设置失败");
				}
				returnString.setLength(0);
				
				break;
			default:
				break;
			}
			
			
		}
		return returnString.toString().toUpperCase();
	}
	
	
	private static String returnFail(String sn){
		StringBuffer returnFail = new StringBuffer();
		returnFail.append("7b");
		returnFail.append("0000");
		returnFail.append(sn);
		returnFail.append("85");
		returnFail.append("0003");
		returnFail.append("09");
		returnFail.append("01");
		returnFail.append("04");
		returnFail.append("7d");
		return returnFail.toString();
	}
	
	private static void writeStr(String str,List<IBeaconSignal> infos){
		File file = new File("C:/firecontrol/shangqilog.txt");
		PrintWriter pw = null;
		StringBuffer fileStr = new StringBuffer();
		try {
			pw = new PrintWriter(new FileOutputStream(file,true));
			fileStr.append(str);
			fileStr.append(":");
			for (IBeaconSignal info : infos) {
				fileStr.append(info.toString());
				fileStr.append("--");
			}
			if(infos.size()==0){
				pw.println(fileStr.toString()+"--"+new Timestamp(System.currentTimeMillis()));
			}else{
				pw.println(fileStr.toString()+new Timestamp(System.currentTimeMillis()));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pw!=null){
				pw.close();
			}
		}
		
	}
	
	
}
