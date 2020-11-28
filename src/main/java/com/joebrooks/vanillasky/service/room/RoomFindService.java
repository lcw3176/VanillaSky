package com.joebrooks.vanillasky.service.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.Room;

public class RoomFindService {

	@Autowired
	private RoomDao roomDao;

	public List<Room> SelectAll(){
		return roomDao.SelectAll();
	}
	
	public Room GetRoomByName(String roomName) {
		return roomDao.SelectByName(roomName);
	}

	public boolean isExist(String roomName) {
		return roomDao.isExistRoom(roomName);
	}
	
	public boolean isRunning(String roomName) {
		return roomDao.SelectByName(roomName).isRunning();
	}

}
