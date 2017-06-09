package com.dk.netty;

public class IBeaconSignal {
	private String uuid;
	private Integer power;
	private Double rssi;
	private Integer maior;
	private Integer minor;
	private Integer bat;
	
	public IBeaconSignal(){
		
	}
	public IBeaconSignal(String uuid,Integer power,Double rssi,Integer maior,Integer minor,Integer bat){
		this.uuid = uuid;
		this.power = power;
		this.rssi = rssi;
		this.maior = maior;
		this.minor = minor;
		this.bat = bat;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Double getRssi() {
		return rssi;
	}
	public void setRssi(Double rssi) {
		this.rssi = rssi;
	}
	public Integer getMaior() {
		return maior;
	}
	public void setMaior(Integer maior) {
		this.maior = maior;
	}
	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	public Integer getBat() {
		return bat;
	}
	public void setBat(Integer bat) {
		this.bat = bat;
	}
	
	@Override
	public String toString() {
		return "uuid:"+uuid+",power"+power+",rssi"+rssi+",bat"+bat;
	}
	

}
