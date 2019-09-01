package com.jmh.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSAliInfo {
	private String telephone;
	private String templateParam;

	@Value("${signName}")
	private String signName;

	@Value("${templateCode}")
	private String templateCode;

	@Value("${accessKeyId}")
	private String accessKeyId;

	@Value("${accessKeySecret}")
	private String ali_accessKeySecret;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTemplateParam() {
		return templateParam;
	}

	public void setTemplateParam(String templateParam) {
		this.templateParam = "{code:" + templateParam + "}";
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAli_accessKeySecret() {
		return ali_accessKeySecret;
	}

	public void setAli_accessKeySecret(String ali_accessKeySecret) {
		this.ali_accessKeySecret = ali_accessKeySecret;
	}

}
