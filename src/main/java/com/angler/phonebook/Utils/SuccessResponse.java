package com.angler.phonebook.Utils;

import java.util.Date;

public class SuccessResponse<T> extends PojoUtils {

	long timestamp = new Date().getTime();

	boolean status;
	String message;
	String description;
	private String logId = "";
	
	
	public String getLogId() {
		return checkResult(logId);
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	T data;

	public T getData() {
		return data;
	}

	public void setData(T users) {
		this.data = users;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return checkResult(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
