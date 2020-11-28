package com.joebrooks.vanillasky.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joebrooks.vanillasky.dto.Room;
import com.joebrooks.vanillasky.service.member.MemberFindService;
import com.joebrooks.vanillasky.service.room.RoomRegistService;

@Controller
@RequestMapping("/makeroom")
public class MakeRoomController  {

	@Autowired
	private RoomRegistService roomService;
	@Autowired
	private MemberFindService memService;
	
	
	@GetMapping
	public String showRoomForm(Room room) {
		return "makeRoom";
	}
	
	@PostMapping
	public String setRoomForGame(Room room, HttpSession session) throws UnsupportedEncodingException {
		String userId = (String) session.getAttribute("userId");
		
		if (userId != null) {
			if (roomService.RegistRoom(room.getRoomName(), room.getMaxNumber(), memService.GetMemberById(userId))) {
				
				session.setAttribute("roomName", room.getRoomName());
				
				String roomName = URLEncoder.encode(room.getRoomName(), "utf-8");
				String id =URLEncoder.encode(userId, "utf-8");
		
				return "redirect:/gameroom?roomName=" + roomName + "&id=" + id;
			}
		}
		
		return "makeRoom";
	}
}
