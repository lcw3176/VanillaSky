package com.joebrooks.vanillasky.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.joebrooks.vanillasky.handler.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{


	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/gameroom").withSockJS().setInterceptors(new HttpSessionHandshakeInterceptor());
		
	}
	
	@Bean
	public WebSocketHandler webSocketHandler() {
		return new WebSocketHandler();
	}
	
	@Bean
	public JSONParser jsonParser() {
		return new JSONParser();
	}
	
	@Bean
	public JSONObject jsonObejct() {
		return new JSONObject();
	}

}
