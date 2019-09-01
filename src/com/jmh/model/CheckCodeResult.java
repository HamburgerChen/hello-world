/**
 * 
 */
package com.jmh.model;

/**
 * @author chen, weiwei
 * @date 08/13/2019
 *
 */
public class CheckCodeResult extends BaseResult {
	private String telephone;
	private String checkCode;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
