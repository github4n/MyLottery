package com.csy.domain.parse;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParseGdklsf {
	private String expect;
	private String opencode;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date opentime;
	private Long opentimestamp;

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

}
