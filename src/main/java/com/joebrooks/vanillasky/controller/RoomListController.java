package com.joebrooks.vanillasky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joebrooks.vanillasky.dto.Room;
import com.joebrooks.vanillasky.service.room.RoomFindService;

@Controller
@RequestMapping("/roomlist")
public class RoomListController {

	@Autowired
	private RoomFindService roomFindService;
	
	@GetMapping
	public String RoomList(Model model) {	
		List<Room> room = roomFindService.SelectAll();
		model.addAttribute("roomList", room);

		return "roomList";
	}
}
