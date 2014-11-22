package com.xinfan.blueblue.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class UpdateNicknameActivity extends BaseActivity {
	private EditText mNewNickname;
	private String newNickname;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_nickname);

		mNewNickname = (EditText) findViewById(R.id.new_nickname_edit);

		String nickname = this.getIntent().getExtras().getString("nickname");
		mNewNickname.setText(nickname);

	}

	public void set_nickname(View v) {
		newNickname = mNewNickname.getText().toString();
		if (newNickname == null || newNickname.equals("")) {
			ToastUtil.showMessage(UpdateNicknameActivity.this, "昵称不能为空！");
			return;
		} else if (newNickname.length() > 11) {
			ToastUtil.showMessage(UpdateNicknameActivity.this, "昵称大于11位！");
			return;
		} else {

			Request request = new Request(FunIdConstants.SET_USER_NICKNAME);
			UserParam param = new UserParam();

			param.setUserId(LoginUserContext.getUserId(this));
			param.setUserName(newNickname);
			request.setParam(param);

			AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

				public void call(Request data) {

					BaseResult result = (BaseResult) data.getResult();
					if (result.getResult() == 1) {
						ToastUtil.showMessage(UpdateNicknameActivity.this, result.getMsg());
						SharePreferenceUtil util = new SharePreferenceUtil(UpdateNicknameActivity.this, Constants.USER_INFO);
						util.setUsername(newNickname);
						AccountInfoActivity.instance.load();
					} else {
						ToastUtil.showMessage(UpdateNicknameActivity.this, result.getMsg());
					}
					UpdateNicknameActivity.this.finish();
				}
			});
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void nickname_back(View v) {
		this.finish();
	}

}
