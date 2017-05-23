package com.dk.netty;

import java.sql.Timestamp;

public class GPS {
	private String isLocation;//是否定位
	private Timestamp date;//时间
	private Double longitude;//经度
	private Double latitude;//纬度
	private Integer altitude;//海拔
	private Integer direction;//方向
	private Integer speed;//速度
	
	public GPS(){
		
	}
	public GPS(String isLocation,Timestamp date,Double longitude,Double latitude,Integer altitude,Integer direction,Integer speed){
		this.isLocation = isLocation;
		this.date = date;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.direction = direction;
		this.speed = speed;
	}
	public String getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(String isLocation) {
		this.isLocation = isLocation;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
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
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	

}
