package com.csy.dto;

import java.util.Date;

public class GamePeriodDTO{
	private Long iGameId;
	private int count;
	private Integer iOpen;
	private Date dOpenTimeFrom;
	private Date dOpenTimeTo;
	private String desc;
	
	public Long getiGameId() {
		return iGameId;
	}
	public void setiGameId(Long iGameId) {
		this.iGameId = iGameId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getdOpenTimeFrom() {
		return dOpenTimeFrom;
	}
	public void setdOpenTimeFrom(Date dOpenTimeFrom) {
		this.dOpenTimeFrom = dOpenTimeFrom;
	}
	public Date getdOpenTimeTo() {
		return dOpenTimeTo;
	}
	public void setdOpenTimeTo(Date dOpenTimeTo) {
		this.dOpenTimeTo = dOpenTimeTo;
	}
	public Integer getiOpen() {
		return iOpen;
	}
	public void setiOpen(Integer iOpen) {
		this.iOpen = iOpen;
	}
	
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
