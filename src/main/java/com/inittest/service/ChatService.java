package com.inittest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inittest.mapper.ChatMapper;
import com.inittest.vo.ChatMessage;

@Service
public class ChatService {

	@Autowired
	ChatMapper chatMapper;
	
	public void insertChatHis(ChatMessage chatMessage) {
		chatMapper.insertChatHis(chatMessage);
	}
}

