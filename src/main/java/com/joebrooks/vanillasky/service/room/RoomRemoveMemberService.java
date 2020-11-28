package com.joebrooks.vanillasky.service.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.MemberDao;
import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.dto.Room;


public class RoomRemoveMemberService {


	@Autowired
	private RoomDao roomDao;
	@Autowired
	private MemberDao memberDao;
	
	public boolean RemoveMember(String roomName, String id) {
		Room room = roomDao.SelectByName(roomName);
		if(memberDao.isExist(id)) {
			MemberInfo member = memberDao.SelectById(id);
			member.setPresenter(false);
			room.removeMember(member);
			room.setNowNumber(room.getNowNumber() - 1);
			
			if(room.getNowNumber() == 0) {
				return roomDao.Delete(room);
			}
			
			return roomDao.update(room);
		}

		return true;

	}
}
