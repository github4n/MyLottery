package com.csy.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataObject {
	private String expect;
	private String opencode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date opentime;
	private Long opentimestamp;
	//彩种属性对象
	private Object property;
	
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getOpencode() {
		return opencode;
	}
	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	public Long getOpentimestamp() {
		return opentimestamp;
	}
	public void setOpentimestamp(Long opentimestamp) {
		this.opentimestamp = opentimestamp;
	}
	public Object getProperty() {
		return property;
	}
	public void setProperty(Object property) {
		this.property = property;
	}
	
	
}
