package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class UpdateNickname extends Activity {
	private EditText mNewNickname;
	private String newNickname;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_nickname);

		mNewNickname = (EditText) findViewById(R.id.new_nickname_edit);

	}

	public void set_nickname(View v) {
		newNickname = mNewNickname.getText().toString();
		if (newNickname == null || newNickname.equals("")) {
			ToastUtil.showMessage(UpdateNickname.this, "昵称不能为空！");
		} else {

			Request request = new Request(FunIdConstants.SET_USER_NICKNAME);
			UserParam param = new UserParam();

			param.setUserId(LoginUserContext.getUserId(this));
			param.setUserName(newNickname);
			request.setParam(param);

			AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

				public void call(Request data) {

					BaseResult result = (BaseResult) data.getResult();
					ToastUtil.showMessage(UpdateNickname.this, result.getMsg());

					AccountInfoActivity.instance.load();

					UpdateNickname.this.finish();
				}
			});
		}
	}

	public void nickname_back(View v) {
		this.finish();
	}

}
