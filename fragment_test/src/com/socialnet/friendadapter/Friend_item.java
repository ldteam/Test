package com.socialnet.friendadapter;

import android.graphics.Bitmap;

public class Friend_item {
	
	public String id;
	Bitmap avatar_picture;
	String FIO;
	int Online_or_not_picture;
	
	public Friend_item(String id, Bitmap avatar_picture, String FIO, int Online_or_not_picture )
	{
		this.id=id;
		this.avatar_picture=avatar_picture;
		
		this.FIO=FIO;
		this.Online_or_not_picture=Online_or_not_picture;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public Bitmap getAvatar_picture()
    {
		return avatar_picture;
	}
	public void setAvatar_picture(Bitmap avatar_picture) 
	{
		this.avatar_picture = avatar_picture;
	}
	
	public String getFIO() 
	{
		return FIO;
	}
	
	public void setFIO(String fIO) 
	{
		FIO = fIO;
	}
	
	public int getOnline_or_not_picture() 
	{
		return Online_or_not_picture;
	}
	
	public void setOnline_or_not_picture(int online_or_not_picture) 
	{
		Online_or_not_picture = online_or_not_picture;
	}

	

}
