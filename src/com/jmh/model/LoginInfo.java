/**
 * 
 */
package com.jmh.model;

/**
 * @author chen, weiewi
 * @date 08/07/2019
 *
 */
public class LoginInfo {
	private String loginName;
	private String loginPwd;

	public LoginInfo() {

	}

	public LoginInfo(String loginName, String loginPwd) {
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getString() {
		return "loginName = " + this.loginName + ", loginPwd = " + this.loginPwd;
	}

}
