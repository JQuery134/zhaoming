package com.example.zhaoming;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button bt_login = null;
	private MyOpenHelper oh;
    private SQLiteDatabase db;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		bt_login = (Button)findViewById(R.id.bt_login);
		bt_login.setOnClickListener(new ButtononeListener());
		
		
		oh = new MyOpenHelper(this, "users.db", null, 1);
        db = oh.getWritableDatabase();
        

        try{
            //��������
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("name", "6102213818");
            values.put("password", "123");
            db.insert("users", null, values);

            values.clear();
            
           
            values.put("name", "Wangxiaoyu");
            values.put("password", "6103213024");
            db.insert("users", null, values);
            
            values.clear();
            
            
            values.put("name", "123");
            values.put("password", "123");
            db.insert("users", null, values);
            //����ִ�гɹ�
            db.setTransactionSuccessful();

        }finally {
            //�ر�����ͬʱ�ύ������Ѿ���������ִ�гɹ�����ôsql������Ч�ˣ���֮��sql���ع�
            db.endTransaction();
        }

        final EditText ed_name= (EditText) findViewById(R.id.ed_name);
        final EditText ed_password= (EditText) findViewById(R.id.ed_password);
        RadioGroup rg= (RadioGroup) findViewById(R.id.rg);
        final RadioButton rb_show= (RadioButton) findViewById(R.id.rb_show);
        final RadioButton rb_hide= (RadioButton) findViewById(R.id.rb_hide);
        
        ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        
        
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== rb_hide.getId()){
                    //��������
                    ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                if (checkedId==rb_show.getId()){
                    //��ʾ����
                    ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
		
		
	}
	
	class ButtononeListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			EditText ed_name= (EditText) findViewById(R.id.ed_name);
	        EditText ed_password= (EditText) findViewById(R.id.ed_password);

	        RadioButton rb_show= (RadioButton) findViewById(R.id.rb_show);
	        RadioButton rb_hide= (RadioButton) findViewById(R.id.rb_hide);


	        String name=ed_name.getText().toString();
	        String password=ed_password.getText().toString();

	        String name_reginstered=null;
	        String password_reginstered=null;
	        try{
	            //��������
	            db.beginTransaction();
	            Cursor cursor = db.query("users", null, null, null, null, null, null, null);

	            while(cursor.moveToNext()) {
	                name_reginstered = cursor.getString(cursor.getColumnIndex("name"));
	                password_reginstered = cursor.getString(cursor.getColumnIndex("password"));

	            }

	            if(name.equals(name_reginstered) && password.equals(password_reginstered)){
	                Toast.makeText(MainActivity.this,"��½�ɹ�",Toast.LENGTH_SHORT).show();
	                
	             // TODO Auto-generated method stub
	    			//Toast.makeText(MainActivity.this, "��¼ing", Toast.LENGTH_LONG).show();
	    		    Intent intent = new Intent();
	    		    intent.setClass(MainActivity.this,Activity1.class);
	    		    MainActivity.this.startActivity(intent);
	    		    finish();
	            }else{
	                Toast.makeText(MainActivity.this,"��½ʧ��",Toast.LENGTH_SHORT).show();
	            }

	            //����ִ�гɹ�
	            db.setTransactionSuccessful();

	        }finally {
	            //�ر�����ͬʱ�ύ������Ѿ���������ִ�гɹ�����ôsql������Ч�ˣ���֮��sql���ع�
	            db.endTransaction();
	        }
			
			
			
		}
	}
}
