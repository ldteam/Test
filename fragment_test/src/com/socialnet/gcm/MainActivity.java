package com.socialnet.gcm;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.fragment_test.GCM_implementation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;





public class MainActivity extends  Activity implements OnClickListener {

	
          GCM_implementation GCM;
	    

    	public static final String EXTRA_MESSAGE = "message";
	    public static final String PROPERTY_REG_ID = "registration_id";
	
	   
	    
	    /**
	     * Substitute you own sender ID here. This is the PROJECT NUMBER you got
	     * from the API Console, as described in "Getting Started."
	     */
	 //  String SENDER_ID = "977481787083";  
	    
	   

	    static final String TAG = "GCM Demo";
	    
	    TextView mDisplay;
	    Button btn1;
	   // GoogleCloudMessaging gcm;
	    
	    //позволяют одновременное обращение к переменным,  используются как  счетчики в многопоточных приложениях
	    AtomicInteger msgId = new AtomicInteger();
	    Context context;
	    
	     
	    String regid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    //    gcm = GoogleCloudMessaging.getInstance(this);
        
  //      GCM = new GCM_implementation(getApplicationContext());
       
       // setContentView(R.layout.activity_main);
     // mDisplay = (TextView) findViewById(R.id.display);
 
    //  btn1=(Button) findViewById(R.id.send);
      
      Log.i(TAG, "create()");
      btn1.setOnClickListener(this);
      
      
       context = getApplicationContext();

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
       
     //  GCM.CheckDeviceForPlayServiceAPK();
 
    
     
    }
    
   
    
 // Called implicitly when device is about to sleep or application is backgrounded
    protected void onPause(){
        super.onPause();
        
       
     
    }
    
    //вызывается перед тем как будет доступно для активности пользователя (взаимодействие)
    @Override
    protected void onResume() {
        super.onResume();

        
     
        
        
        // Check device for Play Services APK.
  //    GCM.checkPlayServices();
    }
    
    
    
    
 
    
    
   
 
    
   
 
    
    // Send an upstream message.
    public void onClick(final View view) {
 
     /*   if (view == findViewById(R.id.send)) {
        	
        	GCM.SendBackground();
        	
        	mDisplay.append(GCM.Return_msg() + "\n");
                } else if (view == findViewById(R.id.clear)) {
            mDisplay.setText("");
        }*/
    }
      
    @Override      
    protected void onDestroy() {
        super.onDestroy();
    }
    
  
    
 
    
    
   
}
