package com.example.zhaoming;

import com.example.zhaoming.MainActivity.ButtononeListener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity2 extends Activity {
private Button buttona =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		buttona = (Button)findViewById(R.id.buttona);
		buttona.setOnClickListener(new ButtonAListener());
	}
	class ButtonAListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Intent intent = new Intent();
			    intent.setClass(Activity2.this,Activity3.class);
			    Activity2.this.startActivity(intent);
		}
		
	}
}