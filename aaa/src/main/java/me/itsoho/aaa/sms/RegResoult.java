package me.itsoho.aaa.sms;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RegResoult {
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Msg")
	private String msg;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
