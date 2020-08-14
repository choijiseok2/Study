package com.inittest.config;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.inittest.enums.MessageType;
import com.inittest.service.ChatService;
import com.inittest.vo.ChatMessage;

@Component
public class WebSocketEventListener {
	@Autowired
	ChatService chatservice;
	
        private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(WebSocketEventListener.class);
           @Autowired
           private SimpMessageSendingOperations messagingTemplate;
           @EventListener
           public void handleWebSocketConnectListener(SessionConnectedEvent  event) {
               logger.info("Received a new web socket connection");
           }
           
           
           @EventListener
           public void handleWebSocketDisconnectListener(SessionDisconnectEvent  event) {
               StompHeaderAccessor headerAccessor =  StompHeaderAccessor.wrap(event.getMessage());
               String username = (String)  headerAccessor.getSessionAttributes().get("username");
               if(username != null) {
                   logger.info("User Disconnected : " + username);
                   ChatMessage chatMessage = new ChatMessage();
                   chatMessage.setType(MessageType.LEAVE);
                   chatMessage.setSender(username);
                   chatservice.insertChatHis(chatMessage);
                   messagingTemplate.convertAndSend("/topic/public",  chatMessage);
               }
           }
}