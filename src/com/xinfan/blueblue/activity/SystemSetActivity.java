package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.activity.context.SystemSetContext;
import com.xinfan.blueblue.activity.context.VersionManager;
import com.xinfan.blueblue.activity.systemset.AboutUs;
import com.xinfan.blueblue.activity.systemset.MessageNumSelectActivity;
import com.xinfan.blueblue.activity.systemset.PaidSet;
import com.xinfan.blueblue.activity.systemset.ReputationSet;
import com.xinfan.blueblue.activity.systemset.SimilaritySet;
import com.xinfan.blueblue.dao.RequestCacheKeyHelper;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.request.ShareSystemSet;
import com.xinfan.blueblue.util.NumToChina;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.BaseParam;
import com.xinfan.msgbox.http.service.vo.param.UserSetParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;
import com.xinfan.msgbox.http.service.vo.result.UserResult;
import com.xinfan.msgbox.http.service.vo.result.UserSetResult;

public class SystemSetActivity extends Activity {
	public static SystemSetActivity instance;
	private CheckBox messageNoticeBtn;// 震动
	private CheckBox voiceBtn;// 震动
	private CheckBox vibrateBtn;// 震动
	private TextView receivenumText;// 消息数量
	private TextView similarityText;// 相似度
	private TextView paidText;// 相似度
	private TextView reputationText;// 相似度
	private TextView system_set_version_text;
	private Long userid;

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
		SharePreferenceUtil util = new SharePreferenceUtil(
				SystemSetActivity.this, Constants.USER_INFO);
		userid = util.getUserId();
		refresh();

		instance = this;
		// 监听是否接收消息
		messageNoticeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean is = messageNoticeBtn.isChecked();
				SystemSetContext.setNewMsgNotify(SystemSetActivity.this, is, userid);	
				SystemSetContext.setIsUpdate(SystemSetActivity.this, true, userid);	
				refresh();
			}
		});
		// 监听震动开关
		vibrateBtn.setOnClickListener(new OnClickListener() {

			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				final boolean is = vibrateBtn.isChecked();
				SystemSetContext.setVibrate(SystemSetActivity.this, is, userid);	
				SystemSetContext.setIsUpdate(SystemSetActivity.this, true, userid);	
				refresh();
			}
		});
		// 监听声音开关
		voiceBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean is = voiceBtn.isChecked();
				SystemSetContext.setVoice(SystemSetActivity.this, is, userid);
				SystemSetContext.setIsUpdate(SystemSetActivity.this, true, userid);	
				refresh();
			}
		});
		// 监听接收数量设置
		LinearLayout receivenum = (LinearLayout) findViewById(R.id.receivenum_sytem_layout);
		receivenum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSetActivity.this,
						MessageNumSelectActivity.class);
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
				intent.setClass(SystemSetActivity.this, SimilaritySet.class);
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
				intent.setClass(SystemSetActivity.this, PaidSet.class);
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
				intent.setClass(SystemSetActivity.this, ReputationSet.class);
				startActivity(intent);
			}
		});
		// 监听系统升级
		LinearLayout goupSystem = (LinearLayout) findViewById(R.id.goup_system_layout);
		goupSystem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				VersionManager manager = new VersionManager(
						SystemSetActivity.this);
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
				intent.setClass(SystemSetActivity.this, AboutUs.class);
				startActivity(intent);
			}
		});

	}

	public void refresh() {

		boolean isupdate=SystemSetContext.getIsUpdate(SystemSetActivity.this, userid); //检查个人信息更新状态
		boolean messageNotice=SystemSetContext.getNewMsgNotify(SystemSetActivity.this, userid);
		boolean voice=SystemSetContext.getVoice(SystemSetActivity.this,userid);
		boolean vibrate=SystemSetContext.getVibrate(SystemSetActivity.this, userid);
		Integer receivenum=SystemSetContext.getReceivenum(SystemSetActivity.this, userid);
		Integer similarity=SystemSetContext.getSimilarity(SystemSetActivity.this, userid);
		Integer paid=SystemSetContext.getPaid(SystemSetActivity.this,userid);
		Integer reputation=SystemSetContext.getReputation(SystemSetActivity.this, userid);
		
		messageNoticeBtn.setChecked(messageNotice);
		voiceBtn.setChecked(voice);
		vibrateBtn.setChecked(vibrate);
		receivenumText.setText(receivenum+"条");
		similarityText.setText(NumToChina.NumToChina(similarity)+"级");
		if(paid==0){
			paidText.setText("全部");
		}else{
			paidText.setText(paid+"元以上");
		}
		if(reputation==0){
		reputationText.setText("全部");
		}else{
			reputationText.setText(reputation+"星");
		}
		
		
		if(isupdate){//同步服务器
		Request request = new Request(FunIdConstants.SET_USERSET);
		UserSetParam param = new UserSetParam();
		param.setUserId(userid);
		if (messageNotice) {
			param.setNewMsgNotify(1);
		} else {
			param.setNewMsgNotify(0);
		}
		if (vibrate) {
			param.setVibrate(1);
		} else {
			param.setVibrate(0);
		}
		if (voice) {
			param.setVoice(1);
		} else {
			param.setVoice(0);
		}
		param.setMaxCount(receivenum);
		param.setSimilarLevel(similarity);
		param.setMinAmmount(paid);
		param.setMinCredit(reputation);
		request.setParam(param);

		request.setCache(true);
		request.setCacheKey(RequestCacheKeyHelper
				.generateSystemSetCacheKey(param));

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {
			
			public void call(Request data) {
				
			BaseResult result = (BaseResult) data.getResult();
			if(result.getResult()==1){
				SystemSetContext.setIsUpdate(SystemSetActivity.this, false, userid);	
				ToastUtil.showMessage(SystemSetActivity.this,"个人设置已生效");
			}else{
				ToastUtil.showMessage(SystemSetActivity.this,result.getMsg());
			}
		
		}
				});

		}
	}

	public void SystemSetBack(View v) { // 返回
		this.finish();
	}

	

}
