package com.liucg.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleFragment extends ListFragment {
	
	private MainActivity mainActivity = null;
	int mCurCheckPosition = 0;

	public TitleFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Text.titles));
		if(savedInstanceState != null){
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}
		ListView listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setSelection(mCurCheckPosition);
		mainActivity.showDetails(mCurCheckPosition);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mainActivity = (MainActivity) activity;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		mainActivity.showDetails(position);
		mCurCheckPosition = position;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mainActivity = null;
	}
	
}
