package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.activity.context.VersionManager;
import com.xinfan.blueblue.activity.systemset.AboutUs;
import com.xinfan.blueblue.activity.systemset.MessageNumSelectActivity;
import com.xinfan.blueblue.activity.systemset.PaidSet;
import com.xinfan.blueblue.activity.systemset.ReputationSet;
import com.xinfan.blueblue.activity.systemset.SimilaritySet;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSetParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class SystemSet extends Activity {
	public static SystemSet instance;
	private CheckBox messageNoticeBtn;// 震动
	private CheckBox voiceBtn;// 震动
	private CheckBox vibrateBtn;// 震动
	private TextView receivenumText;// 消息数量
	private TextView similarityText;// 相似度
	private TextView paidText;// 相似度
	private TextView reputationText;// 相似度
	private TextView system_set_version_text;

	// private String ComplainText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.system_set);
		messageNoticeBtn = (CheckBox) findViewById(R.id.system_messageNotice_btn);
		voiceBtn = (CheckBox) findViewById(R.id.System_voice_btn);
		vibrateBtn = (CheckBox) findViewById(R.id.system_vibrate_btn);
		receivenumText = (TextView) findViewById(R.id.receivenum_sytem_tv);
		similarityText = (TextView) findViewById(R.id.similarity_sytem_tv);
		paidText = (TextView) findViewById(R.id.paid_sytem_tv);
		reputationText = (TextView) findViewById(R.id.reputation_sytem_tv);
		system_set_version_text = (TextView) findViewById(R.id.system_set_version_text);

		system_set_version_text.setText(SystemConfigContext.getVersion(this));

		// 监听是否接收消息
		messageNoticeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean is = messageNoticeBtn.isChecked();
				SharePreferenceUtil util = new SharePreferenceUtil(SystemSet.this, Constants.USER_INFO);
				Long userId = util.getUserId();

				Request request = new Request(FunIdConstants.SET_USERSET);
				UserSetParam param = new UserSetParam();
				if (is) {
					param.setNewMsgNotify(1);
				} else {
					param.setNewMsgNotify(0);
				}
				param.setUserId(userId);
				request.setParam(param);
				AnsynHttpRequest.requestSimpleByPost(SystemSet.this, request, new RequestSucessCallBack() {

					public void call(Request data) {

						BaseResult result = (BaseResult) data.getResult();

						if (result.getResult() == 1) {

							ToastUtil.showMessage(SystemSet.this, result.getMsg());

							messageNoticeBtn.setChecked(is);

						} else {
							ToastUtil.showMessage(SystemSet.this, result.getMsg());
						}

					}
				});
			}
		});
		// 监听声音开关
		vibrateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean is = vibrateBtn.isChecked();
				SharePreferenceUtil util = new SharePreferenceUtil(SystemSet.this, Constants.USER_INFO);
				Long userId = util.getUserId();

				Request request = new Request(FunIdConstants.SET_USERSET);
				UserSetParam param = new UserSetParam();
				if (is) {
					param.setVibrate(1);
				} else {
					param.setVibrate(0);
				}
				param.setUserId(userId);
				request.setParam(param);
				AnsynHttpRequest.requestSimpleByPost(SystemSet.this, request, new RequestSucessCallBack() {

					public void call(Request data) {

						BaseResult result = (BaseResult) data.getResult();

						if (result.getResult() == 1) {

							ToastUtil.showMessage(SystemSet.this, result.getMsg());

							vibrateBtn.setChecked(is);

						} else {
							ToastUtil.showMessage(SystemSet.this, result.getMsg());
						}

					}
				});
			}
		});
		// 监听震动开关
		voiceBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean is = voiceBtn.isChecked();
				SharePreferenceUtil util = new SharePreferenceUtil(SystemSet.this, Constants.USER_INFO);
				Long userId = util.getUserId();

				Request request = new Request(FunIdConstants.SET_USERSET);
				UserSetParam param = new UserSetParam();
				if (is) {
					param.setVoice(1);
				} else {
					param.setVoice(0);
				}
				param.setUserId(userId);
				request.setParam(param);
				AnsynHttpRequest.requestSimpleByPost(SystemSet.this, request, new RequestSucessCallBack() {

					public void call(Request data) {

						BaseResult result = (BaseResult) data.getResult();

						if (result.getResult() == 1) {

							ToastUtil.showMessage(SystemSet.this, result.getMsg());

							voiceBtn.setChecked(is);

						} else {
							ToastUtil.showMessage(SystemSet.this, result.getMsg());
						}

					}
				});
			}
		});
		// 监听接收数量设置
		LinearLayout receivenum = (LinearLayout) findViewById(R.id.receivenum_sytem_layout);
		receivenum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, MessageNumSelectActivity.class);
				startActivity(intent);
			}
		});
		// 监听消息相似度
		LinearLayout similarity = (LinearLayout) findViewById(R.id.similarity_sytem_layout);
		similarity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, SimilaritySet.class);
				startActivity(intent);
			}
		});
		// 监听有偿设置
		LinearLayout paid = (LinearLayout) findViewById(R.id.paid_sytem_layout);
		paid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, PaidSet.class);
				startActivity(intent);
			}
		});
		// 监听信誉等级
		LinearLayout reputation = (LinearLayout) findViewById(R.id.reputation_sytem_layout);
		reputation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, ReputationSet.class);
				startActivity(intent);
			}
		});
		// 监听系统升级
		LinearLayout goupSystem = (LinearLayout) findViewById(R.id.goup_system_layout);
		goupSystem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				VersionManager manager = new VersionManager(SystemSet.this);
				manager.checkManualUpdate();

			}
		});
		// 监听关于我们
		LinearLayout aboutUs = (LinearLayout) findViewById(R.id.about_us_layout);
		aboutUs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, AboutUs.class);
				startActivity(intent);
			}
		});
		instance = this;
	}

	public void SystemSetBack(View v) { // 返回
		this.finish();
	}

	public void SetMessageNum(String name) {
		receivenumText.setText(name);
	}

	public void SetSimilarity(String name) {
		similarityText.setText(name);
	}

	public void SetPaid(String name) {
		paidText.setText(name);
	}

	public void SetReputation(String name) {
		reputationText.setText(name);
	}

}
