package com.joebrooks.vanillasky.service.member;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.MemberDao;
import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.Room;



public class MemberRemoveService {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RoomDao roomDao;
	

	public boolean Remove(String id) {
		
		try {

			for (Room room : roomDao.SelectAll()) {
				
				if (roomDao.isExistMember(room.getRoomName(), id)) {
					roomDao.Delete(room);
				}
			}

			memberDao.Delete(memberDao.SelectById(id));

		} catch (Exception e) {
			return false;
		}

		return true;

	}
}
