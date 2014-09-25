package com.example.android_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LogIn extends SQLiteOpenHelper
{

	final static String databaseName = "social.db3";
	final static int databaseVersion = 1;
	
	public LogIn(Context context) {
		super(context, databaseName, null, databaseVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("MY_TAG", "m0.");
		db.execSQL("CREATE TABLE log(id INTEGER PRIMARY KEY,log VARCHAR(40), pass VARCHAR(40))");
		Log.i("MY_TAG", "m0(2).");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
