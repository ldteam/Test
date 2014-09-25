package com.socialnet.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.net.ParseException;
import android.util.Log;

import com.socialnet.map_entry.ListEntry;

public class Database {
	
	public Database()
			{
	
			}
	
	public ContentValues[] check(String URL, String request_database,  String log, String pass) throws JSONException
	{
		Log.i("MY_TAG", "Database.check: 111");
	 InputStream inputStream = null;
	        String result = "";
	        ContentValues[] tweetsValues = null;
	  	   try
	        {
	        	DefaultHttpClient httpclient = new DefaultHttpClient();
	        	HttpPost httpPost = new HttpPost(URL);
	        	httpPost.setHeader("Accept", "application/json");
	        	httpPost.setHeader("Content-type", "application/json");
	        	httpPost.setHeader("request_database", request_database);
	        	httpPost.setHeader("log", log);
	        	httpPost.setHeader("pass", pass);
	        	
	        	HttpResponse httpResponse = httpclient.execute(httpPost);
	        	inputStream = httpResponse.getEntity().getContent();
	        	Log.i("MY_TAG", log+"_"+pass);
	        	if(inputStream != null)
	        	{
	        		Log.i("MY_TAG", "input_Stream!=0 "+114);
	        		result = convertInputStreamToString(inputStream);
	        		Log.i("MY_TAG","RESULT CHECK "+ result);
	        	}
	        	else{
	        		result = "Did not work!";
	        		Log.i("MY_TAG", "Did not work!");
	        	}
	        	//    Log.v("My", result);

	   } catch (Exception e) {
		   Log.d("MY_TAG", "catch exception Database.check"+ e.getLocalizedMessage());
	   }

	 try{
	                    JSONArray jArray = new JSONArray(result);
	                    
	                     tweetsValues=new ContentValues[jArray.length()];
	                    
	                     tweetsValues =  new ContentValues[jArray.length()];
	                    
	                    JSONObject json_data=null;

	                    for(int i=0;i<jArray.length();i++){
	                            json_data = jArray.getJSONObject(i);
	                            
	                            Iterator<?> keys = json_data.keys();
	                            
	                        	ContentValues tweet = new ContentValues();
	                            
	    			            while( keys.hasNext())
	    			            {
	    			                String key = (String)keys.next();    			                
	    			                tweet.put(key, json_data.getString(key));                  		
	    			                
	    			            }            		
	                  	Log.v("MY_TAG", "user_id: "+tweet.get("user_id").toString());
	                	//Log.v("MY_TAG", "surname: "+tweet.get("surname").toString());
	                  	tweetsValues[i] = tweet;
	                  	//id = Integer.parseInt(tweet.get("iduser").toString());
	                    	
	                    }
	   }catch (ParseException e1){
	                        e1.printStackTrace();
	                        Log.v("MY_TAG", "error json");
	   }                   
		return tweetsValues;
	} //end check
	
	
	public String insert(String URL, String request_database,  ListEntry entry, String request_columns)
	{
		 InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // 1. create HttpClient
	            DefaultHttpClient httpclient = new DefaultHttpClient();
	 
	            // 2. make POST request to the given URL
	            //	//"http://192.168.0.101:8080/send_picture/json"; 
	            HttpPost httpPost = new HttpPost(URL);
	            
	            
               String json = "";
	            
	        
	 
	            // 3. build jsonObject
	          
	            JSONObject jsonObject = Json_object(entry);
	            
	            
	            
	            
	            // 4. convert JSONObject to JSON to String
	            json = jsonObject.toString();
	 
	            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
	            // ObjectMapper mapper = new ObjectMapper();
	            // json = mapper.writeValueAsString(person); 
	 
	            // 5. set json to StringEntity
	            StringEntity se = new StringEntity(json, "UTF-8");
	 
	            // 6. set httpPost Entity
	            httpPost.setEntity(se);
	            
	   
	 
	            // 7. Set some headers to inform server about the type of the content   
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	            
	            httpPost.setHeader("request_database", request_database);
	            httpPost.setHeader("request_columns", request_columns);
	            
	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	 
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // 10. convert inputstream to string
	            if(inputStream != null)
	            {
	                result = convertInputStreamToString(inputStream);
	          //Log.v("My", result);
	            }
	            else
	                result = "Did not work!";
	           Log.v("My", result);
	 
	        } catch (Exception e) {
	            Log.d("My", e.getLocalizedMessage());
	        }
	 
	        // 11. return result
	        return result;  
	        
	          

	}

	
	public boolean delete(String URL, String id, String request_database)
	{
		 InputStream inputStream = null;
	        String result = "";
	        try {
	 
	  
	            DefaultHttpClient httpclient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(URL);
	            
	            
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	            
	            httpPost.setHeader("request_database", request_database);
	            httpPost.setHeader("id",  id);
	            
	            
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	            
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();

	            // 10. convert inputstream to string
	            if(inputStream != null)
	            {
	                result = convertInputStreamToString(inputStream);
	         
	            }
	            else
	                result = "Did not work!";
	        //    Log.v("My", result);
	        }
	            
	            catch (Exception e) {
		            Log.d("My", e.getLocalizedMessage());
		        }
	            
	            
	            
		return true;
	}
	
	
	public ContentValues[] select(String URL, String request_database) throws JSONException
	{
		 InputStream inputStream = null;
	        String result = "";
	        ContentValues[] tweetsValues = null;
	        try
	        {
		
		  DefaultHttpClient httpclient = new DefaultHttpClient();
			 

          HttpPost httpPost = new HttpPost(URL);
          
          
          httpPost.setHeader("Accept", "application/json");
          httpPost.setHeader("Content-type", "application/json");
          
          httpPost.setHeader("request_database", request_database);
     
          
          
          HttpResponse httpResponse = httpclient.execute(httpPost);
     	 
          // 9. receive response as inputStream
          inputStream = httpResponse.getEntity().getContent();

          // 10. convert inputstream to string
          if(inputStream != null)
          {
              result = convertInputStreamToString(inputStream);
       
          }
          else
              result = "Did not work!";
      //    Log.v("My", result);

      } catch (Exception e) {
          Log.d("My", e.getLocalizedMessage());
      }

          
          
		
	
	                    try{
	                    JSONArray jArray = new JSONArray(result);
	                    
	                     tweetsValues=new ContentValues[jArray.length()];
	                    
	                     tweetsValues =  new ContentValues[jArray.length()];
	                    
	                    JSONObject json_data=null;

	                    for(int i=0;i<jArray.length();i++){
	                            json_data = jArray.getJSONObject(i);
	                            
	                            Iterator<?> keys = json_data.keys();
	                            
	                        	ContentValues tweet = new ContentValues();
	                            
	    			            while( keys.hasNext())
	    			            {
	    			                String key = (String)keys.next();    			                
	    			                tweet.put(key, json_data.getString(key));                  		
	    			                
	    			            }
	                     
	               
	                    		
	        //          	Log.v("My", "name: "+tweet.get("name").toString());
	          //      	Log.v("My", "surname: "+tweet.get("surname").toString());
                			
	                    	tweetsValues[i] = tweet;
	                    	
	                    }

	            
	            		

	                    }catch (ParseException e1){
	                        e1.printStackTrace();
	                        Log.v("My", "error json");
	                    }

	 
	        
	  

	        		


	       
	                    
	                    
	                    
		return tweetsValues;
	}
	
	public ContentValues[] select_one(String URL, String request_database,  String id) throws JSONException
	{
		   ContentValues[] tweetsValues = null;
		   Log.i("My", "Database.select_one "+request_database+"_"+id);
		   
		
	   	 InputStream inputStream = null;
	        String result = "";
	     
	        try  
	        {
	        	DefaultHttpClient httpclient = new DefaultHttpClient();
			    HttpPost httpPost = new HttpPost(URL);
       
			    httpPost.setHeader("Accept", "application/json");
			    httpPost.setHeader("Content-type", "application/json");
       
			    httpPost.setHeader("request_database", request_database);
			    httpPost.setHeader("id", id);
			 
			    HttpResponse httpResponse = httpclient.execute(httpPost);
  	 
			    inputStream = httpResponse.getEntity().getContent();

			    if(inputStream != null)
			    {
			    	result = convertInputStreamToString(inputStream);
			    }else
			    	result = "Did not work!";
			    Log.i("MY_TAG", "Database.select_one, result= "+result);

	        } catch (Exception e) {
	        	Log.d("MY_TAG", e.getLocalizedMessage());
	        }

	        
	                    try{
	                    JSONArray jArray = new JSONArray(result);
	                    
	                     tweetsValues=new ContentValues[jArray.length()];
	                    
	                     tweetsValues =  new ContentValues[jArray.length()];
	                    
	                    JSONObject json_data=null;
	                    for(int i=0;i<jArray.length();i++){
	                            json_data = jArray.getJSONObject(i);
	                            
	                            Iterator<?> keys = json_data.keys();
	                            
	                        	ContentValues tweet = new ContentValues();
	    			            while( keys.hasNext())
	    			            {
	    			                String key = (String)keys.next();    			                
	    			                tweet.put(key, json_data.getString(key));                  		
	    			                
	    			            }
	               
	                    		
	                  	//Log.v("MY_TAG", "name: "+tweet.get("name").toString());
	                  	//Log.v("MY_TAG", "surname: "+tweet.get("surname").toString());
             			
	                    	tweetsValues[i] = tweet;
	                    	  
	                    }	

	                    }catch (ParseException e1){
	                        e1.printStackTrace();
	                        Log.v("MY_TAG", "error json");
	                    }                   
		return tweetsValues;
	}
	
	
	public String update(String URL, String request_database,  String id,  ListEntry entry)
	{
		 InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // 1. create HttpClient
	            DefaultHttpClient httpclient = new DefaultHttpClient();
	 
	            // 2. make POST request to the given URL
	            //	//"http://192.168.0.101:8080/send_picture/json"; 
	            HttpPost httpPost = new HttpPost(URL);
	            
	            
            String json = "";
	            
	            //String[] params = new String[]{url, fileUri.toString(), GetAbsolutePath, surname.getText().toString(),
			//name.getText().toString(), login.getText().toString(), password.getText().toString()};
	 
	            // 3. build jsonObject
	          
	            JSONObject jsonObject = Json_object(entry);
	            
	            
	            
	            // 4. convert JSONObject to JSON to String
	            json = jsonObject.toString();
	 
	       
	 
	            // 5. set json to StringEntity
	            StringEntity se = new StringEntity(json, "UTF-8");
	 
	            // 6. set httpPost Entity
	            httpPost.setEntity(se);
	            
	   
	 
	            // 7. Set some headers to inform server about the type of the content   
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	            
	            httpPost.setHeader("request_database", request_database);
	            httpPost.setHeader("id",  id);
	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	 
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // 10. convert inputstream to string
	            if(inputStream != null)
	            {
	                result = convertInputStreamToString(inputStream);
	            Log.v("My", result);
	            }
	            else
	                result = "Did not work!";
	            Log.v("My", result);
	 
	        } catch (Exception e) {
	    Log.d("My", e.getLocalizedMessage());
	        }
	 
	        // 11. return result
	        return result;
	        
		
		

	}


	//ответ с сервера
		private static String convertInputStreamToString(InputStream inputStream) throws IOException {
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        
	        
	        return result;
	        
	        
			
		
	 
	    }  
		
		
		
		//создаем json объект
		public  JSONObject Json_object(ListEntry entry)
		{
			  JSONObject jsonObject = new JSONObject();
			  
			
			  
	          try {
	        	  
	        	  for (int i=0; i<entry.Count(); i++)
	        	  {
	  
	             
	           
				jsonObject.put((String) entry.GetObject(i).getKey(), (String) entry.GetObject(i).getValue());
				

	        	  }
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	          
	          return jsonObject;
		}
		
	
}
