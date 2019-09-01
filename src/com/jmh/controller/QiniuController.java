package com.jmh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jmh.model.BaseResult;
import com.jmh.service.QiniuService;
import com.jmh.utils.Constants;
import com.jmh.utils.WebUtils;

@RestController()
@RequestMapping("/qiniu")
public class QiniuController {
	private QiniuService qiniuService;

	@Autowired
	public void setQiniuService(QiniuService qiniuService) {
		this.qiniuService = qiniuService;
	}

	/**
	 * @api {post} jmh/qiniu/uploadImg 上传图片
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup qiniu
	 * @apiName uploadImg
	 * 
	 * @apiParam {MultipartFile} file 上传文件（文件类型）
	 * 
	 * @apiSuccess {String} message 返回存储url/错误信息
	 * @apiSuccess {int} statusCode 0 代表成功 /1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		BaseResult result = new BaseResult();

		if (file.isEmpty()) {
			result.setMessage("no file upload.");
			result.setStatusCode(Constants.FAILUE);
		}
		try {
			String fileUrl = qiniuService.saveImage(file);
			result.setMessage(fileUrl);
			result.setStatusCode(Constants.SUCCESS);
		} catch (IOException e) {
			result.setMessage(e.getMessage());
			result.setStatusCode(Constants.FAILUE);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {post} jmh/qiniu/upload 上传图片
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup qiniu
	 * @apiName upload
	 * 
	 * @apiParam {String} path 上传文件（路径）
	 * 
	 * @apiSuccess {String} message 返回存储url/错误信息
	 * @apiSuccess {int} statusCode 0 代表成功 /1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadImage(String path, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		String fileUrl = qiniuService.upload(path, true);
		result.setMessage(fileUrl);
		result.setStatusCode(Constants.SUCCESS);
		WebUtils.responseJson(result, response);
		return null;
	}
}
