package com.example.zhaoming;

import android.support.v7.app.ActionBarActivity;

import java.util.Timer;
import java.util.TimerTask;

import com.example.zhaoming.MainActivity;
import com.example.zhaoming.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class Activityhuanying extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_activityhuanying);
		final Intent it = new Intent(this,MainActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(it);
				finish();
			}
			
		};
	  timer.schedule(task,100*5);
	  
	}
      
}