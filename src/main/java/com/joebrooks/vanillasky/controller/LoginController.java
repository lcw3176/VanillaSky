package com.joebrooks.vanillasky.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.service.member.MemberRegistServcie;

@Controller
@RequestMapping(value = { "/login", "/" })
public class LoginController {
	
	@Autowired
	private MemberRegistServcie memberRegistService;

	@GetMapping
	public String SetLoginForm(
			MemberInfo memberInfo, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {

		if (session.getAttribute("userId") != null) {
			response.sendRedirect(request.getContextPath() + "/roomlist");
		}

		return "login";

	}

	@PostMapping
	public String Login(
			MemberInfo memberInfo, 
			HttpServletRequest request,
			HttpSession session, 
			Errors errors) throws Exception {

		if (memberInfo.getNickName() == null || memberInfo.getNickName().trim() == "") {
			errors.rejectValue("nickName", "WhiteSpaceError");

			return "login";
		}

		String ipAddress = request.getRemoteAddr();

		if (!memberRegistService.Regist(memberInfo.getNickName(), ipAddress)) {
			errors.rejectValue("nickName", "AlreadyError");
			
			return "login";
		}
		
		session.setAttribute("userId", memberInfo.getNickName());
		session.setMaxInactiveInterval(60 * 30);
		
		return "redirect:/roomlist";
	}
}
