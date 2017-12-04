package com.example.zhaoming;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.yeepost.Utils;
import com.example.linechart.MainActivity;
import com.example.util.HttpUtil;
import com.example.util.LocalCity;
import com.example.util.Weather;
import com.example.util.isInterent;
import com.google.gson.Gson;


public class TcpActivity extends Activity {
	private ImageButton connect2 = null;
	private ImageButton connect1 = null;
	private ImageButton connect5 = null;
	private ImageButton connect6 = null;
	//false代表关，true代表开
	public boolean flag2=false;
    public boolean flag1=false;
    public boolean flag5=false;
    public boolean flag6=false;
    
    private TextView tv_temperature;
    private TextView tv_shiDu;
    private TextView tv_smoke;
    private TextView tv_hongWai;
    private ImageView im_weather;
    private TextView roomname1;
    
    private String louDong;
    private String classRoom;
	private String temperature;
	private String shiDu;
	private String smoke;
	private String value8;
	private String value9;
	private String value10;
	private String value11;
	//处理硬件的问题
	private int num;
	//seekbar
    private SeekBar seekbar;
    private SeekBar seekbar1;
    private SeekBar seekbar2;
    private SeekBar seekbar3;
	//定位相关
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	private TextView localweather, locationplace;
	private TextView nowweather;
	//private MyHandler myhandler;
	private JSONObject obj;
	private String City;
	LocalCity localcity;
	
	private String MyPlcae;

	//图表按钮
	private Button detail_button;
	
	//用于记录当前的6位信息
	private String nowState;
	
	//false为非自动模式，true为自动模式
	private boolean flage_mode=false;
	//模式
	private View view;
	
	//刷新UI
	Handler handler = new Handler(){
	        @Override
	        public void handleMessage(android.os.Message msg) {
	        	//Toast.makeText(TcpActivity.this, msg.toString(), Toast.LENGTH_LONG).show();
	        	if(msg.what==4){
	        		String place=msg.obj.toString().substring(3, 9);
	        		Log.e("place", place);
	        		tv_smoke.setText(place);}
	        	else{
	        		Log.e("UIInfo",msg.obj.toString());
	        		String value=msg.obj.toString();
	        		
	        		
	        		
	        		louDong=value.substring(0, 1);
	        		classRoom=value.substring(1, 2);
	        		temperature=value.substring(2, 4);
	        		tv_temperature.setText("室内温度："+temperature+"℃"+" ");
	        		
	        		shiDu=value.substring(4, 6);
	        		tv_shiDu.setText("室内湿度："+shiDu+"%");
	        		
	        		smoke=value.substring(6, 7);
	        		//tv_smoke.setText("室内烟雾："+smoke+" ");
	        		
	        		String hongWai=value.substring(7, 8);
	        		//tv_hongWai.append("室内红外："+hongWai);
	        		
	        		char lastNum=value.charAt(12);
	        		String strLastNum=String.valueOf(lastNum);
	        		Log.e("lastNum",strLastNum );
	        		num=Integer.parseInt(strLastNum)+1;
	        		Log.e("num",""+num);
	        		//num=(int)lastNum;
	        		//Log.e("num", String.valueOf(num));
	        		
	        		
	        		//左上角
	        		value8=value.substring(8, 9);
	        		Log.e("value_8", value8);
	        		if("1".equals(value8)){
	        			connect2.setBackgroundResource(R.drawable.led_on);
	        			flag2=true;
	        		}if("0".equals(value8)){
	        			connect2.setBackgroundResource(R.drawable.led_off2);
	        			flag2=false;
	        		}
	        		
	        		//右上角
	        		value9=value.substring(9, 10);
	        		Log.e("value_9", value9);
	        		if("1".equals(value9)){
	        			connect1.setBackgroundResource(R.drawable.led_on);
	        			flag1=true;
	        		}if("0".equals(value9)){
	        			connect1.setBackgroundResource(R.drawable.led_off1);
	        			flag1=false;
	        		}
	        		
	        		//左下角
	        		value10=value.substring(10, 11);
	        		Log.e("value_10", value10);
	        		if("1".equals(value10)){
	        			connect6.setBackgroundResource(R.drawable.led_on);
	        			flag6=true;
	        		}if("0".equals(value10)){
	        			connect6.setBackgroundResource(R.drawable.led_off6);
	        			flag6=false;
	        		}
	        		
	        		//右下角
	        		value11=value.substring(11,12);
	        		Log.e("value_11", value11);
	        		if("1".equals(value11)){
	        			connect5.setBackgroundResource(R.drawable.led_on);
	        			flag5=true;
	        		}if("0".equals(value11)){
	        			connect5.setBackgroundResource(R.drawable.led_off5);
	        			flag5=false;
	        		}
	        		
	        		//当前的6位信息
	        		nowState="11"+value8+value9+value10+value11+num;
	        		Log.e("nowState", nowState);
	        	}
	        	}
	    };
	    
	    
	    //显示actionBar
		  @Override  
		    public boolean onCreateOptionsMenu(Menu menu) {  
		        MenuInflater inflater = getMenuInflater();  
		        inflater.inflate(R.menu.light_control_actionbar,menu);  
		        return super.onCreateOptionsMenu(menu);  
		    } 
		  //actionBar点击事件
		  @Override  
		    public boolean onOptionsItemSelected(MenuItem item) {  
			  switch (item.getItemId()) {  
		        case R.id.action_refresh:
		        	//刷新，重新加载当前Activity
		        	Intent intent = new Intent();
	    		    intent.setClass(TcpActivity.this,TcpActivity.class);
	    		    TcpActivity.this.startActivity(intent);
	    		    finish();
		            return true;  
		        } 
			  
			  switch (item.getItemId()) {  
		        case R.id.change_mode:
		        	if(flage_mode==false){
		        		//切换为自动模式
			        	send_change_auto();
			        	detail_button.setText("自动控制模式");
			        	flage_mode=true;
			        	
			            return true;
		        	}else{
		        		//切换为非自动模式
		        		send_change_notauto();
		        		detail_button.setText("手动控制模式");
		        		flage_mode=false;
		        		return true;
		        	}
		        	  
		        }  
			  switch (item.getItemId()) {  
		        case R.id.table:
		        	//跳转到图表界面
		        	Intent intent = new Intent();
				    intent.setClass(TcpActivity.this,MainActivity.class);
				    TcpActivity.this.startActivity(intent);
		            return true;  
		        } 
		        return false;
		    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);
        ActionBar actionBar = getActionBar();  
        actionBar.setDisplayHomeAsUpEnabled(true);
        //seekbar
        seekbar=(SeekBar)findViewById(R.id.timeline);
        //seekbar
        seekbar1=(SeekBar)findViewById(R.id.timeline1);
        seekbar2=(SeekBar)findViewById(R.id.timeline2);
        seekbar3=(SeekBar)findViewById(R.id.timeline3);
        //显示当前的控制模式
        detail_button=(Button) findViewById(R.id.detail_button);
        /*detail_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
			    intent.setClass(TcpActivity.this,MainActivity.class);
			    TcpActivity.this.startActivity(intent);
			}
		});*/
        
        //天气和位置相关
        localweather = (TextView) findViewById(R.id.localweather);
		//locationplace = (TextView) findViewById(R.id.location);
		nowweather = (TextView) findViewById(R.id.nowweather);

		//handler = new MyHandler();
		localcity = new LocalCity();

		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		// option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setProdName(getPackageName());
		mLocationClient.setLocOption(option);
		if (isInterent.hasInternet(this)) {
			mLocationClient.start();
		} else {
			Toast.makeText(getApplicationContext(), "网络异常，请检查网络是否连接",
					Toast.LENGTH_LONG).show();
		}
      //左上角
        connect2=(ImageButton)findViewById(R.id.button2);
        //右上角
        connect1=(ImageButton)findViewById(R.id.button1);
        //左下角
        connect6=(ImageButton)findViewById(R.id.button6);
        //右下角
        connect5=(ImageButton)findViewById(R.id.button5);
        
        //如果没有网络连接的话
        if(!isInterent.hasInternet(this)){
       	 	Toast.makeText(this, "网络不可用,请检查网络连接",Toast.LENGTH_LONG).show();  
       	 	connect2.setBackgroundResource(R.drawable.led_off);
       	 	connect1.setBackgroundResource(R.drawable.led_off);
       	 	connect5.setBackgroundResource(R.drawable.led_off);
       	 	connect6.setBackgroundResource(R.drawable.led_off);
        }else{
        	tv_temperature=(TextView) findViewById(R.id.tv_temperature);
            tv_shiDu=(TextView)findViewById(R.id.tv_shiDu);
            tv_smoke=(TextView)findViewById(R.id.tv_smoke);
            //tv_hongWai=(TextView)findViewById(R.id.tv_hongWai);
            im_weather=(ImageView)findViewById(R.id.iv_weather);
            im_weather.setBackgroundResource(R.drawable.mostly_cloudy);
            //roomname1=(TextView)findViewById(R.id.roomname1);//房间名
            initState();
         }
        //模式
        View view=findViewById(R.id.change_mode);
        
    }
    
/*  //actionbar的显示
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        MenuInflater inflater = getMenuInflater();  
        inflater.inflate(R.menu.tcp,menu);  
        return super.onCreateOptionsMenu(menu);  
    }  
    
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case android.R.id.home:  
            finish();  
            return true;  
        }  
        return false;
    }  */
    
    //左上角
    public void click2(View view){
		if(flag2==false){
			if(num<=9){
				send1(connect2,Integer.parseInt("111"+value9+value10+value11+String.valueOf(num)));
				flag2=true;
				value8="1";
				Log.e("value8", value8);
				Log.e("num8", String.valueOf(num));
				Log.e("SendNum", "111"+value9+value10+value11+String.valueOf(num));
				nowState="111"+value9+value10+value11+String.valueOf(num);
				num++;
				Log.e("Plusnum8", String.valueOf(num));
				
			}else{
				num=0;
				send1(connect2,Integer.parseInt("111"+value9+value10+value11+String.valueOf(num)));
				flag2=true;
				value8="1";
				Log.e("value8", value8);
				Log.e("num8", String.valueOf(num));
				Log.e("SendNum", "111"+value9+value10+value11+String.valueOf(num));
				nowState="111"+value9+value10+value11+String.valueOf(num);
				num++;
				Log.e("Plusnum8", String.valueOf(num));
			}
		}else{
			if(num<=9){
				send0(connect2,Integer.parseInt("110"+value9+value10+value11+String.valueOf(num)));
				flag2=false;
				value8="0";
				Log.e("value8", value8);
				Log.e("num8", String.valueOf(num));
				Log.e("SendNum", "110"+value9+value10+value11+String.valueOf(num));
				nowState="110"+value9+value10+value11+String.valueOf(num);
				num++;
				Log.e("Plusnum8", String.valueOf(num));
				
			
			}else{
				num=0;
				send0(connect2,Integer.parseInt("110"+value9+value10+value11+String.valueOf(num)));
				flag2=false;
				value8="0";
				Log.e("value8", value8);
				Log.e("num8", String.valueOf(num));
				Log.e("SendNum8", "110"+value9+value10+value11+String.valueOf(num));
				nowState="110"+value9+value10+value11+String.valueOf(num);
				num++;
				Log.e("Plusnum8", String.valueOf(num));
			}
		}
		
    }
  
     //右上角
     public void click1(View view){
    	 
    	 if(flag1==false){
    		 if(num<=9){
    			 send1(connect1,Integer.parseInt("11"+value8+"1"+value10+value11+String.valueOf(num)));
    			 flag1=true;
    			 value9="1";
    			 Log.e("value9", value9);
 				Log.e("num9", String.valueOf(num));
 				Log.e("SendNum9", "11"+value8+"1"+value10+value11+String.valueOf(num));
 				nowState="11"+value8+"1"+value10+value11+String.valueOf(num);
 				num++;
 				Log.e("Plusnum9", String.valueOf(num));
 				
    			 
    			
    		 }else{
    			 num=0;
    			 send1(connect1,Integer.parseInt("11"+value8+"1"+value10+value11+String.valueOf(num)));
    			 flag1=true;
    			 value9="1";
    			 Log.e("value9", value9);
  				Log.e("num9", String.valueOf(num));
  				Log.e("SendNum9", "11"+value8+"1"+value10+value11+String.valueOf(num));
  				nowState="11"+value8+"1"+value10+value11+String.valueOf(num);
  				num++;
  				Log.e("Plusnum9", String.valueOf(num));
  				
    			 
 				
    		 }
    	 }else{
    		 if(num<=9){
    			 send0(connect1,Integer.parseInt("11"+value8+"0"+value10+value11+String.valueOf(num)));
    			 flag1=false;
    			 value9="0";
    			 Log.e("value9", value9);
  				Log.e("num9", String.valueOf(num));
  				Log.e("SendNum9","11"+value8+"0"+value10+value11+String.valueOf(num));
  				nowState="11"+value8+"0"+value10+value11+String.valueOf(num);
  				num++;
  				Log.e("Plusnum9", String.valueOf(num));
  				
    			
    		 }else{
    			 num=0;
    			 send0(connect1,Integer.parseInt("11"+value8+"0"+value10+value11+String.valueOf(num)));
    			 flag1=false;
    			 value9="0";
    			 Log.e("value9", value9);
  				Log.e("num9", String.valueOf(num));
  				Log.e("SendNum9", "11"+value8+"0"+value10+value11+String.valueOf(num));
  				nowState="11"+value8+"0"+value10+value11+String.valueOf(num);
  				num++;
  				Log.e("Plusnum9", String.valueOf(num));
  				
    			
    		 }
    	 }
    	 
     }
     
     //左下角
     public void click6(View view){
		if(flag6==false){
			if(num<=9){
				send1(connect6,Integer.parseInt("11"+value8+value9+"1"+value11+String.valueOf(num)));
				flag6=true;
				value10="1";
				 Log.e("value10", value10);
	 				Log.e("num10", String.valueOf(num));
	 				Log.e("SendNum10","11"+value8+value9+"1"+value11+String.valueOf(num));
	 				nowState="11"+value8+value9+"1"+value11+String.valueOf(num);
	 				num++;
	 				Log.e("Plusnum10", String.valueOf(num));
	 				
				
			}else{
				num=0;
				send1(connect6,Integer.parseInt("11"+value8+value9+"1"+value11+String.valueOf(num)));
				flag6=true;
				value10="1";
				 Log.e("value10", value10);
	 				Log.e("num10", String.valueOf(num));
	 				Log.e("SendNum10","11"+value8+value9+"1"+value11+String.valueOf(num));
	 				nowState="11"+value8+value9+"1"+value11+String.valueOf(num);
	 				num++;
	 				Log.e("Plusnum10", String.valueOf(num));
	 				
				
			}
		}else{
			if(num<=9){
				send0(connect6,Integer.parseInt("11"+value8+value9+"0"+value11+String.valueOf(num)));
				flag6=false;
				value10="0";
				 Log.e("value10", value10);
	 				Log.e("num10", String.valueOf(num));
	 				Log.e("SendNum10","11"+value8+value9+"0"+value11+String.valueOf(num));
	 				nowState="11"+value8+value9+"0"+value11+String.valueOf(num);
	 				num++;
	 				Log.e("Plusnum10", String.valueOf(num));
	 				
				
			}else{
				num=0;
				send0(connect6,Integer.parseInt("11"+value8+value9+"0"+value11+String.valueOf(num)));
				flag6=false;
				value10="0";
				 Log.e("value10", value10);
	 				Log.e("num10", String.valueOf(num));
	 				Log.e("SendNum10","11"+value8+value9+"0"+value11+String.valueOf(num));
	 				nowState="11"+value8+value9+"0"+value11+String.valueOf(num);
	 				num++;
	 				Log.e("Plusnum10", String.valueOf(num));
	 				
				
			}
		}
		
     }
    
    //右下角
    public void click5(View view){
		if(flag5==false){
			if(num<=9){
				send1(connect5,Integer.parseInt("11"+value8+value9+value10+"1"+String.valueOf(num)));
				flag5=true;
				value11="1";
				 Log.e("value11", value11);
	 				Log.e("num11", String.valueOf(num));
	 				Log.e("SendNum11","11"+value8+value9+value10+"1"+String.valueOf(num));
	 				nowState="11"+value8+value9+value10+"1"+String.valueOf(num);
	 				num++;
	 				Log.e("Plusnum11", String.valueOf(num));
	 				
				
			}else{
				num=0;
				send1(connect5,Integer.parseInt("11"+value8+value9+value10+"1"+String.valueOf(num)));
				flag5=true;
				value11="1";
				Log.e("value11", value11);
 				Log.e("num11", String.valueOf(num));
 				Log.e("SendNum11","11"+value8+value9+value10+"1"+String.valueOf(num));
 				nowState="11"+value8+value9+value10+"1"+String.valueOf(num);
 				num++;
 				Log.e("Plusnum11", String.valueOf(num));
 				
				
			}
		}else{
			if(num<=9){
				send0(connect5,Integer.parseInt("11"+value8+value9+value10+"0"+String.valueOf(num)));
				flag5=false;
				value11="0";
				Log.e("value11", value11);
 				Log.e("num11", String.valueOf(num));
 				Log.e("SendNum11","11"+value8+value9+value10+"0"+String.valueOf(num));
 				nowState="11"+value8+value9+value10+"0"+String.valueOf(num);
 				num++;
 				Log.e("Plusnum11", String.valueOf(num));
 				
				
			}else{
				num=0;
				send0(connect5,Integer.parseInt("11"+value8+value9+value10+"0"+String.valueOf(num)));
				flag5=false;
				value11="0";
				Log.e("value11", value11);
 				Log.e("num11", String.valueOf(num));
 				Log.e("SendNum11","11"+value8+value9+value10+"0"+String.valueOf(num));
 				nowState="11"+value8+value9+value10+"0"+String.valueOf(num);
 				num++;
 				Log.e("Plusnum11", String.valueOf(num));
 				
				
			}
		}
		
    }
  
  //获取各个开关的初始状态
  public void initState(){
	  
	  
      Log.e("1","1");
      Thread t = new Thread(){
          @Override
          public void run() {
              //String path = "http://api.yeelink.net/v1.1/device/345048/sensor/384730/datapoints";
              String path = "http://api.yeelink.net/v1.1/device/345048/sensor/387624/datapoints";
              //使用httpClient框架做get方式提交
              //1.创建HttpClient对象
              HttpClient hc = new DefaultHttpClient();
              Log.e("2","2");
              //2.创建httpGet对象，构造方法的参数就是网址
              HttpGet hg = new HttpGet(path);
              hg.addHeader("Accept", "application/json");
              hg.addHeader("U-ApiKey","1f1769d3a1bdb7550e0f1db2a66a417f");
              //3.使用客户端对象，把get请求对象发送出去
              try {
                  HttpResponse hr = hc.execute(hg);
                  //拿到响应头中的状态行
                  Log.e("3","3");
                  StatusLine sl = hr.getStatusLine();
                  if(sl.getStatusCode() == 200){
                      //拿到响应头的实体
                      HttpEntity he = hr.getEntity();
                      
                      //拿到实体中的内容，其实就是服务器返回的输入流
                      InputStream is = he.getContent();
                      String text = Utils.getTextFromStream(is);
						
                      Log.e("4","4");
                      Log.e("MyInfo",text);
                      Gson gson=new Gson();
                      InfoEntity infoEntity=gson.fromJson(text, InfoEntity.class);
                      Log.e("inFoEntity",infoEntity.toString());
                      String value=infoEntity.getValue();
                      Message msg = handler.obtainMessage();

					msg.obj = value;
					handler.sendMessage(msg);
                  }
              } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
          }
          
      };
      t.start();
      
  }
  //发送1的命令
  public void send1(ImageButton imageButton,final Integer integer){
	  imageButton.setBackgroundResource(R.drawable.led_on);
		Thread t=new Thread(){
			
			@Override
			public void run() {
				//String path = "http://api.yeelink.net/v1.1/device/345048/sensor/383870/datapoints";
				String path = "http://api.yeelink.net/v1.1/device/345048/sensor/387623/datapoints";
				//1.创建客户端对象
				HttpClient hc = new DefaultHttpClient();
				//2.创建post请求对象
				HttpPost hp = new HttpPost(path);
				
				//设置请求头
				hp.setHeader("Accept", "application/json");
				hp.setHeader("U-ApiKey","1f1769d3a1bdb7550e0f1db2a66a417f");
				
				Map<String,Integer> map=new HashMap<String,Integer>();
				map.put("value",integer);
				Gson gson=new Gson();
				String json=gson.toJson(map);
				
				try {
					
					Log.e("MyJson",json);
					StringEntity se = new StringEntity(json);
					
					hp.setEntity(se);
					
					HttpResponse hr = hc.execute(hp);
					if(hr.getStatusLine().getStatusCode() == 200){
						InputStream is = hr.getEntity().getContent();
						String text = Utils.getTextFromStream(is);
						Log.e("GetInfo", text);
						Log.e("SEND1", "发送1成功");
						/*//发送消息，让主线程刷新ui显示text
						Message msg = handler.obtainMessage();
						
						msg.obj = text;
						Log.e("MyInfo",msg.obj.toString());
						handler.sendMessage(msg);*/
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		t.start();
  }
  
//发送切换的命令:切换成自动控制模式
  public void send_change_auto(){
		Thread t=new Thread(){
			
			@Override
			public void run() {
				//String path = "http://api.yeelink.net/v1.1/device/345048/sensor/383870/datapoints";
				String path = "http://api.yeelink.net/v1.1/device/345048/sensor/387623/datapoints";
				//1.创建客户端对象
				HttpClient hc = new DefaultHttpClient();
				//2.创建post请求对象
				HttpPost hp = new HttpPost(path);
				
				//设置请求头
				hp.setHeader("Accept", "application/json");
				hp.setHeader("U-ApiKey","1f1769d3a1bdb7550e0f1db2a66a417f");
				
				Map<String,Integer> map=new HashMap<String,Integer>();
				nowState="12"+nowState.substring(2);
				Log.e("nowState1", nowState);
				
				map.put("value",Integer.valueOf(nowState));
				Gson gson=new Gson();
				String json=gson.toJson(map);
				
				try {
					
					Log.e("MyJson",json);
					StringEntity se = new StringEntity(json);
					
					hp.setEntity(se);
					
					HttpResponse hr = hc.execute(hp);
					if(hr.getStatusLine().getStatusCode() == 200){
						InputStream is = hr.getEntity().getContent();
						String text = Utils.getTextFromStream(is);
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		t.start();
  }
  
//发送切换的命令:切换成手动控制模式
  public void send_change_notauto(){
		Thread t=new Thread(){
			
			@Override
			public void run() {
				//String path = "http://api.yeelink.net/v1.1/device/345048/sensor/383870/datapoints";
				String path = "http://api.yeelink.net/v1.1/device/345048/sensor/387623/datapoints";
				//1.创建客户端对象
				HttpClient hc = new DefaultHttpClient();
				//2.创建post请求对象
				HttpPost hp = new HttpPost(path);
				
				//设置请求头
				hp.setHeader("Accept", "application/json");
				hp.setHeader("U-ApiKey","1f1769d3a1bdb7550e0f1db2a66a417f");
				
				Map<String,Integer> map=new HashMap<String,Integer>();
				nowState="11"+nowState.substring(2);
				map.put("value",Integer.valueOf(nowState));
				
				Log.e("nowState2", nowState);
				Gson gson=new Gson();
				String json=gson.toJson(map);
				
				try {
					
					Log.e("MyJson",json);
					StringEntity se = new StringEntity(json);
					
					hp.setEntity(se);
					
					HttpResponse hr = hc.execute(hp);
					if(hr.getStatusLine().getStatusCode() == 200){
						InputStream is = hr.getEntity().getContent();
						String text = Utils.getTextFromStream(is);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		t.start();
  }
  
  //发送0命令
  public void send0(ImageButton imageButton,final Integer integer){
	  
	  imageButton.setBackgroundResource(R.drawable.led_off);
		Thread t = new Thread(){
			@Override
			public void run() {
				String path = "http://api.yeelink.net/v1.1/device/345048/sensor/387623/datapoints";
				//1.创建客户端对象
				HttpClient hc = new DefaultHttpClient();
				//2.创建post请求对象
				HttpPost hp = new HttpPost(path);
				
				//设置请求头
				hp.setHeader("Accept", "application/json");
				hp.setHeader("U-ApiKey","1f1769d3a1bdb7550e0f1db2a66a417f");
				
				Map<String,Integer> map=new HashMap<String,Integer>();
				map.put("value",integer);
				Gson gson=new Gson();
				String json=gson.toJson(map);
				
				try {
					Log.e("MyJson",json);
					StringEntity se = new StringEntity(json);
					hp.setEntity(se);
					HttpResponse hr = hc.execute(hp);
					if(hr.getStatusLine().getStatusCode() == 200){
						InputStream is = hr.getEntity().getContent();
						String text = Utils.getTextFromStream(is);
						Log.e("SEND0", "发送0成功");
						//发送消息，让主线程刷新ui显示text
						/*Message msg = handler.obtainMessage();
						
						msg.obj = text;
						Log.e("MyInfo",msg.obj.toString());
						handler.sendMessage(msg);*/
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
  }

	// 定位
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}

			MyPlcae = location.getAddrStr();
//			locationplace.setText(location.getCity());
			localcity.setCity(location.getCity());
			Message msg = new Message();
			
			//向UI刷新线程发送城市信息
			msg.what = 4;
			msg.obj = MyPlcae;
			handler.sendMessage(msg);
			
			Log.e("MyPlace", ""+MyPlcae);

			//Toast.makeText(getApplicationContext(), "" + place, 1).show();
		}

	}

	protected void onDestroy() {
		// 退出时销毁定位
		mLocationClient.stop();
		super.onDestroy();
	}
  

}
