package com.example.zhaoming;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.example.zhaoming.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity_juyu extends Fragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View root = inflater.inflate(R.layout.activity_activity_juyu, container, false);
	WebView wvone = (WebView) root.findViewById(R.id.webView1);
	wvone.loadUrl("http://www.yeelink.net/login");
	return root;
     }
}
