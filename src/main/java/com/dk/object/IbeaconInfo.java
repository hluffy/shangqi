package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class IbeaconInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2759576085008973881L;
	private Integer minor;
	private String uuid;
	private Double log;//经度
	private Double lat;//纬度
	private Integer ele;//电量
	private String area;//区域
	private Timestamp lastUseTime;//最后使用时间
	private String lastUseTimeStr;
	
	private String startTimeStr;//开始时间
	private String endTimeStr;//结束时间
	
	private boolean isLow;
	
	public boolean isLow() {
		return isLow;
	}
	public void setLow(boolean isLow) {
		this.isLow = isLow;
	}
	private Integer page;
	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Double getLog() {
		return log;
	}
	public void setLog(Double log) {
		this.log = log;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Integer getEle() {
		return ele;
	}
	public void setEle(Integer ele) {
		this.ele = ele;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Timestamp getLastUseTime() {
		return lastUseTime;
	}
	public void setLastUseTime(Timestamp lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	public String getLastUseTimeStr() {
		return lastUseTimeStr;
	}
	public void setLastUseTimeStr(String lastUseTimeStr) {
		this.lastUseTimeStr = lastUseTimeStr;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	

}
