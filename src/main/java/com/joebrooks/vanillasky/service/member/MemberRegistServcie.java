package com.joebrooks.vanillasky.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joebrooks.vanillasky.dao.MemberDao;
import com.joebrooks.vanillasky.dto.MemberInfo;

@Service
public class MemberRegistServcie {

	@Autowired
	private MemberDao memberDao;
	

	public boolean Regist(String name, String ip) {

		MemberInfo member = new MemberInfo();
		member.setNickName(name);
		member.setIpAddress(ip);
		member.setPresenter(false);


		return memberDao.Insert(member);
	}
	
}
