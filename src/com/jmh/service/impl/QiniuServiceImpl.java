package com.jmh.service.impl;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jmh.model.QiniuAuth;
import com.jmh.service.QiniuService;
import com.jmh.utils.ImageQiniuUtils;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Service("qiniuService")
public class QiniuServiceImpl implements QiniuService {

	private static Log log = LogFactory.getLog(QiniuServiceImpl.class);

	private QiniuAuth qiniuAuth;

	@Autowired
	public void setQiniuAuth(QiniuAuth qiniuAuth) {
		this.qiniuAuth = qiniuAuth;
	}

	// 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
	Configuration cfg = new Configuration(Zone.zone0());
	// ...其他参数参考类注释
	UploadManager uploadManager = new UploadManager(cfg);

	public String saveImage(MultipartFile file) throws IOException {
		Auth auth = Auth.create(qiniuAuth.getQiniuAccessKeyId(), qiniuAuth.getQiniuAccessKeySecret());
		int dotPos = file.getOriginalFilename().lastIndexOf(".");
		if (dotPos < 0) {
			return null;
		}
		String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
		// 判断是否是合法的文件后缀
		if (!ImageQiniuUtils.isFileAllowed(fileExt)) {
			return null;
		}

		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
		String upToken = auth.uploadToken(qiniuAuth.getBucketName());
		// 调用put方法上传
		Response res = uploadManager.put(file.getBytes(), fileName, upToken);
		if (res.isOK() && res.isJson()) {
			// 返回这张存储照片的地址
			return qiniuAuth.getDomainBucket() + JSONObject.parseObject(res.bodyString()).get("key");
		} else {
			log.error("七牛异常:" + res.bodyString());
			return null;
		}
	}

	public String upload(String localFilePath, boolean override) {
		return ImageQiniuUtils.upload(qiniuAuth, localFilePath, override);
	}
}