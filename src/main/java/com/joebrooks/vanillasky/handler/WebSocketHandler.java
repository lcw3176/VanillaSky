package com.joebrooks.vanillasky.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.joebrooks.vanillasky.dto.MemberInfo;
import com.joebrooks.vanillasky.dto.Room;
import com.joebrooks.vanillasky.handler.WebSocketEnum.Data;
import com.joebrooks.vanillasky.handler.WebSocketEnum.PointParam;
import com.joebrooks.vanillasky.handler.WebSocketEnum.TextDetailType;
import com.joebrooks.vanillasky.handler.WebSocketEnum.TextParam;
import com.joebrooks.vanillasky.handler.WebSocketEnum.Type;
import com.joebrooks.vanillasky.service.member.MemberFindService;
import com.joebrooks.vanillasky.service.room.RoomAnswerService;
import com.joebrooks.vanillasky.service.room.RoomChangePresenterService;
import com.joebrooks.vanillasky.service.room.RoomFindService;
import com.joebrooks.vanillasky.service.room.RoomRegistService;
import com.joebrooks.vanillasky.service.room.RoomRemoveMemberService;
import com.joebrooks.vanillasky.service.room.RoomStartGameService;

public class WebSocketHandler extends TextWebSocketHandler {

	private static final Map<String, List<WebSocketSession>> sessionMap = new HashMap<String, List<WebSocketSession>>();

	@Autowired
	private RoomRegistService roomRegistService;
	@Autowired
	private MemberFindService memberFindService;
	@Autowired
	private RoomRemoveMemberService roomRemoveMemberService;
	@Autowired
	private RoomFindService roomFindService;
	@Autowired
	private JSONParser parser;
	@Autowired
	private JSONObject object;
	@Autowired
	private RoomAnswerService roomAnswerService;
	@Autowired
	private RoomChangePresenterService roomChangePresenterService;
	@Autowired
	private RoomStartGameService roomStartGameService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		try {
			Map<String, Object> map = session.getAttributes();

			String userId = (String) map.get("userId");
			String roomName = (String) map.get("roomName");

			List<WebSocketSession> temp;

			if (sessionMap.containsKey(roomName)) {
				temp = sessionMap.get(roomName);
				temp.add(session);
				sessionMap.put(roomName, temp);
			}

			else {
				temp = new ArrayList<WebSocketSession>();
				temp.add(session);
				sessionMap.put(roomName, temp);
			}

			if (!roomRegistService.isMember(roomName, userId)) {
				roomRegistService.RegistMember(roomName, memberFindService.GetMemberById(userId));
			}

			sendWelcomeAlarm(roomName, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
		String roomName = (String) map.get("roomName");

		if (sessionMap.get(roomName).remove(session)) {
			echoTextValue(TextDetailType.goodbye.toString(), roomName, userId, "");
			roomRemoveMemberService.RemoveMember(roomName, userId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> text) throws Exception {
		object = (JSONObject) parser.parse(text.getPayload().toString());
		String type = object.get("type").toString();

		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
		String roomName = (String) map.get("roomName");

		if (type.equals(Type.text.toString())) {
			String value = object.get("value").toString();
			String detailType = object.get("detailType").toString();

			if (detailType.equals(TextDetailType.answer.toString())) {
				if (!roomFindService.GetRoomByName(roomName).isRunning()) {
					return;
				}
					
				echoTextValue(TextDetailType.chat.toString(), roomName, userId, value);
				if (value.contains(roomAnswerService.getRoomAnswer(roomName))) {
					echoTextValue(TextDetailType.winner.toString(), roomName, userId, value);
					roomChangePresenterService.setRoomPresenter(roomName, userId);
					echoTextValue(TextDetailType.changePresenter.toString(), roomName, userId, value);
				}
				
			}

			else if (detailType.equals(TextDetailType.gameStart.toString())) {
				roomStartGameService.startGame(roomName);
				echoTextValue(detailType, roomName, userId, value);
				roomAnswerService.setRoomAnswer(roomName);

				object.put(Data.type, Type.text.toString());
				object.put(Data.detailType, TextDetailType.answer.toString());
				object.put(TextParam.userId, userId);
				object.put(TextParam.value, roomAnswerService.getRoomAnswer(roomName));

				session.sendMessage(new TextMessage(object.toJSONString()));
			}

			else {
				echoTextValue(detailType, roomName, userId, value);
			}

		}

		else if (type.equals(Type.point.toString())) {
			String coorx = object.get("coorx").toString();
			String coory = object.get("coory").toString();
			String detailType = object.get("detailType").toString();
			
			echoPointValue(detailType, roomName, coorx, coory);
		}
	}
	
	// text Type 보낸후 jsonObject clear
	@SuppressWarnings("unchecked")
	private void echoTextValue(String detailType, String roomName, String userId, String value) {
		object.put(Data.type, Type.text.toString());
		object.put(Data.detailType, detailType);
		object.put(TextParam.userId, userId);
		object.put(TextParam.value, value);
		
		try {
			for (WebSocketSession i : sessionMap.get(roomName)) {
				i.sendMessage(new TextMessage(object.toJSONString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			object.clear();
		}
		
	}
	
	// point Type 보낸후 jsonObject clear
	@SuppressWarnings("unchecked")
	private void echoPointValue(String detailType, String roomName, String coorx, String coory) {
		object.put(Data.type, Type.point.toString());
		object.put(Data.detailType, detailType);
		object.put(PointParam.coorx, coorx);
		object.put(PointParam.coory, coory);
		
		try {
			for (WebSocketSession i : sessionMap.get(roomName)) {
				i.sendMessage(new TextMessage(object.toJSONString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			object.clear();
		}
		
	}


	@SuppressWarnings("unchecked")
	private void sendWelcomeAlarm(String roomName, String userId) {

		Room room = roomFindService.GetRoomByName(roomName);

		try {

			for (WebSocketSession i : sessionMap.get(roomName)) {
				for (MemberInfo member : room.getMemberList()) {
					object.put(Data.type, Type.text.toString());
					object.put(Data.detailType, TextDetailType.welcome.toString());
					object.put(TextParam.userId, member.getNickName());
					object.put(TextParam.userIp, member.getIpAddress());
					object.put(TextParam.isPresenter, member.isPresenter());

					i.sendMessage(new TextMessage(object.toJSONString()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			object.clear();
		}

	}

}
