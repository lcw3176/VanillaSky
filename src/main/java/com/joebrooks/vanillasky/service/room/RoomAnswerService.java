package com.joebrooks.vanillasky.service.room;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.joebrooks.vanillasky.dao.RoomDao;
import com.joebrooks.vanillasky.dto.Room;

public class RoomAnswerService {

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private JSONParser parser;
	
	private final int lastIndex = 940;

	public void setRoomAnswer(String roomName) throws Exception {
		Room room = roomDao.SelectByName(roomName);
		JSONArray arr = (JSONArray)parser.parse(new FileReader("D:\\project\\Spring\\VanillaSky\\src\\main\\resources\\word.json"));
		int index = (int)(Math.random() * lastIndex);
		JSONObject obj = (JSONObject)parser.parse(arr.get(index).toString());
		
		String answer = obj.get("word").toString();
		room.setAnswer(answer);
		
		roomDao.update(room);
	}
	
	public String getRoomAnswer(String roomName) {
		Room room = roomDao.SelectByName(roomName);
		
		return room.getAnswer();
	}
}
