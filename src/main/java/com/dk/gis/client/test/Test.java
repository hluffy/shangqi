package com.dk.gis.client.test;

import com.dk.gis.client.ApiException;
import com.dk.gis.client.api.IBeaconApi;
import com.dk.gis.client.model.BeaconGroupInput;
import com.dk.gis.client.model.BeaconInput;
import com.dk.gis.client.model.GISLocation;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * 计算单个iBeacon的距离
		 */		
		IBeaconApi client =  new IBeaconApi();
		BeaconInput input = new BeaconInput();
		input.setRssi(-75.0);
		input.setTxPower(0);
		try {
			double distance = client.obtainDistanceUsingPOST(input);
			System.out.println(distance);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * 通过三个ibeacon基站进行位置定位
		 */
		BeaconGroupInput inputGroup = new BeaconGroupInput();
		inputGroup.setRssi1(98.0);
		inputGroup.setTxPower1(0);
		inputGroup.setLatitude1(35.80503);
		inputGroup.setLongitude1(123.67);
		inputGroup.setRssi2(98.0);
		inputGroup.setTxPower2(0);
		inputGroup.setLatitude2(35.80602);
		inputGroup.setLongitude2(123.67);
		inputGroup.setRssi3(98.0);
		inputGroup.setTxPower3(0);
		inputGroup.setLatitude3(35.80701);
		inputGroup.setLongitude3(123.67);
		inputGroup.setCoefficient1(59.5);//59
		inputGroup.setCoefficient2(3.0);//2
		
		
		try {
			GISLocation location = client.obtainLocationUsingPOST(inputGroup);
			System.out.println(location.getLatitude()+":" + location.getLongitude());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
