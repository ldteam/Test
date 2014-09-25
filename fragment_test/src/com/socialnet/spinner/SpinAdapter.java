package com.socialnet.spinner;



import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinAdapter extends ArrayAdapter<SpinnerItem> {
	
	// Your sent context
    private Context context;
    // список user
    private SpinnerItem[] values;

    
	
	  public SpinAdapter(Context context, int textViewResourceId, SpinnerItem[] values) {
	        super(context, textViewResourceId, values);
	        this.context = context;
	        this.values = values;
	    }
	  
	  public int getCount(){
	       return values.length;
	    }

	    //забираем какой item нажал user
	    public SpinnerItem getItem(int position){
	       return values[position];
	    }
	    
	    //забираем tem_id
	    public long getItemId(int position){
	       return position;
	    }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			  TextView label = new TextView(context);
		        label.setTextColor(Color.RED);
		        // Then you can get the current item using the values array (Users array) and the current position
		        // You can NOW reference each method you has created in your bean object (User class)
		        label.setText(values[position].getName());

		        // And finally return your dynamic (or custom) view for each spinner item
		        return label;
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			  TextView label = new TextView(context);
		        label.setTextColor(Color.BLACK);
		        label.setText(values[position].getName());

		        return label;
		}
	    
}
