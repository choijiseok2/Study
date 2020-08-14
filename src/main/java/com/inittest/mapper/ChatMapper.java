package com.inittest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.inittest.vo.ChatMessage;

@Mapper
public interface ChatMapper {

	void insertChatHis(ChatMessage chatMessage);
	
}
