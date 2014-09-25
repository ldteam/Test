package com.socialnet.message_adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fragment_test.R;

public class Adapter_messages extends BaseAdapter{
	
	LayoutInflater lInflater;
	  ArrayList<Message_item> objects;
	  
	  
	  public Adapter_messages(LayoutInflater inflater, ArrayList<Message_item> dialogs) {
		    //ctx = inflater;
		    objects = dialogs;
		    lInflater = inflater;//(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }

	  
	  
	@Override
	public int getCount() {
		 return objects.size();
	}

	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	//// пункт списка
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.message_adapter_item, parent, false);

	      
	      
	    }
	    
	    Message_item p = getMessage(position);
		   
		   // заполняем View в пункте списка данными из товаров: наименование, цена
		    // и картинка
		    
		    ((TextView) view.findViewById(R.id.msId)).setText(p.get_msgID());
		    ((TextView) view.findViewById(R.id.tView_reply)).setText(p.getMessage());
		    
		    ((TextView) view.findViewById(R.id.tView_date)).setText(p.getDate());
		    
		    

		
	    return view;
		
		
	}
	
	 // диалог по позиции
		public Message_item getMessage(int position) {
		    return ((Message_item) getItem(position));
		  }

	

}

