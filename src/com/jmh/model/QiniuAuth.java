/**
 * 
 */
package com.jmh.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qiniu.common.Zone;

/**
 * @author chen, weiwei
 * @date 08/18/2019
 *
 */
@Component
public class QiniuAuth {
	@Value("${qiniu.accessKeyId}")
	private String qiniuAccessKeyId;

	@Value("${qiniu.accessKeySecret}")
	private String qiniuAccessKeySecret;

	@Value("${qiniu.bucketName}")
	private String bucketName;

	@Value("${qiniu.bucketHostName}")
	private String bucketHostName;

	@Value("${qiniu.domainBucket}")
	private String domainBucket;

	private Zone zone0 = Zone.zone0();

	public String getQiniuAccessKeyId() {
		return qiniuAccessKeyId;
	}

	public void setQiniuAccessKeyId(String qiniuAccessKeyId) {
		this.qiniuAccessKeyId = qiniuAccessKeyId;
	}

	public String getQiniuAccessKeySecret() {
		return qiniuAccessKeySecret;
	}

	public void setQiniuAccessKeySecret(String qiniuAccessKeySecret) {
		this.qiniuAccessKeySecret = qiniuAccessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketHostName() {
		return bucketHostName;
	}

	public void setBucketHostName(String bucketHostName) {
		this.bucketHostName = bucketHostName;
	}

	public String getDomainBucket() {
		return domainBucket;
	}

	public void setDomainBucket(String domainBucket) {
		this.domainBucket = domainBucket;
	}

	public Zone getZone0() {
		return zone0;
	}

	public void setZone0(Zone zone0) {
		this.zone0 = zone0;
	}

}
