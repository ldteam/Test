package com.example.main;

import java.util.concurrent.ExecutionException;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragment_test.R;
import com.example.registration.Connection;
import com.socialnet.database.DatabaseRequests;
import com.socialnet.picture.DownloadPicture;
import com.socialnet.url.Url_path_to_server;

public class HomePage extends Fragment {
	
	private static final String ID = "";

	public static Fragment newInstance(String id) {
		HomePage fragment1 = new HomePage();
		Bundle args = new Bundle();
		args.putString(ID, id);
		fragment1.setArguments(args);
		return fragment1;
	}
	
	public HomePage() {
		//	Log.i("qwe", "qwe1");
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.homepag, container,
				false);
		Log.i("MY_TAG", "qwe4_"+getArguments().getString(ID));
		TextView t1=(TextView)rootView.findViewById(R.id.t1);
		//TextView t2=(TextView)rootView.findViewById(R.id.t2);
		ImageView v1 = (ImageView)rootView.findViewById(R.id.im1);
		
		//������
 		Connection con = new Connection(Url_path_to_server.UriSelectFromDatabaseWithId,DatabaseRequests.SelectOneUserAnketa, getArguments().getString(ID));
 		try {
			con.connect();
		
 		//Log.v("MY_TAG", "homepage_con");
 		ContentValues[] tweetsValues =con.returnvalues();
		 //Log.i("MY_TAG", "check2");
		 try{
			 Log.i("MY_TAG", tweetsValues[0].getAsString("name")+" "+tweetsValues[0].getAsString("surname"));
			 t1.setText(tweetsValues[0].getAsString("name")+" "+tweetsValues[0].getAsString("surname"));
			 //t2.setText(tweetsValues[0].getAsString("d_rating"));
		 }catch(Exception e){			 
			 Log.i("MY_TAG", "homepage_Error_");
		 }
 		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		 DownloadPicture pic = new DownloadPicture();
		 v1.setImageBitmap(pic.con(ID));
		 return rootView;
 	}
     
}
