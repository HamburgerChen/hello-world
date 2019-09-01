package com.jmh.model;

import java.sql.Timestamp;

public class User {
	private String id;
	private String userId;
	private String userName;
	private String phone;
	private String socialId;
	private String officerNumber;
	private String officerImage;
	private String resident;
	private Timestamp joinTime;
	private String openId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getOfficerNumber() {
		return officerNumber;
	}

	public void setOfficerNumber(String officerNumber) {
		this.officerNumber = officerNumber;
	}

	public String getOfficerImage() {
		return officerImage;
	}

	public void setOfficerImage(String officerImage) {
		this.officerImage = officerImage;
	}

	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public Timestamp getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
