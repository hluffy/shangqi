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
		//CAR
		String carArea = "[{'lat':30.8866072,'lng':121.8266349},{'lat':30.8863413,'lng':121.8266332},{'lat':30.8863382,'lng':121.8269272},{'lat':30.886173,'lng':121.8269312},{'lat':30.886177,'lng':121.8272759},{'lat':30.8866052,'lng':121.8272786}]";
		//DVT
		String dvtArea = "[{'lat':30.8863399,'lng':121.8266335},{'lat':30.8855217,'lng':121.8266302},{'lat':30.8855203,'lng':121.8268727},{'lat':30.8863387,'lng':121.8268649}]";
		//板链返修区
		String blArea = "[{'lat':30.8855199,'lng':121.8266302},{'lat':30.8851306,'lng':121.8266318},{'lat':30.8851354,'lng':121.827227},{'lat':30.885517,'lng':121.8272303}]";
		//内饰二
		String nseArea = "[{'lat':30.8851211,'lng':121.8267126},{'lat':30.8837422,'lng':121.8267274},{'lat':30.883733,'lng':121.8269151},{'lat':30.8851199,'lng':121.8269111}]";
		//底盘三
		String dpsArea = "[{'lat':30.8861707,'lng':121.8270747},{'lat':30.8856666,'lng':121.8270788},{'lat':30.8856666,'lng':121.827186},{'lat':30.8861661,'lng':121.8271807}]";
		//底盘一二
		String dpyeArea = "[{'lat':30.8855032,'lng':121.8287444},{'lat':30.885128,'lng':121.8287417},{'lat':30.8851303,'lng':121.8290368},{'lat':30.8855009,'lng':121.8290421}]";
		//报交区
		String bjArea = "[{'lat':30.8867214,'lng':121.8271257},{'lat':30.8866604,'lng':121.8271264},{'lat':30.8866604,'lng':121.8272544},{'lat':30.886722,'lng':121.8272544}]";
		//滞留区
		String zlArea = "[{'lat':30.8866564,'lng':121.8272477},{'lat':30.8866161,'lng':121.8272481},{'lat':30.8866158,'lng':121.8273409},{'lat':30.8866585,'lng':121.8273413}]";
		//物流区
		String wlArea = "[{'lat':30.8900769,'lng':121.8267918},{'lat':30.8883551,'lng':121.8267864},{'lat':30.8883413,'lng':121.8271351},{'lat':30.8867358,'lng':121.8271244},{'lat':30.8867312,'lng':121.827583},{'lat':30.8889283,'lng':121.8275803},{'lat':30.8889076,'lng':121.8296939},{'lat':30.8900953,'lng':121.8296832}]";
		if(isInArea(Alon,Alat,carArea)){
			area = "CAR";
		}else if(isInArea(Alon,Alat,dvtArea)){
			area = "DVT";
		}else if(isInArea(Alon,Alat,blArea)){
			area = "板链返修区";
		}else if(isInArea(Alon,Alat,nseArea)){
			area = "内饰二";
		}else if(isInArea(Alon,Alat,dpsArea)){
			area = "底盘三";
		}else if(isInArea(Alon,Alat,dpyeArea)){
			area = "底盘一二";
		}else if(isInArea(Alon,Alat,bjArea)){
			area = "报交区";
		}else if(isInArea(Alon,Alat,zlArea)){
			area = "滞留区";
		}else if(isInArea(Alon,Alat,wlArea)){
			area = "wlArea";
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
