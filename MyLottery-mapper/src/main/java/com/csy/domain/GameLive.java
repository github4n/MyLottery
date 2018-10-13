package com.csy.domain;

public class GameLive extends BaseDomain{
    private Long iGameId;

    private String sStaticUrl;

    private String sDynamicUrl;

	public Long getiGameId() {
		return iGameId;
	}

	public void setiGameId(Long iGameId) {
		this.iGameId = iGameId;
	}

	public String getsStaticUrl() {
		return sStaticUrl;
	}

	public void setsStaticUrl(String sStaticUrl) {
		this.sStaticUrl = sStaticUrl;
	}

	public String getsDynamicUrl() {
		return sDynamicUrl;
	}

	public void setsDynamicUrl(String sDynamicUrl) {
		this.sDynamicUrl = sDynamicUrl;
	}

    
}