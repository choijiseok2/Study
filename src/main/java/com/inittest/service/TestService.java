package com.inittest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inittest.mapper.TestMapper;

@Service
public class TestService {

	@Autowired TestMapper testMapper;
	
	public void getInit() {
		// TODO Auto-generated method stub
		System.out.println(testMapper.getInitTest());
	}
}
