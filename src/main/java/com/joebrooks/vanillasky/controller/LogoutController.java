package com.joebrooks.vanillasky.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joebrooks.vanillasky.service.member.MemberRemoveService;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	MemberRemoveService service;
	
	@GetMapping
	public String Logout(HttpSession session) {
		service.Remove((String)session.getAttribute("userId"));
		session.invalidate();
		
		return "redirect:/login";
	}
}
