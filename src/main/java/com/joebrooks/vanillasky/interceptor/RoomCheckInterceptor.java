package com.joebrooks.vanillasky.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.joebrooks.vanillasky.dto.Room;
import com.joebrooks.vanillasky.service.room.RoomFindService;

public class RoomCheckInterceptor implements HandlerInterceptor{

	@Autowired
	RoomFindService roomFindService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String roomName = request.getParameter("roomName");
		Room room = roomFindService.GetRoomByName(roomName);
		
		if(room != null) {
			if(!room.isRunning() && room.getMaxNumber() >= room.getNowNumber()) {
				return true;
			}
			
		}
		
		response.sendRedirect(request.getContextPath() + "/roomlist");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
