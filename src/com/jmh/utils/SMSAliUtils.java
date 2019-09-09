/**
 * 
 */
package com.jmh.utils;

import java.util.Random;

/**
 * @author chen, weiwei
 * @date 08/10/2019
 *
 */
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jmh.model.SMSAliInfo;

public class SMSAliUtils {
	/**
	 * 阿里短信服务发送工具类
	 * 
	 * @param telephone
	 *            必填:待发送手机号
	 * @param templateParam
	 *            可选:模板中的变量替换JSON串 "{\"name\":\"Tom\", \"code\":\"123\"}"
	 * @param signName
	 *            必填:短信签名-可在短信控制台中找到
	 * @param templateCode
	 *            必填:短信模板-可在短信控制台中找到
	 * @param accessKeyId
	 *            账号AK
	 * @param ali_accessKeySecret
	 *            账号
	 * @return 返回阿里返回值
	 * @throws ServerException
	 * @throws ClientException
	 */
	public static SendSmsResponse sendSMS(SMSAliInfo smsAliInfo) throws ServerException, ClientException {

		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAliInfo.getAccessKeyId(),
				smsAliInfo.getAli_accessKeySecret());
		//DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");

		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest requestSms = new SendSmsRequest();
		// 必填:待发送手机号
		requestSms.setPhoneNumbers(smsAliInfo.getTelephone());
		// 必填:短信签名-可在短信控制台中找到
		requestSms.setSignName(smsAliInfo.getSignName());
		// 必填:短信模板-可在短信控制台中找到
		requestSms.setTemplateCode(smsAliInfo.getTemplateCode());
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\",
		// \"code\":\"123\"}"

		requestSms.setTemplateParam(smsAliInfo.getTemplateParam());
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(requestSms);
		return sendSmsResponse;
	}

	//产生随机6位验证码
	public static String createRandomNumber() {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		int first = random.nextInt(10);
		if(first == 0){
			first = 8;
		}
		str.append(first);
		for (int i = 0; i < 5; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}
}