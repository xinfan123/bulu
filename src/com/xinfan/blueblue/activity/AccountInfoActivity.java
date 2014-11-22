package com.xinfan.blueblue.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.dao.RequestCacheKeyHelper;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.BaseParam;
import com.xinfan.msgbox.http.service.vo.result.UserResult;

public class AccountInfoActivity extends BaseActivity {

	public static AccountInfoActivity instance;

	private LinearLayout mnickName;
	private TextView nickName;
	private TextView user_info_id;
	private TextView user_info_phone;
	private TextView user_info_credit;
	public ImageView account_avatar;


	private UserResult user;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);

		instance = this;
		init();
		load();
	}

	public void init() {

		mnickName = (LinearLayout) findViewById(R.id.nickname_info_layout);
		nickName = (TextView) findViewById(R.id.nickname_info_tv);

		mnickName = (LinearLayout) findViewById(R.id.nickname_info_layout);
		//nickName = (TextView) findViewById(R.id.nickname_info_edit);

		user_info_id = (TextView) findViewById(R.id.user_info_id);
		user_info_phone = (TextView) findViewById(R.id.user_info_phone);
		user_info_credit = (TextView) findViewById(R.id.user_info_credit);
		account_avatar = (ImageView)findViewById(R.id.account_avatar);
		
		mnickName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle data = new Bundle();
				data.putString("nickname", user.getUserName());
				intent.putExtras(data);
				intent.setClass(AccountInfoActivity.this,UpdateNicknameActivity.class);
				startActivity(intent);
			}
		});

		//mpassword = (RelativeLayout) findViewById(R.id.password_update_layout);

	}

	public void load() {

		Request request = new Request(FunIdConstants.GET_USER);
		BaseParam param = new BaseParam();
		param.setUserId(LoginUserContext.getUserId(this));
		request.setParam(param);
		
		request.setCache(true);
		request.setCacheKey(RequestCacheKeyHelper.generateAccountInfoCacheKey(param));

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {
				UserResult result = (UserResult) data.getResult();
				user = result;
				refresh();
			}
		});
	}

	public void refresh() {
		if (user != null) {
			nickName.setText(user.getUserName());
			user_info_id.setText(String.valueOf(user.getUserId()));
			user_info_phone.setText(user.getMobile());
			user_info_credit.setText(String.valueOf(user.getUserCredit()));
			BizUtils.showAvatar(this, account_avatar, user.getAvatar());
		}
	}

	public void btn_back(View v) {
		this.finish();
	}

	public void btn_back_send(View v) {
		this.finish();
	}

	public void updateNickName(String name) {
		nickName.setText(name);
	}
	public void userinfoExit(View v) {
		Intent intent = new Intent();
		intent.setClass(AccountInfoActivity.this, AccountEscActivity.class);
		startActivity(intent);
	}
	public void toUpdatePassword(View v){
		Intent intent = new Intent();
		intent.setClass(AccountInfoActivity.this, UpdatePasswordActivity.class);
		startActivity(intent);
	}
	/*
	 * public void head_xiaohei(View v) { Intent intent = new Intent();
	 * intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
	 * startActivity(intent); }
	 */
}