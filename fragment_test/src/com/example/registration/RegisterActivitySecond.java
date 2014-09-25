package com.example.registration;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fragment_test.R;
import com.socialnet.database.DatabaseRequests;
import com.socialnet.map_entry.ListEntry;
import com.socialnet.map_entry.MyEntry;
import com.socialnet.spinner.SpinAdapter;
import com.socialnet.spinner.SpinnerItem;
import com.socialnet.url.Url_path_to_server;

public class RegisterActivitySecond extends Activity implements OnClickListener {
	
	Button register;
	 Spinner spinner_region;
	 Spinner spinner_city;
	 
	 EditText mobile_phone;
	 
	 private SpinAdapter adapter;
	 private SpinAdapter adapter2;
	 
	 
	 String id_region="";
	 String id_city="";
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.register_form_second);
		
		mobile_phone=(EditText) findViewById(R.id.editText1);
		register=(Button) findViewById(R.id.button1);
		register.setOnClickListener(this);
	     SpinnerItem[] users;
	
	
	     try {
			users = SelectRegionFromDatabase();
		
		  adapter = new SpinAdapter(RegisterActivitySecond.this, android.R.layout.simple_spinner_item, users);
			 
			spinner_region=(Spinner) findViewById(R.id.spinner1);		
			spinner_city=(Spinner) findViewById(R.id.spinner2);
			
			spinner_region.setAdapter(adapter);
			
			users = SelectRegionFromDatabase();
			  adapter = new SpinAdapter(RegisterActivitySecond.this, android.R.layout.simple_spinner_item, users);
				 
				spinner_region=(Spinner) findViewById(R.id.spinner1);		
				spinner_city=(Spinner) findViewById(R.id.spinner2);
				
				spinner_region.setAdapter(adapter);

				spinner_region.setOnItemSelectedListener(new OnItemSelectedListener() {
			      @Override
			      public void onItemSelected(AdapterView<?> parent, View view,
			          int position, long id) {
			    	  SpinnerItem user = adapter.getItem(position);
		               // Here you can do the action you want to...
		               Toast.makeText(RegisterActivitySecond.this, "ID: " + user.getId() + "\nName: " + user.getName(),
		                   Toast.LENGTH_SHORT).show();
		               
		               id_region=user.getId();
		               
		               try {
		            	   
		            	   adapter2= new SpinAdapter(RegisterActivitySecond.this, android.R.layout.simple_spinner_item, SelectCityFromDatabase(user.getId()));
		            	   spinner_city.setAdapter(adapter2);
		            	   spinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
								// TODO Auto-generated method stub
								  SpinnerItem user2 = adapter2.getItem(position1);
							       Toast.makeText(RegisterActivitySecond.this, "ID: " + user2.getId() + "\nName: " + user2.getName(),
						                   Toast.LENGTH_SHORT).show();
							       
							       id_city=user2.getId();
							       
							       
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								// TODO Auto-generated method stub
								
							}
		            		   
		            	   });
		            	   
		            	   
		            	   
		            	   
		            	   
		            	   
		            	   
		            	   
		            	   
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		               
			      }
			      @Override
			      public void onNothingSelected(AdapterView<?> arg0) {
			      }
			
			    });
				
		
	
		
		
		
		
		
		
	
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	@Override
	public void onClick(View v) {
		
		Intent intent1 = getIntent();

		String surname=intent1.getStringExtra("surname");    
			Log.v("My","surname"+ surname);
			Log.v("My","name"+ intent1.getStringExtra("name"));
			Log.v("My","login"+ intent1.getStringExtra("login"));
			Log.v("My","password"+ intent1.getStringExtra("password"));
			Log.v("My","GetAbsolutePath"+ intent1.getStringExtra("GetAbsolutePath"));
			
			
			    ListEntry list = new ListEntry();
	        	list.Add(new MyEntry<String, String>("login",intent1.getStringExtra("login")));
	    	   list.Add(new MyEntry<String, String>("password",intent1.getStringExtra("password")));
	
	    	
	    	 
		    	
		     //insert into database
	    	 Connection con = new Connection(Url_path_to_server.UriProcedureInsertInAuthorisation, DatabaseRequests.InsertIntoAuthorisation,   list, "login-password");
	    	
	    	
		    	
	    	 try {
				con.connect();
				if (Integer.parseInt(con.response())>0)
				{
				
					
					//take id clients
					ListEntry list2 = new ListEntry();
					
					  list2.Add(new MyEntry<String, String>("user_id", con.response()));
						
						Log.v("My","user_id "+ con.response());
					
					list2.Add(new MyEntry<String, String>("name", intent1.getStringExtra("name")));
					Log.v("My","name "+ intent1.getStringExtra("name"));
					
			    	list2.Add(new MyEntry<String, String>("surname",intent1.getStringExtra("surname")));
			    	Log.v("My","surname "+ intent1.getStringExtra("surname"));
			    	
			     	if(!TextUtils.isEmpty(mobile_phone.getText().toString()))
			    	{
			    	list2.Add(new MyEntry<String, String>("mobile_phone",mobile_phone.getText().toString()));
			    	}
			    	else
			    	{
			    		list2.Add(new MyEntry<String, String>("mobile_phone",""));
			    	}
			     	
			    	
			    	list2.Add(new MyEntry<String, String>("id_region",id_region));
			     	Log.v("My","id_region "+id_region);
			    	list2.Add(new MyEntry<String, String>("id_city", id_city));
			      	Log.v("My","id_city "+id_city);
			      	
			  
			
			       	
					   
		       	 Connection con1 = new Connection(Url_path_to_server.UriInsertInDatabase, DatabaseRequests.InsertIntoAnketa,   list2, "user_id-name-surname-mobile_phone-id_region-id_city");
			 	  con1.connect();
			   	 
			 	  if(intent1.getStringExtra("GetAbsolutePath")!=null)
			 	  {
			 	  Connection con2=new Connection(intent1.getStringExtra("GetAbsolutePath"));
			 	  con2.connect();
			 		Log.v("My","response picture "+con2.response());
			 	  }
			    	
			    	Toast toast = Toast.makeText(this, "Cпасибо за регистрацию!", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				
				  Intent intent5 = new Intent(this, EnterActivity.class);
			        startActivity(intent5);
			    	
				}
				else
				{

					Toast toast = Toast.makeText(this, "Mistake in registrtion", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	//	Intent intent1 = getIntent();
		
//	String surname=intent1.getStringExtra("surname");    
	//	Log.v("My","surname"+ surname);
		/*String name=  intent1.getStringExtra("name");
		Log.v("My","name"+ name);
		String login=   intent1.getStringExtra("login");
		Log.v("My","login"+ login);
		String password= intent1.getStringExtra("password");
		Log.v("My","password"+ password);
		  
		String GetAbsolutePath =intent1.getStringExtra("GetAbsolutePath");
		Log.v("My","GetAbsolutePath"+ GetAbsolutePath); 
		
		
		Log.v("My","id_region"+ id_region); 
		Log.v("My","id_city"+ id_city);
		*/ 
	}
	
	
	
	
	
	//возвращает данные для spinner_region
private 	 SpinnerItem[]	SelectRegionFromDatabase() throws JSONException
	{
	 SpinnerItem[] users;
	
	Connection con = new Connection(Url_path_to_server.UriSelectFromDatabase, DatabaseRequests.SelectFromRegion);
	try {
		con.connect();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ContentValues[] tweets=	con.returnvalues();
	 users= new SpinnerItem[tweets.length];
	//Uri_server, Request, id, int =0
	// con = new Connection(Url_path_to_server.UriDeleteFromDatabase, DatabaseRequests.DeleteUser, Integer.toString(53), 0);
//    	con.connect();

  // 	 con = new Connection(Url_path_to_server.UriDeleteFromDatabase, DatabaseRequests.DeleteUser, Integer.toString(53), 0);
 	//con.connect();

for (int i=0; i<tweets.length; i++)
{
	users[i]= new SpinnerItem();
    users[i].setId(tweets[i].getAsString("id"));
	users[i].setName(tweets[i].getAsString("name"));
//Log.v("My","name"+ (String) users[i].getName());
}

return users;
		
	}


//возвращает данные для spinner_city
private SpinnerItem[] SelectCityFromDatabase(String id) throws JSONException
{
	 SpinnerItem[] users;
	
	Connection con = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId, DatabaseRequests.SelectFromCity, id);
	try {
		con.connect();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ContentValues[] tweets=	con.returnvalues();
	 users= new SpinnerItem[tweets.length];
	//Uri_server, Request, id, int =0
	// con = new Connection(Url_path_to_server.UriDeleteFromDatabase, DatabaseRequests.DeleteUser, Integer.toString(53), 0);
//   	con.connect();

 // 	 con = new Connection(Url_path_to_server.UriDeleteFromDatabase, DatabaseRequests.DeleteUser, Integer.toString(53), 0);
	//con.connect();

for (int i=0; i<tweets.length; i++)
{
	users[i]= new SpinnerItem();
   users[i].setId(tweets[i].getAsString("id"));
	users[i].setName(tweets[i].getAsString("name"));
//Log.v("My","name"+ (String) users[i].getName());
}

return users;
		
	}

	
	






}
