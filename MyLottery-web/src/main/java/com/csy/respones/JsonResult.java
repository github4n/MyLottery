package com.csy.respones;

public class JsonResult {
	private Boolean success = true;
	private String errorMsg;
	private Object result;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
	}
}
