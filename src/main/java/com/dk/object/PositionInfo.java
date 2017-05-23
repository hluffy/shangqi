package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class PositionInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1013019550816899731L;
	private String equipmentNum;
	//alter table positioning alter column frame_num set null
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
	
	

}
