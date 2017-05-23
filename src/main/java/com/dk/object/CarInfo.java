package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7546052585516453886L;
	private String frameNum;
	private String engineNum;
	private Timestamp productionTime;
	//alter table carinfo add equipment_num varchar(50)
	//alter table carinfo add constratiint fk foreign key (equipment_num) references positioning(equipment_num)
	private String equipmentNum;
	
	private Integer page;
	
	private String productionTimeStr;
	public String getFrameNum() {
		return frameNum;
	}
	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}
	public String getEngineNum() {
		return engineNum;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public Timestamp getProductionTime() {
		return productionTime;
	}
	public void setProductionTime(Timestamp productionTime) {
		this.productionTime = productionTime;
	}
	public String getProductionTimeStr() {
		return productionTimeStr;
	}
	public void setProductionTimeStr(String productionTimeStr) {
		this.productionTimeStr = productionTimeStr;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	

}
