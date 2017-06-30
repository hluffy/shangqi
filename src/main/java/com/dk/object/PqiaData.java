package com.dk.object;

import java.sql.Timestamp;

import org.joda.time.DateTime;


/** 
* @ClassName: PqiaData 
* @Description: TODO(未合格的汽车，数据表。) 
* @author Roy 
* @date 2017年6月12日 下午4:01:38 
*  
*/ 
public class PqiaData {

	private String shift;//早班
	private String mode_name;
	private int singleframe;//状态码
	private String frameNum;//返回数据
	private String vehicledefects;//车辆缺陷
	private String newspaperdefect;//报缺陷人
	private String repairman;//返修人
	private String thistime;//类型的时间
	private String statittime;//开始时间
	private String endtime;//结束时间
	private String vinnumber;//vin号
	private String devicenumber;//设备号
	private String defectpeople;//报缺陷人条件选择
	private String repairpeople;//返修人条件选择
	private String defectcar;//缺陷信息条件选择
	
	public String getRepairpeople() {
		return repairpeople;
	}
	public void setRepairpeople(String repairpeople) {
		this.repairpeople = repairpeople;
	}
	public String getDefectpeople() {
		return defectpeople;
	}
	public void setDefectpeople(String defectpeople) {
		this.defectpeople = defectpeople;
	}
	public String getDefectcar() {
		return defectcar;
	}
	public void setDefectcar(String defectcar) {
		this.defectcar = defectcar;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getMode_name() {
		return mode_name;
	}
	public void setMode_name(String mode_name) {
		this.mode_name = mode_name;
	}
	public int getSingleframe() {
		return singleframe;
	}
	public void setSingleframe(int singleframe) {
		this.singleframe = singleframe;
	}
	public String getFrameNum() {
		return frameNum;
	}
	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}
	public String getVehicledefects() {
		return vehicledefects;
	}
	public void setVehicledefects(String vehicledefects) {
		this.vehicledefects = vehicledefects;
	}
	public String getNewspaperdefect() {
		return newspaperdefect;
	}
	public void setNewspaperdefect(String newspaperdefect) {
		this.newspaperdefect = newspaperdefect;
	}
	public String getRepairman() {
		return repairman;
	}
	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}
	public String getVinnumber() {
		return vinnumber;
	}
	public void setVinnumber(String vinnumber) {
		this.vinnumber = vinnumber;
	}
	public String getDevicenumber() {
		return devicenumber;
	}
	public void setDevicenumber(String devicenumber) {
		this.devicenumber = devicenumber;
	}
	public String getStatittime() {
		return statittime;
	}
	public void setStatittime(String statittime) {
		this.statittime = statittime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
