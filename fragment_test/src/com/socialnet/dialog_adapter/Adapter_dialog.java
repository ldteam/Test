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
	  
	  
	  //// ���-�� ���������

	@Override
	public int getCount() {
		 return objects.size();
	}

	
	// // ������� �� �������
	@Override
	public Object getItem(int position) {
	
		return objects.get(position);
	}

	
	//// id �� �������
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
	      view = lInflater.inflate(R.layout.dialog_adapter_item, parent, false);
	      
	      
	      
	      
	    }  
	    
	   Dialog_item p = getDialog(position);
	   
	   // ��������� View � ������ ������ ������� �� �������: ������������, ����
	    // � ��������
	    
	    ((TextView) view.findViewById(R.id.FIO)).setText(p.getFIO());
	    ((TextView) view.findViewById(R.id.frId)).setText(p.getFriend_id());
	    
	    ((TextView) view.findViewById(R.id.Date)).setText(p.getDate());
	    
	    ((TextView) view.findViewById(R.id.Message)).setText(p.getLastMessage());
	    
	 ((TextView) view.findViewById(R.id.frId)).setVisibility(View.INVISIBLE);
	    
	    ((ImageView) view.findViewById(R.id.Avatar)).setImageBitmap(p.getAvatar_picture());

	    
	    return view;
	    
	}
	
	 // ������ �� �������
	public Dialog_item getDialog(int position) {
	    return ((Dialog_item) getItem(position));
	  }


}
