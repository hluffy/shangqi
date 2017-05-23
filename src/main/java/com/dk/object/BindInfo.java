package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class BindInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4838383591100334378L;
	
	private Integer id;
	private String frameNum;
	private String EquipmentNum;
	private Timestamp bindTime;
	private Timestamp unbindTime;
	private String bindType;
	
	private String bindTimeStr;
	private String unbindTimeStr;
	
	private Integer page;
	
	private String bindStartTime;
	private String bindEndTime;
	private String unbindStartTime;
	private String unbindEndTime;
	
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
	public String getEquipmentNum() {
		return EquipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		EquipmentNum = equipmentNum;
	}
	public Timestamp getBindTime() {
		return bindTime;
	}
	public void setBindTime(Timestamp bindTime) {
		this.bindTime = bindTime;
	}
	public Timestamp getUnbindTime() {
		return unbindTime;
	}
	public void setUnbindTime(Timestamp unbindTime) {
		this.unbindTime = unbindTime;
	}
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public String getBindTimeStr() {
		return bindTimeStr;
	}
	public void setBindTimeStr(String bindTimeStr) {
		this.bindTimeStr = bindTimeStr;
	}
	public String getUnbindTimeStr() {
		return unbindTimeStr;
	}
	public void setUnbindTimeStr(String unbindTimeStr) {
		this.unbindTimeStr = unbindTimeStr;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getBindStartTime() {
		return bindStartTime;
	}
	public void setBindStartTime(String bindStartTime) {
		this.bindStartTime = bindStartTime;
	}
	public String getBindEndTime() {
		return bindEndTime;
	}
	public void setBindEndTime(String bindEndTime) {
		this.bindEndTime = bindEndTime;
	}
	public String getUnbindStartTime() {
		return unbindStartTime;
	}
	public void setUnbindStartTime(String unbindStartTime) {
		this.unbindStartTime = unbindStartTime;
	}
	public String getUnbindEndTime() {
		return unbindEndTime;
	}
	public void setUnbindEndTime(String unbindEndTime) {
		this.unbindEndTime = unbindEndTime;
	}
	

}
