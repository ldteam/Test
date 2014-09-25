package com.socialnet.message_adapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.fragment_test.R;
import com.example.registration.Connection;
import com.socialnet.database.DatabaseRequests;
import com.socialnet.url.Url_path_to_server;



public class MessageList extends Fragment {
	
private static final String  ID= "";
	
	public static MessageList newInstance(String  id) {
		MessageList fragment = new MessageList();
		Bundle args = new Bundle();
		args.putString(ID, id);
		fragment.setArguments(args);
		return fragment;
	}
	
	  
	public MessageList() {
	//	Log.i("qwe", "qwe1");
	}
	
	 ArrayList<Message_item> messages = new ArrayList<Message_item>();
	  Adapter_messages messageAdapter;
	
	
	
	  @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
		
		  
		View rootView = inflater.inflate(R.layout.fragment_main_messages, container, false);
		
		 messageAdapter =  new Adapter_messages(inflater, messages);
			
		 // создаем адаптер
	    fillData();
		
		    // настраиваем список
	  ListView lvMain = (ListView) rootView.findViewById(R.id.MessageArraylistView1);
		    lvMain.setAdapter(messageAdapter);
		    
		    lvMain.setOnItemClickListener(new OnItemClickListener() {

		        @SuppressLint({ "ShowToast", "Recycle" })
				@Override
		        public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
		            view.setSelected(true);

  
		           Log.v("My", "DialogList.setOnItemClickListener() "+ messageAdapter.getMessage(position).get_msgID());
		        }
		    });	    
	    return rootView;	 
	  }
	  
	// генерируем данные для адаптера
		  void fillData() {
			  
			  try {
			      
				Connection con = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId,  DatabaseRequests.Messages_one_users,  "1");
						//getArguments().getString(ID));
				con.connect();
			    ContentValues[] val = con.returnvalues();
	  
				for (int i=0; i<val.length; i++)
				{				
					 Log.v("My", "Messageist.fillData() "+ val[i].getAsString("reply"));	
					messages.add(new Message_item(val[i].getAsString("ms_id"),  val[i].getAsString("Reply"), val[i].getAsString("time")));	
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
}
