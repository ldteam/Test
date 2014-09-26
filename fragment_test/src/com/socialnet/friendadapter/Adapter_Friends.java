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
	  
	  
	// кол-во элементов
	@Override
	public int getCount() {
	
		  return objects.size();
	}
	  // элемент по позиции
	@Override
	public Object getItem(int position) {
		
	    return objects.get(position);
	}
	
	  // id по позиции
	@Override
	public long getItemId(int position) {
		
		   return position;
	}

	  // пункт списка
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.friend_adapter_item, parent, false);
	    }

	    Friend_item p = getFriend(position);
	    
	    // заполняем View в пункте списка данными из товаров: наименование, цена
	    // и картинка
	    
	    ((TextView) view.findViewById(R.id.FIO)).setText(p.FIO);
	    ((TextView) view.findViewById(R.id.frId)).setText(p.id);
	    
	    
	    
	 //   ((TextView) view.findViewById(R.id.frId)).setVisibility(View.INVISIBLE);
	    
	    ((ImageView) view.findViewById(R.id.Avatar)).setImageBitmap(p.avatar_picture);
	
	    ((ImageView) view.findViewById(R.id.OnlineOrNot)).setImageResource(p.Online_or_not_picture);
	    
	    return view;
	    
	}
	
	
	
	
	
	 // товар по позиции
	public Friend_item getFriend(int position) {
	    return ((Friend_item) getItem(position));
	  }

}
