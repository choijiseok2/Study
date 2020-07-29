package com.inittest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSoketConfig implements WebSocketMessageBrokerConfigurer {

	 	@Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/ws").withSockJS();
	    }
	
	 	@Override
	    public void configureMessageBroker(MessageBrokerRegistry registry) {
	        registry.setApplicationDestinationPrefixes("/app"); //- "/app" 시작되는 메시지가 message-handling methods으로 라우팅 되어야 한다는 것을 명시
	        registry.enableSimpleBroker("/topic"); // - "/topic" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
	    }
}
