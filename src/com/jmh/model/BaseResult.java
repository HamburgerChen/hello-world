/**
 * 
 */
package com.jmh.model;

/**
 * @author chen, weiwei
 * @date 08/05/2019
 *
 */
public class BaseResult {
	public BaseResult() {

	}

	public BaseResult(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	protected int statusCode;
	protected String message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
