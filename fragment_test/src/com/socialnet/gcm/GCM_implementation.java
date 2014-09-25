package com.socialnet.gcm;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


// 



public class GCM_implementation {
	
	public static final String EXTRA_MESSAGE = "message";
	   public static final String PROPERTY_REG_ID = "registration_id";
	    private static final String PROPERTY_APP_VERSION = "appVersion";
	    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	    
	   
	    
	    /**
	     * Substitute you own sender ID here. This is the PROJECT NUMBER you got
	     * from the API Console, as described in "Getting Started."
	     */
	   String SENDER_ID = "977481787083";
	    
	    
	    static final String TAG = "GCM Demo";
	    
	    String SendMessage = "";
	    
	    
	  //позволяют одновременное обращение к переменным,  используются как  счетчики в многопоточных приложениях
	    AtomicInteger msgId = new AtomicInteger();
	    Context context;
	 
	    String regid;
	    
	    GoogleCloudMessaging gcm;
		
	    
	   
	    
	    public GCM_implementation(Context context)
	    {
	    	this.context=context;
	    }

	    
	    // Check device for Play Services APK. If check succeeds, proceed with GCM registration. 
	    
	    
	    public void CheckDeviceForPlayServiceAPK()
	    {
	    	
	    if (checkPlayServices()==0) {
	    	gcm = GoogleCloudMessaging.getInstance(context);
	    	regid = getRegistrationId();
	    	
	    	if (regid.isEmpty()) {
	        	
	            registerInBackground();
	        } else {
	             Log.i(TAG, "RegId  "+regid);     
	         }
	    }
	    	/*
	    	 * 
        gcm = GoogleCloudMessaging.getInstance(context);
       regid = getRegistrationId(context);

       if (regid.isEmpty()) {
    	
          registerInBackground();
      } else {
           Log.i(TAG, "RegId  "+regid);     
       }
    } else {
        Log.i(TAG, "No valid Google Play Services APK found.");
    }  */

	    }
	    
	    /**
	     * Check the device to make sure it has the Google Play Services APK. If
	     * it doesn't, display a dialog that allows users to download the APK from
	     * the Google Play Store or enable it in the device's system settings.
	     */
	    public int checkPlayServices() {
	    	//доступен ли Google PlayService
	    
	    	
	    	
	     int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
	    	
	        //если да
	        if (resultCode != ConnectionResult.SUCCESS) {
	            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	              //  GooglePlayServicesUtil.getErrorDialog(resultCode, this,  PLAY_SERVICES_RESOLUTION_REQUEST).show();
	            	
	            	return 1; //при 1 - вызывать в активити GooglePlayServicesUtil.getErrorDialog(resultCode, this,  PLAY_SERVICES_RESOLUTION_REQUEST).show();

	                
	            } else {
	                Log.i(TAG, "This device is not supported.");
	               return 2; // при 2 - вызывать в активити - finish();
	            }
	            
	            
	           
	       }
	        return 0; //if alles correct, return 0
	    }
	    
	    /**
	     * Stores the registration ID and the app versionCode in the application's
	     * {@code SharedPreferences}.
	     *
	     * @param context application's context.
	     * @param regId registration ID
	     */
	    private void storeRegistrationId(String regId) {
	    	
	    	// PrefeSharedPreferencesrences. Значения сохраняются в виде пары: имя, значение
	        final SharedPreferences prefs = getGcmPreferences(context);
	        int appVersion = getAppVersion(context);
	        Log.i(TAG, "Saving regId on app version " + appVersion);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putString(PROPERTY_REG_ID, regId);
	       editor.putInt(PROPERTY_APP_VERSION, appVersion);
	        editor.commit();
	    }
	    
	    
	    /**
	     * Gets the current registration ID for application on GCM service, if there is one.
	     * <p>
	     * If result is empty, the app needs to register.
	     *
	     * @return registration ID, or empty string if there is no existing
	     *         registration ID.
	     */
	    private String getRegistrationId()
	    {
	        final SharedPreferences prefs = getGcmPreferences(context);
	        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	        if (registrationId.isEmpty()) {
	            Log.i(TAG, "Registration not found.");
	            return "";
	        }
	        // Check if app was updated; if so, it must clear the registration ID
	        // since the existing regID is not guaranteed to work with the new
	        // app version.
	        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	        int currentVersion = getAppVersion(context);
	        if (registeredVersion != currentVersion) {
	            Log.i(TAG, "App version changed.");
	            return "";
	        }
	        return registrationId;
	    }
	 
	    private void registerInBackground() {  new AsyncTask<Void, Void, String>() {
	            @Override
	            protected String doInBackground(Void... params) {
	                String msg = "";
	                try {
	                    if (gcm == null) {
	                        gcm = GoogleCloudMessaging.getInstance(context);
	                    }
	                  regid = gcm.register(SENDER_ID);
	                    msg = "Device registered, registration ID=" + regid;
	 
	                    Log.i(TAG, "GCM_implementation.registerInBackground() "+ msg);
	 
	                  
	                    
	                    //переслать регистрационный	 id на сервер
	                  sendRegistrationIdToBackend();
	 
	                    // For this demo: we don't need to send it because the device will send
	                    // upstream messages to a server that echo back the message using the
	                    // 'from' address in the message.
	 
	                    // созраняем id в SharedPreferences (типа maps)
	                   storeRegistrationId(regid);  
	                } catch (IOException ex) {
	                    msg = "Error :" + ex.getMessage();
	                    // If there is an error, don't just keep trying to register.
	                    // Require the user to click a button again, or perform
	                    // exponential back-off.
	                }
	                return msg;
	            }
	 
	            @Override
	            protected void onPostExecute(String msg) {
	         //       mDisplay.append(msg + "\n");
	            }
	        }.execute(null, null, null);
	    }
	    
	    // Send an upstream message.
	    public void SendMessage()
	    {
	            new AsyncTask<Void, Void, String>() {
	                @Override
	                protected String doInBackground(Void... params) {
	                    String msg = "";
	                    try {
	                    	
	                    	
	                    	// объект типа Bundle можно запихать любой примитивный тип,
	                    	//а так же String и другие, созданные вами пользовательские типы наследующие класс Parcel или интерфейс Parcelable.
	                    	//А потом достать их по ключу 
	                    	//в другом потоке которому вы передали объект Bundle. В итоге получается что Bundle это эдакий HashMap 
	                      
	                    	Bundle data = new Bundle();
	                        data.putString("my_message", "Hello World");
	                        data.putString("my_action", "com.example.gsm_client.ECHO_NOW");
	                        
	                        //счетчик id сообщения
	                        String id = Integer.toString(msgId.incrementAndGet());
	                        //отсылаем с нашим sender id, передаем  id сообщения и данное
	                        gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
	                        msg = "Sent message";
	                    } catch (IOException ex) {
	                        msg = "Error :" + ex.getMessage();
	                    }
	                    return msg;
	                }
	                
	                //when messages finish to send	 
	                @Override
	                protected void onPostExecute(String msg) {
	                	Log.i(TAG, "GCM_implementation.onPostExecute "+msg);
	                
	                }
	            }.execute(null, null, null);
	        } 
	    
	      
	 
	    /**
	     * @return Application's version code from the {@code PackageManager}.
	     */
	    private static int getAppVersion(Context context) {
	        try {
	            PackageInfo packageInfo = context.getPackageManager()
	                    .getPackageInfo(context.getPackageName(), 0);
	            return packageInfo.versionCode;
	        } catch (NameNotFoundException e) {
	            // should never happen
	            throw new RuntimeException("Could not get package name: " + e);
	        }
	    }
	    
	    /**
	     * @return Application's {@code SharedPreferences}.
	     */
	    private SharedPreferences getGcmPreferences(Context context) {
	        // This sample app persists the registration ID in shared preferences, but
	        // how you store the regID in your app is up to you.
	        return context.getSharedPreferences(GCM_implementation.class.getSimpleName(),
	                Context.MODE_PRIVATE);
	    }
	    /**
	     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
	     * messages to your app. Not needed for this demo since the device sends upstream messages
	     * to a server that echoes back the message using the 'from' address in the message.
	     */
	    private void sendRegistrationIdToBackend() {
	      // Your implementation here.
	    }
	    
	    
	    
	    public String Return_msg()
	    {
	    	return SendMessage;
	    }
	    
	    
	    public void SendBackground() {  new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				 String msg = "bk";
                 try {
                 	
                 	
                 	// объект типа Bundle можно запихать любой примитивный тип,
                 	//а так же String и другие, созданные вами пользовательские типы наследующие класс Parcel или интерфейс Parcelable.
                 	//А потом достать их по ключу 
                 	//в другом потоке которому вы передали объект Bundle. В итоге получается что Bundle это эдакий HashMap 
                   
                 	Bundle data = new Bundle();
                     data.putString("my_message", "Hello World");
                     data.putString("my_action", "com.example.gsm_client.ECHO_NOW");
                     
                   //счетчик id сообщения
                     String id = Integer.toString(msgId.incrementAndGet());
                     
                     
                     //отсылаем с нашим sender id, передаем  id сообщения и данное
                     gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
                     msg = "Sent message";
                 } catch (IOException ex) {
                     msg = "Error :" + ex.getMessage();
                 } 
                 return msg;
                 
                 
                 
                 
                 
			}
	    	
			@Override
            protected void onPostExecute(String msg) {
				
			SendMessage = msg;
              //  mDisplay.append(msg + "\n");
            }
        }.execute(null, null, null);
    } 
	    
	    
	    

}

  
