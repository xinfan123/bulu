package com.xinfan.blueblue.activity.systemset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSetActivity;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.SystemSetContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSetParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class PaidSet extends BaseActivity {
	private ListView messageSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;
	private Long userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messageSr = (ListView) findViewById(R.id.message_select_type);
		SharePreferenceUtil util = new SharePreferenceUtil(PaidSet.this, Constants.USER_INFO);
		userid=util.getUserId();
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
		SelectVo two = new SelectVo("10", "10元以上");
		SelectVo three = new SelectVo("100", "100元以上");
		SelectVo four = new SelectVo("1000", "1000元以上");

		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
	}

	public void SaveMessage(int index) {

		final SelectVo select = list.get(index);
		SystemSetContext.setPaid(PaidSet.this,Integer.parseInt(select.getId()),userid);
		SystemSetContext.setIsUpdate(PaidSet.this, true, userid);
		SystemSetActivity.instance.refresh();
		PaidSet.this.finish();
	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
