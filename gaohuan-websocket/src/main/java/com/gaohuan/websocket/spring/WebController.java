package com.gaohuan.websocket.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by acer on 2016/6/28.
 */
@Controller
public class WebController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/userChat")
    @SendTo("/topic/notice")
    public UserCommand userChat(UserCommand userCommand) {
        return userCommand;
    }
}

