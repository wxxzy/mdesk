package com.example.adminserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

//@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate template;
}
