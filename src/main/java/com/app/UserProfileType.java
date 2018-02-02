package com.app;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	USER("shit"),
	DBA("fuck"),
	ADMIN("crap");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
