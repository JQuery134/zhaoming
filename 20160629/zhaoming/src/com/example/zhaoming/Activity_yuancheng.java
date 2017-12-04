package com.example.zhaoming;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity_yuancheng extends Activity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_yuancheng);             
        init();

    }
    

    private void init(){
        webView = (WebView) findViewById(R.id.webView2);
        //WebView加载web资源
       webView.loadUrl("htp://192.168.1.143");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
             view.loadUrl(url);
            return true;
        }
       });
    }
    
}