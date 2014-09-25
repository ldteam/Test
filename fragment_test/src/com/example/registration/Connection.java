package com.example.registration;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.socialnet.database.Database;
import com.socialnet.map_entry.ListEntry;
import com.socialnet.picture.UploadPicture;

public class Connection extends Activity  {
	
	
	static ListEntry list;
	static String UriServer;
	static String RequestDatabase;
	static String Columns;
	static String getPath;
	
	static String  log, pass;
	
	ContentValues[] values=null;
	static String id;
     String response="";
	
	static int method=0; //номер метода, который вызываем, например insert=1, delete=2
	
	@SuppressWarnings("static-access")
	public Connection(String UriServer, String RequestDatabase, ListEntry list, String Columns )
	{
		this.UriServer=UriServer;
		this.RequestDatabase=RequestDatabase;
		this.list=list;
		this.Columns=Columns;
		method=1;
		
	}
	
    @SuppressWarnings("static-access")
    public Connection(String UriServer, String RequestDatabase, String id, ListEntry list )
	{
		this.UriServer=UriServer;
		this.RequestDatabase=RequestDatabase;
		this.id=id;
		this.list=list;
		
		method=2;
		
	}
	
	//метод select all,  method=3
		@SuppressWarnings("static-access")
		public Connection(String UriServer, String RequestDatabase)
		{
			this.UriServer=UriServer;
			this.RequestDatabase=RequestDatabase;
		
			
			method=3;
			
		}
	
	
		// select one, method=4
		@SuppressWarnings("static-access")
		public Connection(String UriServer, String RequestDatabase, String id)
		{
			this.UriServer=UriServer;
			this.RequestDatabase=RequestDatabase;
			this.id=id;
		
			
			method=4;
			
		}
	
		//метод delete, method=5, параметр a - всегда равен 0
    	public Connection(String UriServer, String RequestDatabase, String id, int a)
				{
					this.UriServer=UriServer;
					this.RequestDatabase=RequestDatabase;
					this.id=id;
				
					
					method=5;
					
				}
			
	
				// UploadPicture, method=6
    	public Connection(String getPath)
				{
					this.getPath=getPath;
				
					
					method=6;
					
				}
    	
              	//метод check, method=7
    			public Connection(String UriServer, String RequestDatabase, String log, String pass)
    					{
    						this.UriServer=UriServer;
    						this.RequestDatabase=RequestDatabase;
    						this.log=log;
    						this.pass = pass; 
    					
    						
    						method=7;
    						
    					}

	
public ContentValues[] returnvalues()
{
	return values;
}

	public String response()
	{
		return response;
	}
	
	public void connect() throws InterruptedException, ExecutionException
	{
		  switch (method) {
		    case 1: //метод insert
		    	SendHttpRequestTask_String str = new SendHttpRequestTask_String();
		    	response=str.execute().get();
					
		    	
		    	break;
		    	
		    case 2: //метод update
		    	
		      	 str= new SendHttpRequestTask_String();
				str.execute();
				
		    	
		    	break;
		    	
		    case 3://метод select all
		    	try {
		    SendHttpRequestTask_ContentValue t = new SendHttpRequestTask_ContentValue();
			values=t.execute().get();
				} 
				catch (InterruptedException e) {e.printStackTrace();}
				catch (ExecutionException e) {e.printStackTrace();}
		    	
		    	break;
		    	
		    case 4://Method select one
		    	
		    	try {
				    SendHttpRequestTask_ContentValue t = new SendHttpRequestTask_ContentValue();
					values=t.execute().get();
						} 
						catch (InterruptedException e) {e.printStackTrace();}
						catch (ExecutionException e) {e.printStackTrace();}
		    	
		    	break;
		    	
		    case 5://метод delete
		       str = new SendHttpRequestTask_String();
				str.execute();
		    	break;
		    	
		    case 6://метод UploadPicture
		    	 str = new SendHttpRequestTask_String();
				str.execute();
		    	break;
		    	
		    case 7://метод  check
		    	try {
		         SendHttpRequestTask_ContentValue t = new SendHttpRequestTask_ContentValue();
			      values=t.execute().get();
				} 
				catch (InterruptedException e) {e.printStackTrace();}
				catch (ExecutionException e) {e.printStackTrace();}
		    	
		    	break;
		    	
		    default:
		    		
		    break;
		  }
        	
	}
	

	
	private static class SendHttpRequestTask_String extends AsyncTask<String, Void, String> 
	{

		@Override
		protected String doInBackground(String... params) {
		
			 String response="";
			Database dt = new Database();
		
			switch (method) {
			    case 1: //метод insert
			    	response=dt.insert(UriServer, RequestDatabase, list, Columns);
			    	
			    	break;
			    	
			    case 2: //метод update
			    	response=dt.update(UriServer, RequestDatabase, id, list);
			    	break;
			    case 5://метод delete
			    	dt.delete(UriServer, id, RequestDatabase);
			    	break;
			    case 6:
			    	UploadPicture upload = new UploadPicture(getPath);
			    	response=upload.UploadPicture_from_Server();
			    	break;
			    	default:
			    		
			    		break;
			 }
			return response;
		}


		
	}
	
	
private static class SendHttpRequestTask_ContentValue extends AsyncTask<String, Void, ContentValues[]> 
{

		
		@Override
		protected ContentValues[] doInBackground(String... params) {
			 ContentValues[] tweetsValues=null;
			try {
			
			Database dt = new Database();
			
			if (method==3)//Method select all)
			{
            // tweetsValues=	dt.select(Url_path_to_server.UriSelectFromDatabase, DatabaseRequests.SelectAllUsers);
			  tweetsValues=	dt.select(UriServer, RequestDatabase);
			}
			else if (method==4)//Method select one)
			{
				 tweetsValues=	dt.select_one(UriServer, RequestDatabase, id);
			}
			else if (method==7)//метод check)
			{
				 tweetsValues = dt.check(UriServer, RequestDatabase, log, pass);
				 //Log.d("MY_TAG", "!!!"+tweetsValues[0].getAsString("iduser"));
			}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			Log.v("My", "error in select method");
			}
			


			return tweetsValues;
		}
		
   
		 
		
		
	}
}
