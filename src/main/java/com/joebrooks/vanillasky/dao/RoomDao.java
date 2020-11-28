package com.joebrooks.vanillasky.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.dto.Room;

public class RoomDao {

	@Autowired
	private ValueOperations<String, Object> values;

	private static List<String> roomName = new ArrayList<String>();
	
	public Room prefix(Room room) {
		if (room.getRoomName().contains("+")) {
			room.setRoomName(room.getRoomName().replaceAll("\\+", " "));
		}
		
		return room;
	}
	
	public String prefix(String roomName) {
		if (roomName.contains("+")) {
			roomName.replaceAll("\\+", " ");
		}
		
		return roomName;
	}

	public boolean Insert(Room room) {
		room = prefix(room);
		Object obj = values.get(room.getRoomName());

		if (obj != null) {
			return false;
		}

		values.set(room.getRoomName(), room);
		roomName.add(room.getRoomName());
		
		return true;
	}


	public boolean update(Room room) {

		if (values.get(room.getRoomName()) == null) {
			return false;
		}

		values.set(room.getRoomName(), room);
		return true;
	}


	public boolean Delete(Room room) {
		if (values.get(room.getRoomName()) != null) {
			values.set(room.getRoomName(), null);
			roomName.remove(room.getRoomName());
			
			return true;
		}

		return false;

	}

	public List<Room> SelectAll() {
		List<Object> roomList = values.multiGet(roomName);

		if (roomList != null) {
			return roomList.stream().map(s -> (Room) s).collect(Collectors.toList());
		}

		return null;
	}

	public Room SelectByName(String name) {
		name = prefix(name);
		
		Object obj = values.get(name);

		if (obj != null) {
			return (Room) obj;
		}

		return null;
	}

	public boolean isExistMember(String roomName, String userName) {
		roomName = prefix(roomName);
		userName = prefix(userName);

		Object roomObj = values.get(roomName);
		if (roomObj != null) {
			for (MemberInfo i : ((Room) roomObj).getMemberList()) {
				if (i.getNickName().equals(userName)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isExistRoom(String roomName) {
		roomName = prefix(roomName);

		Object obj = values.get(roomName);

		if (obj != null) {
			return true;
		}

		return false;
	}
}
