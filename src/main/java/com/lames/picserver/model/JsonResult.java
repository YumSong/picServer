package com.lames.picserver.model;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {

	private String status;
	private String message;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(String key, Object value) {
		data.put(key, value);
	}
	public Object getData(String key) {
		return data.get(key);
	}
}
