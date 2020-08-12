package com.inittest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inittest.vo.ChatMessage;

@Controller
public class ChatController {
	    
		@MessageMapping("/chat.sendMessage")
	    @SendTo("/topic/public")
	    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
	        return chatMessage;
	    }
		
		@MessageMapping("/chat.addUser")
	    @SendTo("/topic/public")
	    public ChatMessage addUser(@Payload ChatMessage chatMessage,  SimpMessageHeaderAccessor headerAccessor){
	        headerAccessor.getSessionAttributes().put( "username" ,  chatMessage.getSender() );
	        return chatMessage;
	    }
	    
	    /*채팅 대기실로 이동하는 컨트롤러*/
	    @RequestMapping("/chat")
	    public String goChat() {
	       return "chat";
	    }
	    
}