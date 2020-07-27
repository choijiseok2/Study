package com.inittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inittest.service.TestService;

@Controller
public class TestWebController {

	@Autowired
	TestService testService;

	@Value("${DB_port}")
	private String key;

	@RequestMapping("/")
	public String init() {
		testService.getInit();
		return "index";
	}
}
