package com.example.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragment_test.R;

public class fragment2 extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";//№
   
	/**
	 * Возвращает новый экземпляр фрагмента для получения текущего числа
	 */
	public static fragment2 newInstance(int sectionNumber) {
		fragment2 fragment = new fragment2();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber+100);
		fragment.setArguments(args);
		return fragment;
	}

	public fragment2() {
		
	}
   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main2, container,
				false);
		TextView textView = (TextView) rootView
				.findViewById(R.id.section_label); // rootView - позволяет искать id не во всей активности, а только в пределах фрагмета 
		textView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}

