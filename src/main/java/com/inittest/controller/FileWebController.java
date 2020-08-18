package com.inittest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inittest.service.FileService;
import com.inittest.vo.FileVo;

@Controller
public class FileWebController {

	// 파일을 저장할 경로
	@Value("${temp.path}")
	private String tempPath;

	@Autowired
	private FileService fileService;

	@RequestMapping("/fileview")
	public String init() {
		return "fileTest";
	}

}
