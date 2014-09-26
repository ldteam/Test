package com.example.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragment_test.R;

public class fragment1 extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";//№


	//khkjhjkhkjhk
	
	//jhkjhjkhjkjklj
	
	
	public static fragment1 newInstance(int sectionNumber) {
		fragment1 fragment = new fragment1();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber+100);
		fragment.setArguments(args);
		return fragment;
	}

	public fragment1() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		TextView textView = (TextView) rootView
				.findViewById(R.id.section_label); // rootView - позволяет искать id не во всей активности, а только в пределах фрагмета 
		textView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));
		
		Log.i("MY_TAG", "loaderCallbacks");

		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}

