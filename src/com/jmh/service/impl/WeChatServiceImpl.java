/**
 * 
 */
package com.jmh.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.jmh.dao.WeChatDataService;
import com.jmh.model.CheckCodeResult;
import com.jmh.model.JobProvidedInfo;
import com.jmh.model.JobWantedInfo;
import com.jmh.model.SMSAliInfo;
import com.jmh.service.WeChatService;
import com.jmh.utils.Constants;
import com.jmh.utils.SMSAliUtils;

/**
 * @author chen, weiwei
 * @date 08/13/2019
 *
 */
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

	private static Log log = LogFactory.getLog(WeChatServiceImpl.class);

	private SMSAliInfo smsAliInfo;

	@Autowired
	public void setInfo(SMSAliInfo smsAliInfo) {
		this.smsAliInfo = smsAliInfo;
	}

	private WeChatDataService weChatDataService;

	@Autowired
	public void setWeChatDataService(WeChatDataService weChatDataService) {
		this.weChatDataService = weChatDataService;
	}

	@Override
	public CheckCodeResult sendSMS(String telephone, String checkCode) throws ServerException, ClientException {
		CheckCodeResult result = new CheckCodeResult();
		smsAliInfo.setTelephone(telephone);
		smsAliInfo.setTemplateParam(checkCode);
		SendSmsResponse smsAck = SMSAliUtils.sendSMS(smsAliInfo);
		result.setTelephone(telephone);
		if (smsAck == null) {
			log.error("smsAck error, no sms come back.");
			result.setStatusCode(Constants.FAILUE);
			return result;
		}

		if (smsAck.getCode().equals(Constants.SENT_SUCCEED) || smsAck.getCode().isEmpty()) {
			result.setCheckCode(checkCode);
			result.setStatusCode(Constants.SUCCESS);
		} else {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(smsAck.getMessage());
		}
		return result;
	}

	@Override
	public List<JobProvidedInfo> getAllJobProvidedInfo(
			@Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobProvidedInfo> jobProvidedInfos = weChatDataService.getAllJobProvidedInfo(startPage, pageSize);
		return jobProvidedInfos;
	}

	@Override
	public int getAllJobProvidedCount() {
		int result = weChatDataService.getJobProvidedCount();
		return result;
	}

	@Override
	public String newJobProvidedInfo(JobProvidedInfo jobInfo) {
		String jobId = weChatDataService.newJobProvidedId();
		jobInfo.setJobId(jobId);
		if(weChatDataService.newJobProvidedInfo(jobInfo) == 1){
			return jobId;
		}
		return null;
	}

	@Override
	public int updateJobProvidedInfo(JobProvidedInfo jobInfo) {
		int updateCode = weChatDataService.updateJobProvidedInfo(jobInfo);
		return updateCode;
	}

	@Override
	public int deleteJobProvidedInfo(String jobId) {
		int deleteCode = weChatDataService.deleteJobProvidedInfo(jobId);
		return deleteCode;
	}

	@Override
	public String getPhone(String openId) {
		String phone = weChatDataService.getPhone(openId);
		return phone;
	}

	@Override
	public JobProvidedInfo getJobProvidedInfo(String jobId) {
		JobProvidedInfo result = weChatDataService.getJobProvidedInfo(jobId);
		return result;
	}

	@Override
	public List<JobProvidedInfo> getAllJobPvdInfoByCompId(
			@Param("companyId")String companyId, @Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobProvidedInfo> jobProvidedInfos = weChatDataService.getAllJobPvdInfoByCompId(companyId, startPage, pageSize);
		return jobProvidedInfos;
	}

	@Override
	public int getJobProvidedCompCount(String companyId) {
		int result = weChatDataService.getJobProvidedCompCount(companyId);
		return result;
	}

	@Override
	public List<JobWantedInfo> getAllJobWantedInfo(@Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobWantedInfo> jobWantedInfos = weChatDataService.getAllJobWantedInfo(startPage, pageSize);
		return jobWantedInfos;
	}

	@Override
	public int getAllJobWantedCount() {
		int result = weChatDataService.getAllJobWantedCount();
		return result;
	}

	@Override
	public JobWantedInfo getJobWantedInfo(String wantedId) {
		JobWantedInfo result = weChatDataService.getJobWantedInfo(wantedId);
		return result;
	}

	@Override
	public String newJobWantedInfo(JobWantedInfo wantedInfo) {
		String wantedId = weChatDataService.newJobWantedId();
		wantedInfo.setWantedId(wantedId);
		if(weChatDataService.newJobWantedInfo(wantedInfo) == 1){
			return wantedId;
		}
		return null;
	}
}
