package com.socialnet.friendadapter;

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
import com.socialnet.picture.DownloadPicture;
import com.socialnet.url.Url_path_to_server;


public class FriendList  extends Fragment {
	
	private static final String ID = "";
	
	public static FriendList newInstance(String id) {
		FriendList fragment = new FriendList();
		Bundle args = new Bundle();
		args.putString(ID, id);
		fragment.setArguments(args);
		return fragment;
	}

	public FriendList() {
	//	Log.i("qwe", "qwe1");
	}
	
	
	 ArrayList<Friend_item> friends = new ArrayList<Friend_item>();
	  Adapter_Friends FriendAdapter;
	  

	  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		  View rootView = inflater.inflate(R.layout.fragment_main_friends, container,
					false);

		FriendAdapter = new Adapter_Friends(inflater, friends);
		//select user_id, name, surname, adress_image from anketa where user_id in ( select friend_id from friends where user_id=53);


		 // создаем адаптер
	    fillData();
		
		    // настраиваем список
	    ListView lvMain = (ListView) rootView.findViewById(R.id.FriendsArraylistView1);
		    lvMain.setAdapter(FriendAdapter);
		    
		    lvMain.setOnItemClickListener(new OnItemClickListener() {

		        @SuppressLint("ShowToast")
				@Override
		        public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
		            view.setSelected(true);
		            
		    
		           Log.v("My",  FriendAdapter.getFriend(position).id+" ");
		        }
		    });
		    return rootView;
	}
	
	
	
	// генерируем данные для адаптера
	  void fillData() {
		  
		
		  try {
		    
			Connection con = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId, 
					DatabaseRequests.SelectAnketaInFriendslist, "91");
					//getArguments().getString(ID));
			con.connect();
			ContentValues[] val = con.returnvalues();
			
			 DownloadPicture pic = new DownloadPicture();
			// v1.setImageBitmap(pic.con("1"));
			 
		  
			for (int i=0; i<val.length; i++)
			{
				   //adress_folder, adress_image
				  Log.v("MY_TAG","FrenAdapt_"+val[i].getAsString("user_id")+"_"+val[i].getAsString("adress_image")+" "+val[i].getAsString("adress_folder"));
				  
			  	friends.add(new Friend_item(val[i].getAsString("user_id"),  pic.con(val[i].getAsString("user_id")), val[i].getAsString("name").toUpperCase()+" "+val[i].getAsString("surname").toUpperCase(),  R.drawable.no));
		    }
			   
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	  }
	  




}
