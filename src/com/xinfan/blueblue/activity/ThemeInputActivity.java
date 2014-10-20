package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.ThemeVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSentParam;

public class ThemeInputActivity extends Activity {

	private TextView ok;

	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_input);

		ok = (TextView) this.findViewById(R.id.click_ok);
		text = (TextView) this.findViewById(R.id.theme_text);

		ok.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String str = (String) text.getText().toString();
				if (str == null || str.length() == 0) {
					ToastUtil.showMessage(ThemeSetActivity.instance, "请输入正确内容");
					return;
				}

				if (str.length() > 180) {
					ToastUtil.showMessage(ThemeSetActivity.instance, "内容太长，不能超过180个字符");
					return;
				}

				Request request = new Request(FunIdConstants.SET_USER_SENT);

				UserSentParam param = new UserSentParam();
				param.setUserId(LoginUserContext.getUserId(ThemeInputActivity.this));
				param.setUserSent(str);
				request.setParam(param);

				AnsynHttpRequest.requestSimpleByPost(ThemeInputActivity.this, request, new RequestSucessCallBack() {
					public void call(Request request) {
						ThemeSetActivity.instance.load();
						ToastUtil.showMessage(ThemeInputActivity.this, "添加主题成功");
						ThemeInputActivity.this.finish();
					}
				});
			}
		});

	}

	public void send_msg_back(View view) {
		finish();
	}

	public void click_ok(View view) {
		finish();
	}

}