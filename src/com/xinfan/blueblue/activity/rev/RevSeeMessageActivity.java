package com.xinfan.blueblue.activity.rev;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.dao.RequestCacheKeyHelper;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.DateUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.MessageRevParam;
import com.xinfan.msgbox.http.service.vo.result.MessageRevDetailVO;
import com.xinfan.msgbox.http.service.vo.result.MessageRevResult;

public class RevSeeMessageActivity extends BaseActivity implements OnClickListener {

	public TextView see_time_select_label;

	public TextView see_area_select_label;

	public TextView see_money_select_label;

	public TextView see_message_content_edit;

	public TextView see_message_more_edit;

	public TextView see_message_credit;

	public TextView rev_message_send_username;

	public TextView rev_message_send_time;

	public static RevSeeMessageActivity instance;

	public RevMessageMenu menu;

	public RevMessageSummaryVO vo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rev_message);
		see_message_more_edit = (TextView) findViewById(R.id.see_message_more_edit);
		see_message_content_edit = (TextView) findViewById(R.id.see_message_content_edit);

		see_time_select_label = (TextView) findViewById(R.id.see_time_select_label);
		see_area_select_label = (TextView) findViewById(R.id.see_message_area);
		see_message_credit = (TextView) findViewById(R.id.see_message_credit);
		see_money_select_label = (TextView) findViewById(R.id.see_money_select_label);
		rev_message_send_username = (TextView) findViewById(R.id.rev_message_send_username);
		rev_message_send_time = (TextView) findViewById(R.id.rev_message_send_time);

		instance = this;
		Bundle data = this.getIntent().getExtras();

		vo = (RevMessageSummaryVO) data.getSerializable("vo");
		
		load();
	}

	public void show(MessageRevDetailVO messageVo) {

		if (messageVo.getContext() == null || messageVo.getContext().length() <= 1) {
			see_message_more_edit.setVisibility(View.GONE);
		} else {
			see_message_more_edit.setVisibility(View.VISIBLE);
			see_message_more_edit.setText(messageVo.getContext());
		}

		see_message_content_edit.setText(messageVo.getTitle());

		String time = BizUtils.calUsefulTime(messageVo.getRefreshTime(), messageVo.getDurationTime());

		see_time_select_label.setText(time);
		see_area_select_label.setText(messageVo.getSendType() == 1 ? "附近" : "本市");
		see_message_credit.setText("信用值：" + String.valueOf(messageVo.getSendUserCredit()));
		see_money_select_label.setText("有偿：" + String.valueOf(messageVo.getAmount()));
		rev_message_send_username.setText(messageVo.getSendUserName());
		rev_message_send_time.setText(DateUtil.formateLong(messageVo.getRefreshTime()));
	}

	public void load() {

		Request request = new Request(FunIdConstants.GET_REV_MESSAGE);
		MessageRevParam param = new MessageRevParam();
		param.setUserId(LoginUserContext.getUserId(this));
		param.setMsgId(vo.getMsgId());

		request.setParam(param);
		request.setCache(true);
		request.setCacheKey(RequestCacheKeyHelper.generateSeeRevMessageCacheKey(param));
		

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				MessageRevResult result = (MessageRevResult) data.getResult();

				MessageRevDetailVO messageVo = result.getMessage();

				show(messageVo);
			}
		});
	}

	public void send_msg_back(View view) {
		finish();
	}

	public void onClickMenu(View v) {
		menu = new RevMessageMenu(this);
		menu.showAtLocation(findViewById(R.id.rev_message_menu), Gravity.TOP | Gravity.RIGHT, 10, 150);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
	}
}