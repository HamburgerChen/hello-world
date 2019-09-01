/**
 * 
 */
package com.jmh.model;

import java.sql.Timestamp;

/**
 * @author chen, weiwei
 * @date 08/30/2019
 *
 */
public class JobWantedInfo {
	private String wantedId;
	private String wantedName;
	private char male;
	private int age;
	private char eduKey;
	private String eduValue;
	private String serveExp;
	private String wantedJob1;
	private String wantedJob2;
	private String wantedPlace;
	private String advantage1;
	private String advantage2;
	private Timestamp updateTime;
	private String updateBy;

	public String getWantedId() {
		return wantedId;
	}

	public void setWantedId(String wantedId) {
		this.wantedId = wantedId;
	}

	public String getWantedName() {
		return wantedName;
	}

	public void setWantedName(String wantedName) {
		this.wantedName = wantedName;
	}

	public char getMale() {
		return male;
	}

	public void setMale(char male) {
		this.male = male;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getServeExp() {
		return serveExp;
	}

	public void setServeExp(String serveExp) {
		this.serveExp = serveExp;
	}

	public String getWantedJob1() {
		return wantedJob1;
	}

	public void setWantedJob1(String wantedJob1) {
		this.wantedJob1 = wantedJob1;
	}

	public String getWantedJob2() {
		return wantedJob2;
	}

	public void setWantedJob2(String wantedJob2) {
		this.wantedJob2 = wantedJob2;
	}

	public String getWantedPlace() {
		return wantedPlace;
	}

	public void setWantedPlace(String wantedPlace) {
		this.wantedPlace = wantedPlace;
	}

	public String getAdvantage1() {
		return advantage1;
	}

	public void setAdvantage1(String advantage1) {
		this.advantage1 = advantage1;
	}

	public String getAdvantage2() {
		return advantage2;
	}

	public void setAdvantage2(String advantage2) {
		this.advantage2 = advantage2;
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
