package com.inittest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileWebController {
	@RequestMapping("/fileview")
	public String init() {
		return "fileTest";
	}
}
