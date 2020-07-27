package com.inittest.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {


	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;
	
	@RequestMapping("redisTest/{value}/{key}")
	public String redisTest(@PathVariable("value") int value, @PathVariable("key") int key) {
		valueOperations.set(String.valueOf(key), String.valueOf(value));
		return "index";
	}
	
}