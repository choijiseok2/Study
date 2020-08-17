package com.inittest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inittest.service.FileService;
import com.inittest.vo.FileVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileApiController {

	// 파일을 저장할 경로
	@Value("${temp.path}")
	private String tempPath;

	@Autowired
	private FileService fileService;

	@RequestMapping("/upload")
	public int upload(@RequestPart List<MultipartFile> files) throws IOException {
		FileVo filevo = new FileVo();
		log.info("Upload start =>{} ", tempPath);
		for (MultipartFile file : files) {
			Long temptime = System.currentTimeMillis();
			log.info("Upload file =>{} ", file.getOriginalFilename());
			File tmp = new File(tempPath + temptime);
			filevo.setOriginFileName(file.getOriginalFilename());
			filevo.setChangeFileName(temptime.toString());
			fileService.insertFileHis(filevo);
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
