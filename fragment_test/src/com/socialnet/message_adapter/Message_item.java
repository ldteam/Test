package com.socialnet.message_adapter;

public class Message_item {
	
	private String date;
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	public String get_msgID() {
		return msgID;
	}


	public void get_msgID(String iD) {
		msgID = iD;
	}


	private String Message;
	private String msgID;
	
	
	public Message_item(String msgID,String Message, String date )
	{
		this.date=date;
		this.Message=Message;
		this.msgID=msgID;
		
	}

}
