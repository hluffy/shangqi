package com.dk.netty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;



public class Positioning implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9163600704367181854L;
	private String equipmentNum;//设备编号
	private String frameNum;//车架号
	private Integer electricity;//电量
	private Double longitude;//经度
	private Double latitude;//纬度
	private String positioningMode;//定位模式
	private Timestamp positioningTime;//定位时间
	
	private String startTime;
	private String endTime;
	private String positioningTimeStr;
	
	private String positioningTimeStr1;
	private int page;
	private int rows;
	
	private String area;
	
	private String defData;
	
	
	public Positioning(){
		
	}
	public Positioning(String equipmentNum,String frameNum,Integer electricity,Double longitude,
			Double latitude,String positioningMode,Timestamp positioningTime,String area){
		this.equipmentNum = equipmentNum;
		this.frameNum = frameNum;
		this.electricity = electricity;
		this.longitude = longitude;
		this.latitude = latitude;
		this.positioningMode = positioningMode;
		this.positioningTime = positioningTime;
		this.area = area;
	}
	
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getFrameNum() {
		return frameNum;
	}
	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}
	public Integer getElectricity() {
		return electricity;
	}
	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getPositioningMode() {
		return positioningMode;
	}
	public void setPositioningMode(String positioningMode) {
		this.positioningMode = positioningMode;
	}
	public Timestamp getPositioningTime() {
		return positioningTime;
	}
	public void setPositioningTime(Timestamp positioningTime) {
		this.positioningTime = positioningTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPositioningTimeStr() {
		if(positioningTime!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(positioningTime);
		}
		return null;
		
	}
//	public String getPositioningTimeStr() {
//		return positioningTimeStr;
//	}
	public void setPositioningTimeStr(String positioningTimeStr) {
		this.positioningTimeStr = positioningTimeStr;
	}
	public String getPositioningTimeStr1() {
		return positioningTimeStr1;
	}
	public void setPositioningTimeStr1(String positioningTimeStr1) {
		this.positioningTimeStr1 = positioningTimeStr1;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDefData() {
		return defData;
	}
	public void setDefData(String defData) {
		this.defData = defData;
	}
	

}
