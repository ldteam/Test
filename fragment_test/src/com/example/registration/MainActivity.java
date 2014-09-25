package com.example.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.android_db.LogIn;
import com.example.fragment_test.R;
import com.example.fragment_test.UserId;
import com.socialnet.database.DatabaseRequests;


public class MainActivity extends FragmentActivity implements OnClickListener {
	
	Button register;
	Button enter;
	
	String _userId;
	SharedPreferences sPref;
	final String SAVED_TEXT = "UserId";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogIn helper;
		SQLiteDatabase db;
		
		helper = new LogIn(this);
	    db = helper.getWritableDatabase();
	    String log = "", pass = "";
	    String query = "SELECT * from log";
    	Cursor cursor2 = db.rawQuery(query, null);
    	
    	while (cursor2.moveToNext()) {
			log = cursor2.getString(cursor2.getColumnIndex("log"));
			pass = cursor2.getString(cursor2.getColumnIndex("pass"));
			}
    	db.close();
		helper.close();
    	Log.i("MY_TAG", "m1."+log+"_"+pass);
		if(!log.equals("")){
			CheckPass check = new CheckPass();
			_userId= check.Check(log, pass);
	    	
	    	Log.i("MY_TAG", "m2."+_userId);
			if(!_userId.equals("")){
				Intent intent = new Intent(this, com.example.main.MainActivity.class);
				intent.putExtra("ID", _userId);
				
				
			/*	sPref = getPreferences(MODE_PRIVATE);
			    Editor ed = sPref.edit();
			    ed.putString(SAVED_TEXT, UserId);
			    ed.commit();*/
			    
				UserId.ID=_userId;
				
			  // Log.i("My", "MainActivity() id  getString="+ UserId.ID);
			   
			   
				this.finish();
				startActivity(intent);
			}
		}
		
			setContentView(R.layout.first);
			register=(Button)findViewById(R.id.btn1);
			enter = (Button)findViewById(R.id.btn2);
			
			register.setOnClickListener(this);
			enter.setOnClickListener(this);
			Log.i("MY_TAG", "zxc");
			
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  switch (v.getId()) {
		    case R.id.btn1:
		        Intent intent = new Intent(this, RegisterActivity.class);
		        startActivity(intent);
		      break;
		    case R.id.btn2:
		    	  Intent intent1 = new Intent(this, EnterActivity.class);
		    	  //this.finish(); //- �� �������( 
			        startActivity(intent1);
			        this.finish();
			        return;			      
		    default:
		      break;
		    }
		
	}
	
	
	
		
		

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}








}
