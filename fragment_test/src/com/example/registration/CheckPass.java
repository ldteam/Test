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
			// Log.i("MY_TAG", "check");
			 String ID = "";
			 Connection con = new Connection(Url_path_to_server.UriCheckFromDatabase, DatabaseRequests.CheckUser, log, pass);		
				try {
					con.connect();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ContentValues[] tweetsValues =con.values;
			 //Log.i("MY_TAG", "check2");
			 try{
			 ID=tweetsValues[0].getAsString("user_id");
			 
			 
			 Log.i("My","CheckPass.Check, ID "+ID);
			 
			 }catch(Exception e){
				 
				 Log.i("MY_TAG", "check_Error_");
			 }
			 return ID;
	}

}
