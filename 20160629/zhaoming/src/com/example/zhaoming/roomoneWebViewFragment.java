package com.example.zhaoming;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class roomoneWebViewFragment extends Fragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View root = inflater.inflate(R.layout.roomone_wevview, container, false);
	WebView wvone = (WebView) root.findViewById(R.id.wv1);
	wvone.loadUrl("http://www.yeelink.net/login");
	return root;
     }
}