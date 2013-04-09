package com.liucg.uitest;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
	
	private int mindex = 0;
	
	public static DetailsFragment newInstance(int index){
		
		DetailsFragment detailsFragment = new DetailsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		detailsFragment.setArguments(bundle);
		return detailsFragment;	

	}
	
	public int getShowIndex(){
		return mindex;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mindex = getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.details, container,false);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(Text.textStrings[mindex]);
		return view;
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onInflate(activity, attrs, savedInstanceState);
	}

}
