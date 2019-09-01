package com.jmh.model;

import java.sql.Timestamp;

public class Visitor {
	private String id;
	private String visitorName;
	private String phone;
	private String visitorType;
	private String visitorTypeName;
	private String visitorStatus;
	private Timestamp sumbitTime; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getVisitorType() {
		return visitorType;
	}

	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	public String getVisitorTypeName() {
		return visitorTypeName;
	}

	public void setVisitorTypeName(String visitorTypeName) {
		this.visitorTypeName = visitorTypeName;
	}

	public String getVisitorStatus() {
		return visitorStatus;
	}

	public void setVisitorStatus(String visitorStatus) {
		this.visitorStatus = visitorStatus;
	}
	
	public Timestamp getSumbitTime() {
		return sumbitTime;
	}

	public void setSumbitTime(Timestamp sumbitTime) {
		this.sumbitTime = sumbitTime;
	}

}