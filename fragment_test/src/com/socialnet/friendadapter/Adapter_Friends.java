package com.socialnet.friendadapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragment_test.R;

public class Adapter_Friends extends BaseAdapter  {
	
	 //Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<Friend_item> objects;
	  
	  public Adapter_Friends(LayoutInflater inflater, ArrayList<Friend_item> products) {
		    //ctx = inflater;
		    objects = products;
		    lInflater = inflater;//(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }
	  
	  
	// ���-�� ���������
	@Override
	public int getCount() {
	
		  return objects.size();
	}
	  // ������� �� �������
	@Override
	public Object getItem(int position) {
		
	    return objects.get(position);
	}
	
	  // id �� �������
	@Override
	public long getItemId(int position) {
		
		   return position;
	}

	  // ����� ������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 // ���������� ���������, �� �� ������������ view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.friend_adapter_item, parent, false);
	    }

	    Friend_item p = getFriend(position);
	    
	    // ��������� View � ������ ������ ������� �� �������: ������������, ����
	    // � ��������
	    
	    ((TextView) view.findViewById(R.id.FIO)).setText(p.FIO);
	    ((TextView) view.findViewById(R.id.frId)).setText(p.id);
	    
	    
	    
	 //   ((TextView) view.findViewById(R.id.frId)).setVisibility(View.INVISIBLE);
	    
	    ((ImageView) view.findViewById(R.id.Avatar)).setImageBitmap(p.avatar_picture);
	
	    ((ImageView) view.findViewById(R.id.OnlineOrNot)).setImageResource(p.Online_or_not_picture);
	    
	    return view;
	    
	}
	
	
	
	
	
	 // ����� �� �������
	public Friend_item getFriend(int position) {
	    return ((Friend_item) getItem(position));
	  }

}
