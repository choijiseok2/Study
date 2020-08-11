package com.inittest.vo;

import com.inittest.enums.MessageType;

import lombok.Data;

@Data	
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
}