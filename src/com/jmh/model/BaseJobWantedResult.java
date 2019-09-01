/**
 * 
 */
package com.jmh.model;

/**
 * @author chen, weiwei
 * @date 08/30/2019
 *
 */
public class BaseJobWantedResult extends BaseResult {
	private JobWantedInfo jobWantedInfo;

	public JobWantedInfo getJobWantedInfo() {
		return jobWantedInfo;
	}

	public void setJobWantedInfo(JobWantedInfo jobWantedInfo) {
		this.jobWantedInfo = jobWantedInfo;
	}
}
