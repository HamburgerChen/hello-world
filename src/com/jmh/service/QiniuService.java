package com.jmh.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface QiniuService {

	public String saveImage(MultipartFile file) throws IOException;

	public String upload(String localFilePath, boolean override);
}
