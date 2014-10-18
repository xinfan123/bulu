package com.xinfan.blueblue.activity.systemset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.xinfan.blueblue.activity.Login;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSetParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;


public class MessageNumSelectActivity extends Activity {
	private ListView messagenumSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messagenumSr = (ListView) findViewById(R.id.message_select_type);

		init();

		adapter = new SystemSetAdapter(this, list);

		messagenumSr.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
				SaveMessage(arg2);
			}

		});

		// 将adapter 添加到spinner中
		messagenumSr.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	public void init() {
		SelectVo one = new SelectVo("20", "20条");
		SelectVo two = new SelectVo("50", "50条");
		SelectVo three = new SelectVo("100", "100条");

		list.add(one);
		list.add(two);
		list.add(three);
	}

	public void SaveMessage(int arg2) {

		final SelectVo select = list.get(arg2);
		
		Integer maxCount=Integer.parseInt(select.getId());
		
		SharePreferenceUtil util = new SharePreferenceUtil(MessageNumSelectActivity.this, Constants.USER_INFO);
		Long userId=util.getUserId();
		
		Request request = new Request(FunIdConstants.SET_USERSET);
		UserSetParam param = new UserSetParam();
		param.setMaxCount(maxCount);
		param.setUserId(userId);
		request.setParam(param);
		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {
			
		public void call(Request data) {
			
		BaseResult result = (BaseResult) data.getResult();
	
		if(result.getResult()==1){
			
			ToastUtil.showMessage(MessageNumSelectActivity.this,result.getMsg());
			
			
			SystemSet.instance.SetMessageNum(select.getText());
			MessageNumSelectActivity.this.finish();
		}else{
			ToastUtil.showMessage(MessageNumSelectActivity.this,result.getMsg());
		}
	
	}
});
		
	

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
