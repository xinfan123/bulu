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

public class SimilaritySet extends BaseActivity {

	private static String[] m = { "一级", "二级", "三级", "四级", "五级" };

	private ListView messageSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;
	private Long userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messageSr = (ListView) findViewById(R.id.message_select_type);
		SharePreferenceUtil util = new SharePreferenceUtil(SimilaritySet.this,
				Constants.USER_INFO);
		userid = util.getUserId();

		init();

		adapter = new SystemSetAdapter(this, list);
		// messagenumSr.setSelection(0, true);
		messageSr.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				SaveMessage(arg2);
			}

		});
		// 将adapter 添加到spinner中
		messageSr.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	public void init() {
		SelectVo one = new SelectVo("1", "一级");
		SelectVo two = new SelectVo("2", "二级");
		SelectVo three = new SelectVo("3", "三级");
		SelectVo four = new SelectVo("4", "四级");
		SelectVo five = new SelectVo("5", "五级");

		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		list.add(five);
	}

	public void SaveMessage(int v) {

		final SelectVo select = list.get(v);
		SystemSetContext.setSimilarity(SimilaritySet.this, Integer.parseInt(select.getId()),
				userid);
		SystemSetContext.setIsUpdate(SimilaritySet.this, true, userid);
		SystemSetActivity.instance.refresh();
		SimilaritySet.this.finish();

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
