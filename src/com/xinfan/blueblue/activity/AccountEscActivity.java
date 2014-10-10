package com.xinfan.blueblue.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AccountEscActivity extends Activity {
	//private MyDialog dialog;
	private LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_esc);
		//dialog=new MyDialog(this);
		layout=(LinearLayout)findViewById(R.id.esc_layout2);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
						Toast.LENGTH_SHORT).show();	
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	
	public void escbutton1(View v) {  
    	this.finish();    	
      }  
	public void escbutton0(View v) {  
		Intent intent=new Intent();
    	intent.setClass(AccountEscActivity.this, Login.class);
    	startActivity(intent);
    	this.finish();
//    	MainWeixin.instance.finish();
      }  
	
}
