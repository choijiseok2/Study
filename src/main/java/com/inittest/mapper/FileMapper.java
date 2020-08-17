package com.inittest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.inittest.vo.FileVo;

@Mapper
public interface FileMapper {

	void insertFileHis(FileVo filevo);
	
}
