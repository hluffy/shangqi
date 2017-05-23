package com.dk.util;

import java.util.Comparator;

import com.dk.netty.IBeaconSignal;

public class MyComparator implements Comparator<IBeaconSignal>{

	@Override
	public int compare(IBeaconSignal o1, IBeaconSignal o2) {
		// TODO Auto-generated method stub
		return o1.getRssi().compareTo(o2.getRssi());
	}

}
