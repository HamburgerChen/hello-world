package com.jmh.model;

import java.sql.Timestamp;

public class JobProvidedInfo {
	private String jobId;
	private String jobName;
	private String salaryRange;
	private String location;
	private String companyId;
	private String companyName;
	private String jobDesc;
	private char eduKey;
	private String eduValue;
	private char industryKey;
	private String industryValue;
	private char compSizeKey;
	private String compSizeValue;
	private char jobExpKey;
	private String jobExpValue;
	private Timestamp updateTime;
	private String updateBy;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public char getEduKey() {
		return eduKey;
	}

	public void setEduKey(char eduKey) {
		this.eduKey = eduKey;
	}

	public String getEduValue() {
		return eduValue;
	}

	public void setEduValue(String eduValue) {
		this.eduValue = eduValue;
	}

	public char getIndustryKey() {
		return industryKey;
	}

	public void setIndustryKey(char industryKey) {
		this.industryKey = industryKey;
	}

	public String getIndustryValue() {
		return industryValue;
	}

	public void setIndustryValue(String industryValue) {
		this.industryValue = industryValue;
	}

	public char getCompSizeKey() {
		return compSizeKey;
	}

	public void setCompSizeKey(char compSizeKey) {
		this.compSizeKey = compSizeKey;
	}

	public String getCompSizeValue() {
		return compSizeValue;
	}

	public void setCompSizeValue(String compSizeValue) {
		this.compSizeValue = compSizeValue;
	}

	public char getJobExpKey() {
		return jobExpKey;
	}

	public void setJobExpKey(char jobExpKey) {
		this.jobExpKey = jobExpKey;
	}

	public String getJobExpValue() {
		return jobExpValue;
	}

	public void setJobExpValue(String jobExpValue) {
		this.jobExpValue = jobExpValue;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
