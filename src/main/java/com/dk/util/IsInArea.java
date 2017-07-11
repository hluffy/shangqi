package com.dk.util;

import net.sf.json.JSONArray;

public class IsInArea {
	public static void main(String[] args) {
//		JSONArray jsons = new JSONArray();
//		JSONObject json = new JSONObject();
//		json.put("lat", 30.8907444);
//		json.put("lng", 121.8267596);
//		jsons.add(json);
//		json = new JSONObject();
//		json.put("lat", 30.8886544);
//		json.put("lng", 121.8268025);
//		jsons.add(json);
//		json = new JSONObject();
//		json.put("lat", 30.888682);
//		json.put("lng", 121.8296885);
//		jsons.add(json);
//		json = new JSONObject();
//		json.put("lat", 30.8907352);
//		json.put("lng", 121.8296885);
//		jsons.add(json);
//		System.out.println(jsons.size());
//		System.out.println(isInArea(121.8281329,30.889727,jsons));
//		System.out.println(isInArea(121.826,30.89,jsons));
		
		String banArea = "[{'lat':30.8907444,'lng':121.8267596},{'lat':30.8886544,'lng':121.8268025},{'lat':30.888682,'lng':121.8296885},{'lat':30.8907352,'lng':121.8296885}]";
		System.out.println(isInArea(121.8281329,30.889727,banArea));
		System.out.println(isInArea(121.826,30.89,banArea));
		
	}
	
	public static String whereArea(double Alon,double Alat){
		String area = "";
		//CARE
		String carArea = "[{'lat':30.8866138,'lng':121.8269695},{'lat':30.8866132,'lng':121.8272799},{'lat':30.8862029,'lng':121.8272796},{'lat':30.8862015,'lng':121.8269711}]";
		//DVT返修区
		String dvtArea = "[{'lat':30.8860992,'lng':121.8266307},{'lat':30.8860992,'lng':121.8267125},{'lat':30.8851775,'lng':121.8267121},{'lat':30.8851776,'lng':121.8266307}]";
		//板链返修区
		String blArea = "[{'lat':30.8851772,'lng':121.8269735},{'lat':30.8852733,'lng':121.8269738},{'lat':30.8852753,'lng':121.8272028},{'lat':30.8851795,'lng':121.8272045}]";
		//内饰门线
		String nseArea = "[{'lat':30.8851769,'lng':121.826895},{'lat':30.8851772,'lng':121.827058},{'lat':30.8841428,'lng':121.827064},{'lat':30.8841428,'lng':121.8268977}]";
		//底盘三
		String dpsArea = "[{'lat':30.885273,'lng':121.8269738},{'lat':30.885275,'lng':121.8272031},{'lat':30.8862029,'lng':121.8272031},{'lat':30.8862015,'lng':121.8269711}]";
		//底盘一二
		String dpyeArea = "[{'lat':30.8851694,'lng':121.8289429},{'lat':30.8851691,'lng':121.8290441},{'lat':30.8853121,'lng':121.8290441},{'lat':30.8853121,'lng':121.8289426}]";
		//报交区
		String bjArea = "[{'lat':30.8867246,'lng':121.827126},{'lat':30.8866621,'lng':121.8271254},{'lat':30.8866621,'lng':121.8272548},{'lat':30.8867243,'lng':121.8272541}]";
		//总装滞留区
		String zlArea = "[{'lat':30.8868564,'lng':121.8271093},{'lat':30.8866515,'lng':121.8271093},{'lat':30.8866518,'lng':121.8272652},{'lat':30.8868578,'lng':121.8272648}]";
		//物流区
		String wl1Area = "[{'lat':30.8874482,'lng':121.8267596},{'lat':30.8874482,'lng':121.8271565},{'lat':30.8883321,'lng':121.8271458},{'lat':30.8883321,'lng':121.827575},{'lat':30.888903,'lng':121.8275642},{'lat':30.8889122,'lng':121.8296778},{'lat':30.890772,'lng':121.8296778},{'lat':30.8907536,'lng':121.8267703}]";
		//油漆返修区
		String yqArea = "[{'lat':30.8866145,'lng':121.8266318},{'lat':30.8866142,'lng':121.8267153},{'lat':30.8863064,'lng':121.8267118},{'lat':30.8863064,'lng':121.8266327}]";
		//车身返修区
		String csfxArea = "[{'lat':30.8863065,'lng':121.8266327},{'lat':30.8863065,'lng':121.8267118},{'lat':30.8860993,'lng':121.8267126},{'lat':30.8860993,'lng':121.8266307}]";
		//检测区
		String qcArea = "[{'lat':30.8851773,'lng':121.8267123},{'lat':30.8851775,'lng':121.826974},{'lat':30.8866132,'lng':121.8269701},{'lat':30.8866138,'lng':121.826715}]";
		//车身滞留区
		String cszlArea = "[{'lat':30.8867994,'lng':121.8266305},{'lat':30.8866168,'lng':121.8266315},{'lat':30.8866165,'lng':121.8267163},{'lat':30.8867995,'lng':121.8267163}]";
		//扣车区
		String kcArea = "[{'lat':30.8871012,'lng':121.8263713},{'lat':30.8871011,'lng':121.8264758},{'lat':30.8868258,'lng':121.8264775},{'lat':30.8868255,'lng':121.8263719}]";
		//物流2区
//		String wl2Area = "[{'lat':30.891085,'lng':121.8280363},{'lat':30.8910666,'lng':121.8300319},{'lat':30.8946757,'lng':121.8300319},{'lat':30.8946572,'lng':121.8265128},{'lat':30.8925029,'lng':121.8265342},{'lat':30.8924845,'lng':121.8280577}]";
		if(isInArea(Alon,Alat,carArea)){
			area = "CARE";
		}else if(isInArea(Alon,Alat,dvtArea)){
			area = "DVT返修区";
		}else if(isInArea(Alon,Alat,blArea)){
			area = "板链返修区";
		}else if(isInArea(Alon,Alat,nseArea)){
			area = "内饰门线";
		}else if(isInArea(Alon,Alat,dpsArea)){
			area = "底盘三";
		}else if(isInArea(Alon,Alat,dpyeArea)){
			area = "底盘一二";
		}else if(isInArea(Alon,Alat,bjArea)){
			area = "报交区";
		}else if(isInArea(Alon,Alat,zlArea)){
			area = "总装滞留区";
		}else if(isInArea(Alon,Alat,wl1Area)){
			area = "物流区";
		}else if(isInArea(Alon,Alat,yqArea)){
			area = "油漆返修区";
		}else if(isInArea(Alon,Alat,csfxArea)){
			area = "车身返修区";
		}else if(isInArea(Alon,Alat,qcArea)){
			area = "检测区";
		}else if(isInArea(Alon,Alat,cszlArea)){
			area = "车身滞留区";
		}else if(isInArea(Alon,Alat,kcArea)){
			area = "扣车区";
//		}else if(isInArea(Alon,Alat,wl2Area)){
//			area = "物流2区";
		}else{
			area = "其他";
		}
		
		return area;
	}
	
	public static boolean isInArea(double ALon,double ALat,String str){
		JSONArray APoints = JSONArray.fromObject(str);
		int iSum = 0,  
		        iCount;  
		    double dLon1, dLon2, dLat1, dLat2, dLon;  
		    if (APoints.size() < 3) return false;  
		    iCount = APoints.size();  
		    for (int i = 0; i < iCount; i++) {  
		        if (i == iCount - 1) {  
		            dLon1 = APoints.getJSONObject(i).getDouble("lng");
		            dLat1 = APoints.getJSONObject(i).getDouble("lat");
		            dLon2 = APoints.getJSONObject(0).getDouble("lng");
		            dLat2 = APoints.getJSONObject(0).getDouble("lat");
		        } else {  
		            dLon1 = APoints.getJSONObject(i).getDouble("lng");
		            dLat1 = APoints.getJSONObject(i).getDouble("lat");
		            dLon2 = APoints.getJSONObject(i+1).getDouble("lng");
		            dLat2 = APoints.getJSONObject(i+1).getDouble("lat");
		        }  
		        //以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上  
		        if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {  
		            if (Math.abs(dLat1 - dLat2) > 0) {  
		                //得到 A点向左射线与边的交点的x坐标：  
		                dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);  
		                if (dLon < ALon)  
		                    iSum++;  
		            }  
		        }  
		    }  
		    if (iSum % 2 != 0)  
		        return true;  
		    return false; 
	}

}
