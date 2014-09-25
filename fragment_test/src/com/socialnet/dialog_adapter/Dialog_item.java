package com.socialnet.dialog_adapter;

import android.graphics.Bitmap;

public class Dialog_item {
	
	
	public Dialog_item(String friend_id, Bitmap avatar_picture, String FIO, String lastMessage, String date)
	{
		this.friend_id=friend_id;
		this.avatar_picture=avatar_picture;
        this.FIO=FIO;
		this.LastMessage=lastMessage;
		this.Date=date;
		
	}  
	
	private String friend_id;
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public Bitmap getAvatar_picture() {
		return avatar_picture;
	}
	public void setAvatar_picture(Bitmap avatar_picture) {
		this.avatar_picture = avatar_picture;
	}
	public String getFIO() {
		return FIO;
	}
	public void setFIO(String fIO) {
		FIO = fIO;
	}
	public String getLastMessage() {
		return LastMessage;
	}
	public void setLastMessage(String lastMessage) {
		LastMessage = lastMessage;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	private Bitmap avatar_picture;
	private String FIO;
	private String LastMessage;
	private String Date;
	

}
