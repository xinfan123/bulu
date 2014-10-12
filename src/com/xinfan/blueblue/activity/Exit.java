package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.BaseParam;

public class Exit extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exit_dialog);
		// dialog=new MyDialog(this);
		layout = (LinearLayout) findViewById(R.id.exit_layout);
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "退出", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void exitNobutton(View v) {
		this.finish();
	}

	public void exitYesbutton(View v) {

		Request request = new Request(FunIdConstants.LOGOUT);

		SharePreferenceUtil util = new SharePreferenceUtil(Exit.this, Constants.USER_INFO);
		Long userid = util.getUserId();

		BaseParam param = new BaseParam();
		param.setUserId(userid);

		request.setParam(param);
		request.setShowDialog(false);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {
			public void call(Request request) {
				Exit.this.finish();
				MainActivity.instance.finish();
			}
		});

	}

}
