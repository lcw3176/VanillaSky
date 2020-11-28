package com.joebrooks.vanillasky.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class MemberInfo implements Serializable{
	private String nickName;
	private String ipAddress;
	private boolean Presenter;
	
}
