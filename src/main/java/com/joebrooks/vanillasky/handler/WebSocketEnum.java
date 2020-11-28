package com.joebrooks.vanillasky.handler;

public class WebSocketEnum {
	public enum Data{
		type, detailType
	}
	
	public enum Type {
		text, point
	}
	
	public enum TextDetailType{
		changePresenter, answer, winner, gameStart, welcome, goodbye, chat
	}
	
	public enum PointDetailType{
		initDraw, draw, erase, clearDraw
	}
	
	public enum TextParam{
		userId, userIp, value, isPresenter
	}
	
	public enum PointParam{
		coorx, coory
	}
}
