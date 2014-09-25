package com.example.registration;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_db.LogIn;
import com.example.fragment_test.R;

public class EnterActivity extends Activity 
implements OnClickListener,SQLiteTransactionListener {
	
	Button forget_password, enter;
	EditText log, pass;
	TextView Error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.enter_form);
		 
		 enter=(Button)findViewById(R.id.btn3);
		 forget_password=(Button)findViewById(R.id.btn4);
		 log=(EditText)findViewById(R.id.surname);
		 pass=(EditText)findViewById(R.id.editText2);
		 Error=(TextView)findViewById(R.id.error);
		 
		 enter.setOnClickListener(this);
		 forget_password.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		  switch (v.getId()) {
		    case R.id.btn3:
		    	Log.i("MY_TAG", "Enter"); 		        
		   	Error.setVisibility(0); // хз... а то что ниже нужно перенести в поток, но уже три утра, так чо в другой раз
		    	CheckPass check = new CheckPass();
	 	String str = check.Check(log.getText().toString(), pass.getText().toString());
	 	
		Log.v("My"," ID: "+str);
		   		if(str.equals("")) 
				{
					Error.setVisibility(100);
					Log.i("MY_TAG", "EnterActivity.onClick, enter_error");
				}
				else{
					Log.i("MY_TAG","enter_good, ID_Client="+str);
					LogIn helper = new LogIn(this);
					SQLiteDatabase db = helper.getWritableDatabase();
					
					try
		      		{
		      			db.beginTransactionWithListener(this);
		      			ContentValues cv = new ContentValues();
				    	cv.put("log", log.getText().toString());
				    	cv.put("pass", pass.getText().toString());
				    	db.insert("log", null, cv);
				    	
			      		db.setTransactionSuccessful();
			      		 String log = "", pass = "";
			     	    String query = "SELECT * from log";
			         	Cursor cursor2 = db.rawQuery(query, null);
			         	while (cursor2.moveToNext()) {
			     			log = cursor2.getString(cursor2.getColumnIndex("log"));
			     			pass = cursor2.getString(1);
			     			}
			         	Log.i("MY_TAG", "m1."+log+"_"+pass);
		      		}
		      		catch(Exception ex)
		      		{
		      			Log.i("MY_TAG", "m1.Exaption?");
		      		}
					finally
		      		{
		      			db.endTransaction();
		      		}
					
					db.close();
					helper.close();
					
					Intent intent = new Intent(this, com.example.main.MainActivity.class);
					intent.putExtra("ID", str);
					//finishActivity();
					
					this.finish();
					startActivity(intent);
					return;
					
					
				}
		      break;
		    case R.id.btn4:
		    	  Intent intent2 = new Intent(this, ForgetPassword.class);
			        startActivity(intent2);
			      break;
			      
		    default:
		      break;
		    }
		
	}

	@Override
	public void onBegin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCommit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRollback() {
		// TODO Auto-generated method stub
		
	}
	

}
