package com.xinfan.blueblue.activity.systemset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSetActivity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSetParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class ReputationSet extends Activity {

	private ListView messageSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;

	private static String[] m = { "全部", "一星", "二星", "三星", "4星", "五星" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messageSr = (ListView) findViewById(R.id.message_select_type);

		init();
		
		adapter = new SystemSetAdapter(this, list);
		// messagenumSr.setSelection(0, true);
		messageSr.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				SaveMessage(arg2);
			}

		});
		// 将adapter 添加到spinner中
		messageSr.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	public void init() {
		SelectVo one = new SelectVo("0", "全部");
		SelectVo two = new SelectVo("1", "一星");
		SelectVo three = new SelectVo("2", "二星");
		SelectVo four = new SelectVo("3", "三星");
		SelectVo five = new SelectVo("4", "四星");
		SelectVo six = new SelectVo("5", "五星");

		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		list.add(five);
		list.add(six);
	}

	public void SaveMessage(int v) {

		final SelectVo select = list.get(v);
		
		Integer requtation=Integer.parseInt(select.getId());
		
		SharePreferenceUtil util = new SharePreferenceUtil(ReputationSet.this, Constants.USER_INFO);
		Long userId=util.getUserId();
		
		Request request = new Request(FunIdConstants.SET_USERSET);
		UserSetParam param = new UserSetParam();
		param.setMinCredit(requtation);
		param.setUserId(userId);
		request.setParam(param);
		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {
			
		public void call(Request data) {
			
		BaseResult result = (BaseResult) data.getResult();
	
		if(result.getResult()==1){
			
			ToastUtil.showMessage(ReputationSet.this,result.getMsg());
			
			
			SystemSetActivity.instance.SetReputation(select.getText());
			ReputationSet.this.finish();
		}else{
			ToastUtil.showMessage(ReputationSet.this,result.getMsg());
		}
	
	}
});
		

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
