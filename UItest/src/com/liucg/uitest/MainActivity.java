package com.liucg.uitest;

import android.R.color;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private Button button;
	private FrameLayout frameLayout;
	private SlideMenuControl slideMenuControl;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		slideMenuControl = (SlideMenuControl) findViewById(R.id.slideMenuControl1);
		frameLayout = (FrameLayout) findViewById(R.id.framelayout_details);
		
		
	}
	
	void showDetails(int index){
		DetailsFragment dFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_details);
		if(dFragment == null||dFragment.getShowIndex()!=index){
			dFragment = DetailsFragment.newInstance(index);
		}
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.replace(R.id.framelayout_details, dFragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
	
		super.onResume();
	}

}
