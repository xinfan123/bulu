package com.xinfan.blueblue.activity.send;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.DateUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.SendMessageParam;
import com.xinfan.msgbox.http.service.vo.result.MessageResult;
import com.xinfan.msgbox.http.service.vo.result.MessageVO;

public class SeeMessageActivity extends Activity implements OnClickListener {

	public TextView see_time_select_label;

	public TextView see_area_select_label;

	public TextView see_money_select_label;

	public TextView see_message_content_edit;

	public TextView see_message_more_edit;

	public TextView see_message_credit;

	public TextView see_message_rev_count;

	public TextView see_message_read_count;

	public TextView see_message_lasttime;

	public static SeeMessageActivity instance;

	public SeeMessageMenu menu;

	public SendMessageSummaryVO vo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.see_message);
		see_message_more_edit = (TextView) findViewById(R.id.see_message_more_edit);
		see_message_content_edit = (TextView) findViewById(R.id.see_message_content_edit);

		see_time_select_label = (TextView) findViewById(R.id.see_time_select_label);
		see_area_select_label = (TextView) findViewById(R.id.see_message_area);
		see_message_credit = (TextView) findViewById(R.id.see_message_credit);
		see_money_select_label = (TextView) findViewById(R.id.see_money_select_label);

		see_message_rev_count = (TextView) findViewById(R.id.see_message_rev_count);
		see_message_read_count = (TextView) findViewById(R.id.see_message_read_count);
		see_message_lasttime = (TextView) findViewById(R.id.see_message_lasttime);

		instance = this;

		Bundle data = this.getIntent().getExtras();
		vo = (SendMessageSummaryVO) data.getSerializable("vo");

		load();
	}

	public void load() {

		Request request = new Request(FunIdConstants.GET_MESSAGE);
		SendMessageParam param = new SendMessageParam();
		param.setUserId(LoginUserContext.getUserId(this));
		param.setMsgId(vo.getMsgId());

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				MessageResult result = (MessageResult) data.getResult();

				MessageVO messageVo = result.getMessage();

				show(messageVo);
			}
		});
	}

	public void show(MessageVO messageVo) {

		if (messageVo.getContext() == null || messageVo.getContext().length() <= 1) {
			see_message_more_edit.setVisibility(View.GONE);
		} else {
			see_message_more_edit.setVisibility(View.VISIBLE);
			see_message_more_edit.setText(messageVo.getContext());
		}

		see_message_content_edit.setText(messageVo.getTitle());

		see_time_select_label.setText("有效时间（分）：" + messageVo.getDurationTime());
		see_area_select_label.setText("地址范围：" + (messageVo.getSendType() == 1 ? "附近" : "本市"));

		see_message_credit.setText("发布时间：" + DateUtil.formateLong(messageVo.getCreateTime()));
		see_money_select_label.setText("有偿金额：" + (messageVo.getAmount() == null ? 0 : messageVo.getAmount()));
		see_message_read_count.setText("阅读数：" + (messageVo.getReadCount() == null ? 0 : messageVo.getReadCount()));
		see_message_rev_count.setText("推送数:" + (messageVo.getPublishCount() == null ? 0 : messageVo.getPublishCount()));

		see_message_lasttime.setText(BizUtils.calUsefulTime(messageVo.getCreateTime(), messageVo.getDurationTime()));
	}

	public void send_msg_back(View view) {
		finish();
	}

	public void onClickMenu(View v) {
		menu = new SeeMessageMenu(this);
		menu.showAtLocation(findViewById(R.id.see_message_menu), Gravity.TOP | Gravity.RIGHT, 10, 150);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
	}
}