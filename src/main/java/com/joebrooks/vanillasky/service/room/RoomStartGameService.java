package com.joebrooks.vanillasky.service.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.Room;

public class RoomStartGameService {

	@Autowired
	private RoomDao roomDao;
	
	public void startGame(String roomName) {
		Room room = roomDao.SelectByName(roomName);
		room.setRunning(true);
		roomDao.update(room);
	}
}
