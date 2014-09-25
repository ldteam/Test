package com.socialnet.dialog_adapter;



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

public class DialogList  extends Fragment{
	

	
	private static final String  ID= "";
	
	public static DialogList newInstance(String  id) {
		DialogList fragment = new DialogList();
		Bundle args = new Bundle();
		args.putString(ID, id);
		fragment.setArguments(args);
		return fragment;
	}
	
	public DialogList() {
	//	Log.i("qwe", "qwe1");
	}
	
	 ArrayList<Dialog_item> dialogs = new ArrayList<Dialog_item>();
	  Adapter_dialog dialogAdapter;

	      
	  @Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState) {
				  View rootView = inflater.inflate(R.layout.fragment_main_dialogs, container, false);
				  
			
				  
				  dialogAdapter =  new Adapter_dialog(inflater, dialogs);
					
					 // создаем адаптер
				    fillData();
					
					    // настраиваем список
				  ListView lvMain = (ListView) rootView.findViewById(R.id.MessageArraylistView1);
					    lvMain.setAdapter(dialogAdapter);
					    
					    lvMain.setOnItemClickListener(new OnItemClickListener() {

					        @SuppressLint({ "ShowToast", "Recycle" })
							@Override
					        public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
					            view.setSelected(true);


					       
					
							
					            
					            
					           Log.v("My", "DialogList.setOnItemClickListener() "+ dialogAdapter.getDialog(position).getFriend_id());
					        }
					    });
		
				    
				    return rootView;

		  }

	  
		// генерируем данные для адаптера
	  void fillData() {
		  
		
		  try {
		      
			Connection con = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId,  DatabaseRequests.Dialogs_one_user,  "91");
					//getArguments().getString(ID));
			con.connect();
		    ContentValues[] val = con.returnvalues();
		    
		
			
			 DownloadPicture pic = new DownloadPicture();
			// v1.setImageBitmap(pic.con("1"));
		   
		  
			for (int i=0; i<val.length; i++)
			{

				//last message in dialog
				Connection con2 = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId, DatabaseRequests.LastMessage, val[i].getAsString("d_id"));
						//getArguments().getString(ID));
				con2.connect();
				
				ContentValues[] val2=con2.returnvalues();
				
			//	 Log.v("My", "DialogList.fillData() "+ val[i].getAsString("user_id"));
					
				
				
				if (val2.length!=0)
				{
					
					  dialogs.add(new Dialog_item(val[i].getAsString("user_id"),  pic.con(val[i].getAsString("adress_image")), val[i].getAsString("name")+" "+val[i].getAsString("surname"),  val2[0].getAsString("reply"), val[i].getAsString("time")));

					  
				}
				else
				{
					  dialogs.add(new Dialog_item(val[i].getAsString("user_id"),  pic.con(val[i].getAsString("adress_image")), val[i].getAsString("name")+" "+val[i].getAsString("surname"),  "сообщений нет", val[i].getAsString("time")));

				}
				
				
				
				  
		
			
				
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	  }
	  
	  
	  
}
