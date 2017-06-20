package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class EmployInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5218108833920611325L;
	private String employeeId;
	private String employeeName;
	private String employeeShiftGroup;
	private String employeeSite;
	private String employeeShift;
	private Timestamp lastUseTime;
	
	private String lastUseTimeStr;
	private String file;
	
	public EmployInfo(){
		
	}
	
	public EmployInfo(String employeeId,String employeeName,String employeeShiftGroup,String employeeSite,String employeeShift,Timestamp lastUseTime){
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeShiftGroup = employeeShiftGroup;
		this.employeeSite = employeeSite;
		this.employeeShift = employeeShift;
		this.lastUseTime = lastUseTime;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeShiftGroup() {
		return employeeShiftGroup;
	}
	public void setEmployeeShiftGroup(String employeeShiftGroup) {
		this.employeeShiftGroup = employeeShiftGroup;
	}
	public String getEmployeeSite() {
		return employeeSite;
	}
	public void setEmployeeSite(String employeeSite) {
		this.employeeSite = employeeSite;
	}
	public String getEmployeeShift() {
		return employeeShift;
	}
	public void setEmployeeShift(String employeeShift) {
		this.employeeShift = employeeShift;
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	

}
