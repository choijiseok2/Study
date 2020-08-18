package com.inittest.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping("/download")
	public ResponseEntity<Resource> download(@RequestParam("filename") String filename, HttpServletRequest request)
			throws IOException {
		Path path = Paths.get(tempPath + filename);

		String contentType = Files.probeContentType(path);

		FileVo filevo = fileService.getFileInfo(filename);

		HttpHeaders headers = new HttpHeaders();

		String browser = request.getHeader("User-Agent");

		boolean ie = (browser.indexOf("MSIE") > -1 || browser.indexOf("Trident") > -1);

		String orginFileName = filevo.getOriginFileName();

		if (ie) {
			orginFileName = URLEncoder.encode(orginFileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			orginFileName = new String(String.valueOf(orginFileName).getBytes("utf-8"), "iso-8859-1");
		}

		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + orginFileName + "\";");

		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		Resource resource = new InputStreamResource(Files.newInputStream(path));

		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
