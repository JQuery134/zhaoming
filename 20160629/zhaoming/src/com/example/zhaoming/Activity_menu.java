package com.example.zhaoming;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter.LengthFilter;

import com.example.zhaoming.Activity2.ButtonAListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_menu extends Activity {
private Button buttonm1 =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_menu);
		buttonm1 = (Button)findViewById(R.id.buttonm1);
		buttonm1.setOnClickListener(new ButtonMoneListener());
	}
	class ButtonMoneListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Intent intent = new Intent();
			    intent.setClass(Activity_menu.this,MainActivity.class);
			    Activity_menu.this.startActivity(intent);
		}
		
	}
}
