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
        //WebView����web��Դ
       webView.loadUrl("htp://192.168.1.143");
        //����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
             view.loadUrl(url);
            return true;
        }
       });
    }
    
}