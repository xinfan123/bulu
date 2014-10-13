package com.xinfan.blueblue.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.ThemeVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSentParam;

public class ThemeSetMenu extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themeset_menu);
		layout = (LinearLayout) findViewById(R.id.theme_menu_id);

		TextView delBtn = (TextView) findViewById(R.id.btn_delete);
		delBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delete(v);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void delete(View v) {

		Long id = ThemeSetMenu.this.getIntent().getLongExtra("id",0);

		Request request = new Request(FunIdConstants.DELETE_USER_SENT);

		UserSentParam param = new UserSentParam();
		param.setId(id);
		param.setUserId(LoginUserContext.getUserId(ThemeSetMenu.this));
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(ThemeSetMenu.this, request, new ObserverCallBack() {
			public void call(Request request) {
				ThemeSetActivity.instance.load();
				ThemeSetMenu.this.finish();
			}
		});

	}

}
