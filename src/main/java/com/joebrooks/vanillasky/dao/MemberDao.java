package com.joebrooks.vanillasky.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.joebrooks.vanillasky.dto.MemberInfo;

public class MemberDao {

	@Autowired
	private ValueOperations<String, Object> values;

	private static List<String> userList = new ArrayList<String>();
	
	public MemberInfo prefix(MemberInfo member) {
		if (member.getNickName().contains("+")) {
			member.setNickName(member.getNickName().replaceAll("\\+", " "));
		}

		return member;
	}

	public String prefix(String memberName) {
		if (memberName.contains("+")) {
			memberName.replaceAll("\\+", " ");
		}

		return memberName;
	}

	public boolean Insert(MemberInfo member) {
		member = prefix(member);
		
		Object mem = values.get(member.getIpAddress());

		if (mem != null) {
			return false;
		}
		
		List<Object> tempList = values.multiGet(userList);
		List<MemberInfo> memList = tempList.stream().map(s -> (MemberInfo) s).collect(Collectors.toList());
		
		for(MemberInfo i : memList) {
			if(i.getIpAddress().equals(member.getIpAddress())) {
				return false;
			}
		}

		values.set(member.getNickName(), member);
		userList.add(member.getNickName());
		
		return true;
	}

	public void Delete(MemberInfo member) {

		member = prefix(member);

		values.set(member.getNickName(), null);
		userList.remove(member.getNickName());
	}

	public MemberInfo SelectById(String id) {

		id = prefix(id);
		Object obj = values.get(id);

		if (obj != null) {
			return (MemberInfo) obj;
		}

		return null;
	}

	public boolean isExist(String id) {
		id = prefix(id);

		Object obj = values.get(id);

		if (obj != null) {
			return true;
		}

		return false;
	}

}
