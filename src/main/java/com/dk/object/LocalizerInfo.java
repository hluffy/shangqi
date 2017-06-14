package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class LocalizerInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 620322497324876030L;
	private String number;//编号
	private String staticTime;//静止时上报时间间隔
	private String runTime;//运行时上报时间间隔
	private Timestamp time;//时间
	private String log;//经度
	private String lat;//纬度
	
	private String area;
	private Integer ele;
	private int sumCount;
	private String sv;
	private String svStr;
	
	private String timeStr;
	private Integer page;
	private String numberDef;
	
	private String gpsTimeOut;//gps超时时间
	private String loraSleepTime;//lora休眠时间
	
	private String ibeaconEffectNum;//有效的ibeacon基站数量，默认为3
	private String ibeaconTimeOut;//ibeacon超时时间
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStaticTime() {
		return staticTime;
	}
	public void setStaticTime(String staticTime) {
		this.staticTime = staticTime;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getEle() {
		return ele;
	}
	public void setEle(Integer ele) {
		this.ele = ele;
	}
	public String getNumberDef() {
		return numberDef;
	}
	public void setNumberDef(String numberDef) {
		this.numberDef = numberDef;
	}
	public String getSv() {
		return sv;
	}
	public void setSv(String sv) {
		this.sv = sv;
	}
	public String getSvStr() {
		return svStr;
	}
	public void setSvStr(String svStr) {
		this.svStr = svStr;
	}
	public String getGpsTimeOut() {
		return gpsTimeOut;
	}
	public void setGpsTimeOut(String gpsTimeOut) {
		this.gpsTimeOut = gpsTimeOut;
	}
	public String getLoraSleepTime() {
		return loraSleepTime;
	}
	public void setLoraSleepTime(String loraSleepTime) {
		this.loraSleepTime = loraSleepTime;
	}
	public String getIbeaconEffectNum() {
		return ibeaconEffectNum;
	}
	public void setIbeaconEffectNum(String ibeaconEffectNum) {
		this.ibeaconEffectNum = ibeaconEffectNum;
	}
	public String getIbeaconTimeOut() {
		return ibeaconTimeOut;
	}
	public void setIbeaconTimeOut(String ibeaconTimeOut) {
		this.ibeaconTimeOut = ibeaconTimeOut;
	}
	

}
