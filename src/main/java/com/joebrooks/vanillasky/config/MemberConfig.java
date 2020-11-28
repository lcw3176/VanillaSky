package com.joebrooks.vanillasky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joebrooks.vanillasky.dao.MemberDao;
import com.joebrooks.vanillasky.service.member.MemberFindService;
import com.joebrooks.vanillasky.service.member.MemberRegistServcie;
import com.joebrooks.vanillasky.service.member.MemberRemoveService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberRegistServcie memberRegistService() {
		return new MemberRegistServcie();
	}

	@Bean
	public MemberRemoveService memberRemoveService() {
		return new MemberRemoveService();
	}
	
	@Bean
	public MemberFindService memberFindService() {
		return new MemberFindService();
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	

}
