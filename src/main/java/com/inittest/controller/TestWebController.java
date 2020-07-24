package com.inittest.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inittest.service.TestService;

@Controller
public class TestWebController {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	TestService testService;

	@Value("${DB_port}")
	private String key;

	@RequestMapping("/")
	public String init() {
		testService.getInit();
		return "index";
	}

	@RequestMapping("redisTest/{value}/{key}")
	public String redisTest(@PathVariable("value") int value, @PathVariable("key") int key) {
		valueOperations.set(String.valueOf(key), String.valueOf(value));
		return "index";
	}

}
