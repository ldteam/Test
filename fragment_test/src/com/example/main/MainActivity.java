package com.example.main;

import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android_db.LogIn;
import com.example.fragment_test.GCM_implementation;
import com.example.fragment_test.R;
import com.socialnet.dialog_adapter.DialogList;
import com.socialnet.friendadapter.FriendList;
import com.socialnet.message_adapter.MessageList;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	
	//��� google cloud messaging
	    GCM_implementation GCM;
    	public static final String EXTRA_MESSAGE = "message";
	    public static final String PROPERTY_REG_ID = "registration_id";
	    static final String TAG = "GCM Demo";
	    
	    AtomicInteger msgId = new AtomicInteger();
	    Context context;	     
	    private String regid;
	    

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	String ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Log.i("qwe","qwe1_"+ID);
		{//����� ������� � ������� ������ NavigationDrawerFragment
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		}
		
		
	    	//������ GCM 
	  //    GCM = new GCM_implementation(getApplicationContext());
	    //context = getApplicationContext();

	        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
	       
	      //    GCM.CheckDeviceForPlayServiceAPK(); 
		
		   
	}
	
	class DownloadImageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			
			
			//������ GCM 
		      GCM = new GCM_implementation(getApplicationContext());
		      context = getApplicationContext();

		        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
		       
		       GCM.CheckDeviceForPlayServiceAPK(); 
			
			return null;
		}
	}

	
	 
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// �������� ��������
		Intent intent = getIntent();
		ID = intent.getStringExtra("ID");
		//Log.i("qwe","qwe2_"+ID);
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position) {
		case 0:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					HomePage.newInstance(ID)).commit();
			break;
		case 1:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					FriendList.newInstance(ID)).commit(); 
			break;
		case 2:
			
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					DialogList.newInstance(ID)).commit(); 
		
		//	Log.i("MY_TAG", "MainActivity.onNavigationDrawerItemSelected: case 2");
			
			break;
		case 3:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					MessageList.newInstance(ID)).commit(); 
		
				
			break;
		case 4:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					fragment2.newInstance(position + 1)).commit();
			break;
			
			
			
		default:
			break;
		}
	}
	
	  //���������� ����� ��� ��� ����� �������� ��� ���������� ������������ (��������������)
    @Override
    protected void onResume() {
        super.onResume();
         
        new DownloadImageTask().execute("");

    }  
    
    @Override      
    protected void onDestroy() {
        super.onDestroy();
    }  
    
    
    
	
	   
	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() { // �� ����� �����
		Log.i("qwe", "hz");
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { // ���� �� ����� ����� �����
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			Log.i("qwe", "close?");
			// ���������� ����� �������� ����-��������
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// ���������� ��� �������� ����-��������
		Log.i("qwe", "menu");
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id==R.id.exit)
		{
			Log.i("MY_TAG", "exit");
			LogIn helper = new LogIn(this);
			SQLiteDatabase db = helper.getWritableDatabase();
			
			String deleteQuery = "delete from log;";
      		db.execSQL(deleteQuery);
      		
      		db.close();
    		helper.close();
    		
    		Intent intent = new Intent(this, com.example.registration.MainActivity.class);
			
			this.finish();
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

}
