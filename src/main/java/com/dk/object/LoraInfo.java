package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoraInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1253459725971406726L;
	private String number;//编号
	private String ip;//ip地址
	private String registAddr;//注册地址
	private String registPort;//注册端口
	private String loginAddr;//登录地址
	private String terNum;//终端号
	private Timestamp time;//时间
	
	private String timeStr;
	private Integer page;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegistAddr() {
		return registAddr;
	}
	public void setRegistAddr(String registAddr) {
		this.registAddr = registAddr;
	}
	public String getRegistPort() {
		return registPort;
	}
	public void setRegistPort(String registPort) {
		this.registPort = registPort;
	}
	public String getLoginAddr() {
		return loginAddr;
	}
	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}
	public String getTerNum() {
		return terNum;
	}
	public void setTerNum(String terNum) {
		this.terNum = terNum;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	

}
