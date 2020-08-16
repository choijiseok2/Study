package com.inittest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileApiController {
	
	@Value("${temp.path}") private String tempPath;
	
	
	@RequestMapping("/upload")
	public int upload(@RequestPart List<MultipartFile> files) throws IOException {
		log.info("Upload start =>{} " , tempPath);
		for (MultipartFile file : files) {
			log.info("Upload file =>{} " , file);
			File tmp = new File(tempPath + UUID.randomUUID().toString());
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), tmp);
			} catch (IOException e) {
				log.error("Error while copying.", e);
				throw e;
			}
		}
	
	return 1;
	}
}
