package com.logWatch.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.logWatch.demo.model.logWatchModel;

@Controller
public class logwatchController {
	@MessageMapping("/ws")
	@GetMapping("/log")
	public logWatchModel getFileUpdates(logWatchModel message)
	{
		return message;
	}
	
}
