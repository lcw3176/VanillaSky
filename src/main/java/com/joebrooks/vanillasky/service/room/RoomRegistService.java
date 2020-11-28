package com.joebrooks.vanillasky.service.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.dto.Room;


public class RoomRegistService {
	
	@Autowired
	private RoomDao roomDao;

	
	// 처음 방 만든사람을 게임 진행자로 만든다.
	public boolean RegistRoom(String roomName, int maxNumber, MemberInfo member) {
		Room room = new Room();
		MemberInfo memberInfo = member;
		memberInfo.setPresenter(true);
		room.setRoomName(roomName);
		room.setMaxNumber(maxNumber);
		room.addMember(memberInfo);
		room.setNowNumber(1);
		room.setRunning(false);
		
		return roomDao.Insert(room);
	}
	
	public boolean RegistMember(String roomName, MemberInfo member) {
		
		Room room = roomDao.SelectByName(roomName);
		room.addMember(member);
		room.setNowNumber(room.getNowNumber() + 1); 
		
		return roomDao.update(room);
	}
	
	
	public boolean isMember(String roomName, String userName) {
		
		return roomDao.isExistMember(roomName, userName);
		
	}
}
