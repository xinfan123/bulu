package com.xinfan.blueblue.activity;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.xinfan.blueblue.activity.adapter.ThemeSetAdapter;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.AlertHelper;
import com.xinfan.blueblue.util.LogUtil;
import com.xinfan.blueblue.vo.ThemeVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.BaseParam;
import com.xinfan.msgbox.http.service.vo.result.UserSentListResult;
import com.xinfan.msgbox.http.service.vo.result.UserSentResult;

public class ThemeSetActivity extends Activity {
	/** Called when the activity is first created. */

	private ListView themeList;

	public ArrayList list = new ArrayList();

	public TextView currentView;

	public TextView maxView;

	public static ThemeSetActivity instance;

	public ThemeSetAdapter adapter;

	public int maxsize = 4;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_set);

		instance = this;

		// list.add("如何实现长按列表弹出对话框 [复制链接]");

		themeList = (ListView) findViewById(R.id.theme_list);
		currentView = (TextView) findViewById(R.id.theme_count_current);
		maxView = (TextView) findViewById(R.id.theme_count_max);

		adapter = new ThemeSetAdapter(this, list);

		themeList.setAdapter(adapter);

		themeList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {
				ThemeVo vo = (ThemeVo) list.get(position);
				if (vo != null) {
					Intent intent = new Intent();
					intent.putExtra("id", vo.getId());
					intent.setClass(ThemeSetActivity.this, ThemeSetMenu.class);
					startActivity(intent);
				}

				return false;
			}
		});

		adapter.notifyDataSetChanged();

		reCountTip();

		load();
	}

	public void load() {

		Request request = new Request(FunIdConstants.GET_USER_SENT_LIST);

		BaseParam param = new BaseParam();
		param.setUserId(LoginUserContext.getUserId(this));
		request.setParam(param);
		

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request request) {
				UserSentListResult sentList = (UserSentListResult) request.getResult();

				list.clear();

				for (UserSentResult item : sentList.getList()) {
					ThemeVo vo = new ThemeVo();

					try {
						com.xinfan.blueblue.util.BeanUtils.copyProperty(item, vo);
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.e(e.getMessage(), e);
					}
					list.add(vo);
				}
				adapter.notifyDataSetChanged();
				reCountTip();
			}
		});
	}

	public void send_msg_back(View view) {
		finish();
	}

	public void click_theme_add(View view) {

		if (list.size() >= this.maxsize) {
			AlertHelper.showNormalTips(this, "主题发布提示", "您最大的发布数量是" + maxsize);
			return;
		}

		Intent intent = new Intent();
		intent.setClass(this, ThemeInputActivity.class);
		startActivity(intent);
	}

	public void reCountTip() {
		maxView.setText("您可以发布：" + maxsize + "条");
		currentView.setText("已发布：" + list.size() + "条");
	}

}