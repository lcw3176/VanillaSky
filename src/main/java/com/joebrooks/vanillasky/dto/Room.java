package com.joebrooks.vanillasky.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Room implements Serializable {

	private String roomName;
	private List<MemberInfo> memberList = new ArrayList<MemberInfo>();
	private int maxNumber;
	private int nowNumber;
	private String answer;
	private boolean running;

	
	public void addMember(MemberInfo memberInfo) {
		memberList.add(memberInfo);
	}
	
	public void removeMember(MemberInfo memberInfo) {
		memberList.remove(memberInfo);
	}
}
