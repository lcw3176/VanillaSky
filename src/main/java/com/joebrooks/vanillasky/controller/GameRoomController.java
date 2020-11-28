package com.joebrooks.vanillasky.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
@RequestMapping("/gameroom")
public class GameRoomController extends TextWebSocketHandler {

	@GetMapping
	public String setRoom(@RequestParam("roomName") String roomName, HttpSession session){
		session.setAttribute("roomName", roomName);
		return "gameRoom";

	}

}
