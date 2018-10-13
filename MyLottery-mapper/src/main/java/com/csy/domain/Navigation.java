package com.csy.domain;



public class Navigation extends BaseDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1781630468556151206L;
	private Long iGameId; //彩种对应id
	private String sUrl; //彩种对应图标
	private String sName; //彩种名称
	
	
	public Long getiGameId() {
		return iGameId;
	}
	public void setiGameId(Long iGameId) {
		this.iGameId = iGameId;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
