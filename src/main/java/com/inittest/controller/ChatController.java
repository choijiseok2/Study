package com.inittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inittest.service.ChatService;
import com.inittest.vo.ChatMessage;

@Controller
public class ChatController {
	    
	@Autowired
	ChatService chatservice;
	
		@MessageMapping("/chat.sendMessage")
	    @SendTo("/topic/public")
	    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
			
			//대화 내용 기록
			chatservice.insertChatHis(chatMessage);
			
	        return chatMessage;
	    }
	   
	    @MessageMapping("/chat.addUser")
	    @SendTo("/topic/public")
	    public ChatMessage addUser(@Payload ChatMessage chatMessage,  SimpMessageHeaderAccessor headerAccessor){
	        headerAccessor.getSessionAttributes().put("username",  chatMessage.getSender());
	        
	        System.out.println(chatMessage.toString());
	        //대화 접속 기록
	        chatservice.insertChatHis(chatMessage);
	        return chatMessage;
	    }
	    
	    @RequestMapping("/chat")
	    public String goChat() {
	       return "chat";
	    }    
}