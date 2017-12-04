package com.example.zhaoming;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.model.LatLng;
import com.example.zhaoming.MainActivity.ButtononeListener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;

public class Activity1 extends Activity {
	private ScaleAnimation sa=null;
	private static final String TAG = "HelloWorld";
	private MyBaduSdkReceiver baduSdkReceiver;
	private BaiduMap baiduMap;
	private MapView mapview;
	private double latitude1 = 28.66753;// γ��
	private double longitude1 = 115.806217;// ����
	private LatLng NCU_xingong = new LatLng(latitude1, longitude1);// �ϲ���ѧ�Ź�¥
	
	private double latitude2 = 28.670208;// γ��
	private double longitude2 = 115.810871;// ����
	private LatLng NCU_zhujiao = new LatLng(latitude2, longitude2);// �ϲ���ѧ�Ź�¥

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_activity1);
		
		initManager();
		setContentView(R.layout.mymap);
		init();
		
	}
	
	private void init() {
		// ���õ�ͼ����V2.X 3-19 V1.X 3-18��
				// �� �޸����ļ��ĸ�ʽ �Ż��˿ռ��ʹ�ã����� 110M 15M��
				// �� �����˼��� 3DЧ����18 19��

				mapview = (MapView) findViewById(R.id.mapview);

				baiduMap = mapview.getMap();
				// BaiduMap: ��������ĳһ��MapView �� ��ת���ƶ������ţ��¼�����

				// ������ͼ״̬��Ҫ�����ı仯 ʹ�ù�����MapStatusUpdateFactory����
				MapStatusUpdate mapstatusUpdate = MapStatusUpdateFactory.zoomTo(19);// Ĭ�ϵļ���12
				// �������ż���
				baiduMap.setMapStatus(mapstatusUpdate);

				// LatLng latlng = new LatLng(arg0, arg1);// ���� ��γ�� ����1 γ�� ����2 ����
				MapStatusUpdate mapstatusUpdatePoint = MapStatusUpdateFactory
						.newLatLng(NCU_xingong);
				// �������ĵ� Ĭ�����찲��
				baiduMap.setMapStatus(mapstatusUpdatePoint);

				mapview.showZoomControls(true);// Ĭ����true ��ʾ���Ű�ť
				
				mapview.showScaleControl(true);// Ĭ����true ��ʾ���
				baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
				BitmapDescriptor bitMap=BitmapDescriptorFactory.fromResource(R.drawable.at);
				
				MarkerOptions mark_xingong=new MarkerOptions();
				mark_xingong.position(NCU_xingong);
				mark_xingong.title("�Ź�¥");
				mark_xingong.icon(bitMap);
				
				MarkerOptions mark_zhujiao=new MarkerOptions();
				mark_zhujiao.position(NCU_zhujiao);
				mark_zhujiao.title("����¥");
				mark_zhujiao.icon(bitMap);
				
				baiduMap.addOverlay(mark_xingong);
				baiduMap.addOverlay(mark_zhujiao);
				baiduMap.setOnMarkerClickListener(new MyListener());
	}
	
	class MyListener implements OnMarkerClickListener{

		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
			
			 Intent intent = new Intent();
			    intent.setClass(Activity1.this,Slider1Activity.class);
			    Activity1.this.startActivity(intent);
			return true;
		}

		
	}
	
	private void initManager() {
		SDKInitializer.initialize(getApplicationContext()); // ���ܴ���Activity��������ȫ��Context
		baduSdkReceiver = new MyBaduSdkReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);// ע���������
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR); // ע��keyУ����
		registerReceiver(baduSdkReceiver, filter);
	}
	
	class MyBaduSdkReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getAction();
			if (result
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				// �������
				Toast.makeText(getApplicationContext(), "������", 0).show();
			} else if (result
					.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				// keyУ��ʧ��
				Toast.makeText(getApplicationContext(), "У��ʧ��", 0).show();
			}
		}

	}
	
	class Button1Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(Activity1.this, "�����Ź�ing", Toast.LENGTH_SHORT).show();
			 Intent intent = new Intent();
			    intent.setClass(Activity1.this,Slider1Activity.class);
			    Activity1.this.startActivity(intent);
			    
		}
		
	}
	class Button2Listener implements OnClickListener{
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(Activity1.this, "��������", Toast.LENGTH_SHORT).show();
		 Intent intent = new Intent();
		    intent.setClass(Activity1.this,Slider1Activity.class);
		    Activity1.this.startActivity(intent);
		    
	}
	}
	class Button3Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(Activity1.this, "�������¥", Toast.LENGTH_SHORT).show();
			 Intent intent = new Intent();
			    intent.setClass(Activity1.this,Slider1Activity.class);
			    Activity1.this.startActivity(intent);
			    
		}
	}
		class Button4Listener implements OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity1.this, "������Ӿ��", Toast.LENGTH_SHORT).show();
				 Intent intent = new Intent();
				    intent.setClass(Activity1.this,Slider1Activity.class);
				    Activity1.this.startActivity(intent);
				    
			}
		}
	class Buttonm1Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Intent intent = new Intent();
			    intent.setClass(Activity1.this,MainActivity.class);
			    Activity1.this.startActivity(intent);
			    
		}
		
	}
	
	
	class Buttonm2Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 finish();
			 System.exit(1);
			    
		}
		
	}
	
	
	class Buttonm3Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Intent intent = new Intent();
			    intent.setClass(Activity1.this,Activity_women.class);
			    Activity1.this.startActivity(intent);
			    
		}
		
	}
	
	@Override
	protected void onResume() {
		mapview.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mapview.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(baduSdkReceiver);
		mapview.onDestroy();
		super.onDestroy();
	}
}
	
	
