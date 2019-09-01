/**
 * 
 */
package com.jmh.model;

import java.sql.Timestamp;

/**
 * @author chen weiwei
 * @date 08/16/2019
 *
 */
public class CompanyRole {
	private String companyRoleId;
	//from jmh_company
	private String companyName;
	private String officialNumber;
	private String officialImage;
	private String phone;
	private String companyContacts;
	private Timestamp joinTime;
	//from jmh_role
	private String account;
	private String password;
	private String access_level;

	public String getCompanyRoleId() {
		return companyRoleId;
	}

	public void setCompanyRoleId(String companyRoleId) {
		this.companyRoleId = companyRoleId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficialNumber() {
		return officialNumber;
	}

	public void setOfficialNumber(String officialNumber) {
		this.officialNumber = officialNumber;
	}

	public String getOfficialImage() {
		return officialImage;
	}

	public void setOfficialImage(String officialImage) {
		this.officialImage = officialImage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyContacts() {
		return companyContacts;
	}

	public void setCompanyContacts(String companyContacts) {
		this.companyContacts = companyContacts;
	}

	public Timestamp getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccess_level() {
		return access_level;
	}

	public void setAccess_level(String access_level) {
		this.access_level = access_level;
	}
}
