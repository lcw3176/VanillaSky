package com.joebrooks.vanillasky.service.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.dto.Room;

public class RoomChangePresenterService {

	@Autowired
	private RoomDao roomDao;
	
	public void setRoomPresenter(String roomName, String userId) {

		Room room = roomDao.SelectByName(roomName);
		List<MemberInfo> memList = room.getMemberList();
		
		for(MemberInfo i : memList) {
			if(i.getNickName().equals(userId)) {
				i.setPresenter(true);
				break;
			}
		}
		
		room.setMemberList(memList);
		roomDao.update(room);
	}
}
