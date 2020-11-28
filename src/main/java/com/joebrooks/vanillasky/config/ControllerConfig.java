package com.joebrooks.vanillasky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joebrooks.vanillasky.common.CommonExceptionHandler;
import com.joebrooks.vanillasky.controller.GameRoomController;
import com.joebrooks.vanillasky.controller.LoginController;
import com.joebrooks.vanillasky.controller.LogoutController;
import com.joebrooks.vanillasky.controller.MakeRoomController;
import com.joebrooks.vanillasky.controller.RoomListController;

@Configuration
public class ControllerConfig {
	

	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public RoomListController roomListController() {
		return new RoomListController();
	}
	
	@Bean
	public MakeRoomController setRoomController() {
		return new MakeRoomController();
	}
	
	@Bean
	public GameRoomController gameRoomController() {
		return new GameRoomController();
	}
	
	@Bean
	public CommonExceptionHandler commonExcecptionHandler() {
		return new CommonExceptionHandler();
	}
	
}
