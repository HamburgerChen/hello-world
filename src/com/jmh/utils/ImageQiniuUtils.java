package com.jmh.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.jmh.model.QiniuAuth;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class ImageQiniuUtils {

	private static Log log = LogFactory.getLog(ImageQiniuUtils.class);

	/**
	 * 上传本地文件
	 * 
	 * @param localFilePath
	 *            本地文件完整路径
	 * @param key
	 *            文件云端存储的名称
	 * @param override
	 *            是否覆盖同名同位置文件
	 * @return
	 */

	public static String upload(QiniuAuth qiniuAuth, String localFilePath, boolean override) {
		Configuration cfg = new Configuration(qiniuAuth.getZone0());
		UploadManager uploadManager = new UploadManager(cfg);
		Auth auth = Auth.create(qiniuAuth.getQiniuAccessKeyId(), qiniuAuth.getQiniuAccessKeySecret());
		String key = UUID.randomUUID().toString();
		String upToken;
		if (override) {
			upToken = auth.uploadToken(qiniuAuth.getBucketName(), key);// 覆盖上传凭证
		} else {
			upToken = auth.uploadToken(qiniuAuth.getBucketName());
		}
		try {
			Response response = uploadManager.put(localFilePath, key, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			if (response.isOK()) {
				return qiniuAuth.getDomainBucket() + putRet.key;
				// return DOMAIN + ret.key + "?" + style;
			}
			return null;
		} catch (QiniuException ex) {
			Response r = ex.response;
			log.error(r.toString());
			try {
				log.error(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
				return null;
			}
			return null;
		}
	}

	/**
	 * 获取文件访问地址
	 * 
	 * @param fileName
	 *            文件云端存储的名称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fileUrl(QiniuAuth qiniuAuth, String fileName) throws UnsupportedEncodingException {
		String encodedFileName = URLEncoder.encode(fileName, "utf-8");
		String publicUrl = String.format("%s/%s", qiniuAuth.getDomainBucket(), encodedFileName);
		Auth auth = Auth.create(qiniuAuth.getQiniuAccessKeyId(), qiniuAuth.getQiniuAccessKeySecret());
		long expireInSeconds = -1;
		if (-1 == expireInSeconds) {
			return auth.privateDownloadUrl(publicUrl);
		}
		return auth.privateDownloadUrl(publicUrl, expireInSeconds);
	}

	// 图片允许的后缀扩展名
	public static String[] IMAGE_FILE_EXTD = new String[] { "png", "bmp", "jpg", "jpeg", "pdf" };

	public static boolean isFileAllowed(String fileName) {
		for (String ext : IMAGE_FILE_EXTD) {
			if (ext.equals(fileName)) {
				return true;
			}
		}
		return false;
	}
}
