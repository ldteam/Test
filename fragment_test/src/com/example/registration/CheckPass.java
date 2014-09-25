package com.example.registration;


import java.util.concurrent.ExecutionException;

import android.content.ContentValues;
import android.util.Log;

import com.socialnet.database.DatabaseRequests;
import com.socialnet.url.Url_path_to_server;

public class CheckPass {
	
	public CheckPass() {
		
	}
	   
	public String Check(String log, String pass) {
			 String ID = "";
			 Connection con = new Connection(Url_path_to_server.UriCheckFromDatabase, DatabaseRequests.CheckUser, log, pass);		
				try {
					con.connect();
					ContentValues[] tweetsValues =con.values;
					 try{
						 ID=tweetsValues[0].getAsString("user_id");
						 //Log.i("My","CheckPass.Check, ID "+ID);
					 }catch(Exception e){					 
					 }
				} catch (InterruptedException e1) {
					ID = "-1";
					//e1.printStackTrace();
				} catch (ExecutionException e1) {
					//e1.printStackTrace();
					ID = "-1";
				}		
			 return ID;
	}

}
