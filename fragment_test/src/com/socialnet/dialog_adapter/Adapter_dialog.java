package com.socialnet.dialog_adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragment_test.R;

public class Adapter_dialog   extends BaseAdapter{
	
	LayoutInflater lInflater;
	  ArrayList<Dialog_item> objects;
	  
	  
	  public Adapter_dialog(LayoutInflater inflater, ArrayList<Dialog_item> dialogs) {
		    //ctx = inflater;
		    objects = dialogs;
		    lInflater = inflater;//(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }
	  
	  
	  //// кол-во элементов

	@Override
	public int getCount() {
		 return objects.size();
	}

	
	// // элемент по позиции
	@Override
	public Object getItem(int position) {
	
		return objects.get(position);
	}

	
	//// id по позиции
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
	      view = lInflater.inflate(R.layout.dialog_adapter_item, parent, false);
	      
	      
	      
	      
	    }  
	    
	   Dialog_item p = getDialog(position);
	   
	   // заполняем View в пункте списка данными из товаров: наименование, цена
	    // и картинка
	    
	    ((TextView) view.findViewById(R.id.FIO)).setText(p.getFIO());
	    ((TextView) view.findViewById(R.id.frId)).setText(p.getFriend_id());
	    
	    ((TextView) view.findViewById(R.id.Date)).setText(p.getDate());
	    
	    ((TextView) view.findViewById(R.id.Message)).setText(p.getLastMessage());
	    
	 ((TextView) view.findViewById(R.id.frId)).setVisibility(View.INVISIBLE);
	    
	    ((ImageView) view.findViewById(R.id.Avatar)).setImageBitmap(p.getAvatar_picture());

	    
	    return view;
	    
	}
	
	 // диалог по позиции
	public Dialog_item getDialog(int position) {
	    return ((Dialog_item) getItem(position));
	  }


}
