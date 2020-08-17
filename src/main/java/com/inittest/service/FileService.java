package com.inittest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inittest.mapper.FileMapper;
import com.inittest.vo.FileVo;

@Service
public class FileService {

	@Autowired FileMapper filemapper;
	
	public void insertFileHis(FileVo filevo) {
		// TODO Auto-generated method stub
		filemapper.insertFileHis(filevo);
	}

}
