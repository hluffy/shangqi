package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class PositionInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1013019550816899731L;
	private String equipmentNum;
	// alter table positioning alter column frame_num set null
	private Integer elec;
	private Double log;
	private Double lat;
	private String positionMode;
	private Timestamp positionTime;
	private String area;
	private Integer id;
	private String positionTimeStr;
	private int page;
	private int total;  
	private String frameNum;
    private String Chinesedescription;//返回前段中文描述
    private String Personliable;//返回前段报缺陷人人名
    private String Repairman;//返回前段返修人的人名
    private String frameunmber;
	private String startTime;
	private String endTime;
	private String vin;
	private PqiaData pqiaData;
	
    
    public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public PqiaData getPqiaData() {
		return pqiaData;
	}

	public void setPqiaData(PqiaData pqiaData) {
		this.pqiaData = pqiaData;
	}

	public String getChinesedescription() {
		return Chinesedescription;
	}

	public void setChinesedescription(String chinesedescription) {
		Chinesedescription = chinesedescription;
	}

	public String getPersonliable() {
		return Personliable;
	}

	public void setPersonliable(String personliable) {
		Personliable = personliable;
	}

	public String getRepairman() {
		return Repairman;
	}

	public void setRepairman(String repairman) {
		Repairman = repairman;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public String getFrameunmber() {
		return frameunmber;
	}

	public void setFrameunmber(String frameunmber) {
		this.frameunmber = frameunmber;
	}

	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public Integer getElec() {
		return elec;
	}

	public void setElec(Integer elec) {
		this.elec = elec;
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

	public String getPositionMode() {
		return positionMode;
	}

	public void setPositionMode(String positionMode) {
		this.positionMode = positionMode;
	}

	public Timestamp getPositionTime() {
		return positionTime;
	}

	public void setPositionTime(Timestamp positionTime) {
		this.positionTime = positionTime;
	}

	public String getPositionTimeStr() {
		return positionTimeStr;
	}

	public void setPositionTimeStr(String positionTimeStr) {
		this.positionTimeStr = positionTimeStr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
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
	
	


}
