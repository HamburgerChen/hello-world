package com.jmh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jmh.model.JobProvidedInfo;
import com.jmh.model.JobWantedInfo;

public interface WeChatDataService {

	List<JobProvidedInfo> getAllJobProvidedInfo(@Param("startPage")int startPage, @Param("pageSize")int pageSize);

	int getJobProvidedCount();

	int newJobProvidedInfo(JobProvidedInfo jobInfo);

	String newJobProvidedId();

	int updateJobProvidedInfo(JobProvidedInfo jobInfo);

	int deleteJobProvidedInfo(String jobId);

	String getPhone(String openId);

	JobProvidedInfo getJobProvidedInfo(String jobId);

	List<JobProvidedInfo> getAllJobPvdInfoByCompId(
			@Param("companyId")String companyId, @Param("startPage")int startPage, @Param("pageSize")int pageSize);

	int getJobProvidedCompCount(String companyId);

	List<JobWantedInfo> getAllJobWantedInfo(@Param("startPage")int startPage, @Param("pageSize")int pageSize);

	int getAllJobWantedCount();

	JobWantedInfo getJobWantedInfo(String wantedId);

	String newJobWantedId();

	int newJobWantedInfo(JobWantedInfo wantedInfo);

}
