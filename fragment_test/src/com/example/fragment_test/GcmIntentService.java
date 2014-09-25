/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.fragment_test;

import android.R;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */   
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    
  
    
    
    //NotificationManager — системный сервис Android, который управляет всеми уведомлениями. Экземпляр NotificationManager создается при помощи вызова метода getSystemService(), 
    //а затем, когда надо показать уведомление пользователю, вызывается метод notify()
    private NotificationManager mNotificationManager;
    
    // Allows easier control over all the flags, as well as help constructing the typical notification layouts.
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
        
        Log.i(TAG, "GcmIntentService constructor... ");
        


    }
    public static final String TAG = "GCM Demo";
    

    

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        
        //The class you use to write a GCM-enabled client application that runs on an Android device
        
        //When a GCM connection server 
        //delivers the message to your client app, the BroadcastReceiver receives the message as an intent. 
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        
        

        
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
 	   Log.i(TAG,"messageType "+ messageType);
 	   Log.i(TAG,"GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE "+ GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE);
        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
        	   Log.i(TAG, "onHandleIntent");
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
         if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
            	   Log.i(TAG, "1");
                sendNotification("Send error: " + extras.toString());
            } 
            
           else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
                Log.i(TAG, "2");
            // If it's a regular GCM message, do some work.
            } 
         
               else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            
           	   
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
           //     sendNotification("Received: " + extras.toString());
                
                String message = intent.getStringExtra("message");
                
            Log.i(TAG, "Received: " + message);
            
            sendNotification("Received: " + message);
            

            
           }
         // Release the wake lock provided by the WakefulBroadcastReceiver.
         GcmBroadcastReceiver.completeWakefulIntent(intent);
     //    Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
         // Post notification of received message.
      //   sendNotification("Received: " + extras.toString());
    
       //  Log.i(TAG, "Received: " + extras.toString());
        }
        
      
      
        

    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        //.setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("GCM Notification")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg).setAutoCancel(true) //уведомление закроется по клику на него
        .setTicker(msg) //текст, который отобразится вверху статус-бара при создании уведомления
        .setContentText(msg) // Основной текст уведомления
        .setWhen(System.currentTimeMillis()) //отображаемое время уведомления
        .setContentTitle("AppName") //заголовок уведомления
        .setDefaults(Notification.DEFAULT_SOUND); // звук, вибро и диодный индикатор выставляются по умолчанию;

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
    

}
