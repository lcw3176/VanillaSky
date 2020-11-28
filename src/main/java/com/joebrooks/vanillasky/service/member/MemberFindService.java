package com.joebrooks.vanillasky.service.member;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.MemberDao;
import com.joebrooks.vanillasky.dto.MemberInfo;


public class MemberFindService {

	@Autowired
	private MemberDao memberDao;

	public MemberInfo GetMemberById(String id) {
		return memberDao.SelectById(id);
	}
}
