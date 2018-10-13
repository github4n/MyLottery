package com.csy.domain.parse;

import java.util.List;

public class ParseResultJson {
	private Long rows;   
	private String code;   //彩种别名
	private String remain;   
	private List<ParseGdklsf> data;    //近5期的开奖数据
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	
	
	
	
	
	public List<ParseGdklsf> getData() {
		return data;
	}
	public void setData(List<ParseGdklsf> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ParseResultJson [rows=" + rows + ", code=" + code + ", remain=" + remain + ", data=" + data + "]";
	}
	
	
	
	
	
	
}
