package com.socialnet.gcm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GcmBroadcastReceiver  extends WakefulBroadcastReceiver {


	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("GCM Demo", "GcmBroadcastReceiver.onReceive(");
		

	
		
	
	// Waking up mobile if it is sleeping
		WakeLocker.acquire(context.getApplicationContext());
		 
		// new message code here
		 
		// Releasing wake lock
		WakeLocker.release();  
		
		  // Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),  GcmIntentService.class.getName());
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
        
        
		
	}

}
