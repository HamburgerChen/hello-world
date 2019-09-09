package com.jmh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.jmh.model.CheckCodeResult;
import com.jmh.model.JobProvidedInfo;
import com.jmh.model.JobWantedInfo;

/**
 * @author chen, weiwei
 * @date 08/13/2019
 * 
 */

public interface WeChatService {

	CheckCodeResult sendSMS(String telephone, String checkCode) throws ServerException, ClientException;

	List<JobProvidedInfo> getAllJobProvidedInfo(int startPage, int pageSize);

	int getAllJobProvidedCount();

	String newJobProvidedInfo(JobProvidedInfo jobInfo);

	int updateJobProvidedInfo(JobProvidedInfo jobInfo);

	int deleteJobProvidedInfo(String jobId);

	String getPhone(String jmhsId);

	JobProvidedInfo getJobProvidedInfo(String jobId);

	List<JobProvidedInfo> getAllJobPvdInfoByCompId(String companyId, int startPage, int pageSize);

	int getJobProvidedCompCount(String companyId);

	List<JobWantedInfo> getAllJobWantedInfo(int startPage, int pageSize);

	int getAllJobWantedCount();

	JobWantedInfo getJobWantedInfo(String wantedId);

	String newJobWantedInfo(JobWantedInfo wantedInfo);

	String processRequest(HttpServletRequest request) throws Exception;
}
