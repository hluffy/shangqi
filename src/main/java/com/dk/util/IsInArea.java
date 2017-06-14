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
		String carArea = "[{'lat':30.8866104,'lng':121.8266308},{'lat':30.8862329,'lng':121.8266255},{'lat':30.8862329,'lng':121.8270063},{'lat':30.886165,'lng':121.8270037},{'lat':30.8861615,'lng':121.8272813},{'lat':30.8866092,'lng':121.8272786}]";
		//DVT
		String dvtArea = "[{'lat':30.8862352,'lng':121.8266281},{'lat':30.8851671,'lng':121.8266281},{'lat':30.8851684,'lng':121.8268454},{'lat':30.8862352,'lng':121.8268427}]";
		//板链返修区
		String blArea = "[{'lat':30.885296,'lng':121.8268454},{'lat':30.8851717,'lng':121.8268481},{'lat':30.8851694,'lng':121.827229},{'lat':30.885296,'lng':121.8272263}]";
		//内饰一
		String nseArea = "[{'lat':30.8851671,'lng':121.8269339},{'lat':30.884152,'lng':121.8269339},{'lat':30.8841497,'lng':121.8270653},{'lat':30.8851717,'lng':121.8270573}]";
		//底盘三
		String dpsArea = "[{'lat':30.8862352,'lng':121.8268427},{'lat':30.8862352,'lng':121.827001},{'lat':30.8861615,'lng':121.8270037},{'lat':30.8861615,'lng':121.8270358},{'lat':30.885296,'lng':121.8270385},{'lat':30.8852983,'lng':121.8268427}]";
		//底盘一二
		String dpyeArea = "[{'lat':30.8852879,'lng':121.8289322},{'lat':30.8851752,'lng':121.8289335},{'lat':30.8851775,'lng':121.8290462},{'lat':30.8852914,'lng':121.8290462}]";
		//报交区
		String bjArea = "[{'lat':30.8867246,'lng':121.827126},{'lat':30.8866621,'lng':121.8271254},{'lat':30.8866621,'lng':121.8272548},{'lat':30.8867243,'lng':121.8272541}]";
		//滞留区
		String zlArea = "[{'lat':30.886657,'lng':121.8272672},{'lat':30.886619,'lng':121.8272679},{'lat':30.886619,'lng':121.8273282},{'lat':30.8866573,'lng':121.8273275}]";
		//物流区
		String wlArea = "[{'lat':30.8900861,'lng':121.8267864},{'lat':30.8883551,'lng':121.8267757},{'lat':30.8883643,'lng':121.8274462},{'lat':30.888926,'lng':121.8274516},{'lat':30.888926,'lng':121.8296617},{'lat':30.8900907,'lng':121.8296564}]";
		if(isInArea(Alon,Alat,carArea)){
			area = "CARE";
		}else if(isInArea(Alon,Alat,dvtArea)){
			area = "DVT";
		}else if(isInArea(Alon,Alat,blArea)){
			area = "板链返修区";
		}else if(isInArea(Alon,Alat,nseArea)){
			area = "内饰一";
		}else if(isInArea(Alon,Alat,dpsArea)){
			area = "底盘三";
		}else if(isInArea(Alon,Alat,dpyeArea)){
			area = "底盘一二";
		}else if(isInArea(Alon,Alat,bjArea)){
			area = "报交区";
		}else if(isInArea(Alon,Alat,zlArea)){
			area = "滞留区";
		}else if(isInArea(Alon,Alat,wlArea)){
			area = "物流区";
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
