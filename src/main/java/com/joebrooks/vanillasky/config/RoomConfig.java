package com.joebrooks.vanillasky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.service.room.RoomAnswerService;
import com.joebrooks.vanillasky.service.room.RoomChangePresenterService;
import com.joebrooks.vanillasky.service.room.RoomFindService;
import com.joebrooks.vanillasky.service.room.RoomRegistService;
import com.joebrooks.vanillasky.service.room.RoomRemoveMemberService;
import com.joebrooks.vanillasky.service.room.RoomStartGameService;

@Configuration
public class RoomConfig {
	@Bean
	public RoomFindService roomFindService() {
		return new RoomFindService();
	}
	
	@Bean
	public RoomRegistService roomRegistService() {
		return new RoomRegistService();
	}
	
	@Bean
	public RoomRemoveMemberService roomRemoveMemberService() {
		return new RoomRemoveMemberService();
	}
	
	@Bean
	public RoomAnswerService roomAnswerService() {
		return new RoomAnswerService();
	}
	
	@Bean
	public RoomChangePresenterService roomChangePresenterService() {
		return new RoomChangePresenterService();
	}
	
	@Bean
	public RoomStartGameService roomStartGameService() {
		return new RoomStartGameService();
	}
	
	@Bean
	public RoomDao roomDao() {
		return new RoomDao();
	}
	

}
