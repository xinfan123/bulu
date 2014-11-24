package com.xinfan.blueblue.activity.send;

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
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.DateUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.SendMessageParam;
import com.xinfan.msgbox.http.service.vo.result.MessageResult;
import com.xinfan.msgbox.http.service.vo.result.MessageVO;

public class SeeMessageActivity extends BaseActivity implements OnClickListener {

	public TextView see_time_select_label;

	public TextView see_area_select_label;

	public TextView see_money_select_label;

	public TextView see_message_content_edit;

	public TextView see_message_more_edit;

	public TextView see_message_publishtime;

	public TextView see_message_rev_count;

	public TextView see_message_read_count,see_message_refresh_count;

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
		see_message_publishtime = (TextView) findViewById(R.id.see_message_publishtime);
		see_money_select_label = (TextView) findViewById(R.id.see_money_select_label);

		see_message_rev_count = (TextView) findViewById(R.id.see_message_rev_count);
		see_message_read_count = (TextView) findViewById(R.id.see_message_read_count);
		see_message_lasttime = (TextView) findViewById(R.id.see_message_lasttime);
		see_message_refresh_count = (TextView) findViewById(R.id.see_message_refresh_count);
		

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
		request.setCache(true);
		request.setCacheKey(RequestCacheKeyHelper.generateSeeSendMessageCacheKey(param));

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				MessageResult result = (MessageResult) data.getResult();

				MessageVO messageVo = result.getMessage();

				show(messageVo);
			}
		});
	}

	public void show(MessageVO messageVo) {
		View see_message_more_edit_layout = this.findViewById(R.id.see_message_more_edit_layout);
		if (messageVo.getContext() == null || messageVo.getContext().length() <= 1) {
			see_message_more_edit_layout.setVisibility(View.GONE);
		} else {
			see_message_more_edit_layout.setVisibility(View.VISIBLE);
			see_message_more_edit.setText(messageVo.getContext());
		}

		see_message_content_edit.setText(messageVo.getTitle());

		see_time_select_label.setText(String.valueOf(messageVo.getDurationTime()));
		see_area_select_label.setText((messageVo.getSendType() == 1 ? "附近" : "本市"));

		see_message_publishtime.setText("发布时间：" + DateUtil.formateLong(messageVo.getRefreshTime()));
		see_money_select_label.setText(String.valueOf(messageVo.getAmount() == null ? 0 : messageVo.getAmount()));
		see_message_read_count.setText("阅读人数：" + String.valueOf(messageVo.getReadCount() == null ? 0 : messageVo.getReadCount()));
		see_message_rev_count.setText("推送人数：" + String.valueOf(messageVo.getPublishCount() == null ? 0 : messageVo.getPublishCount()));
		see_message_refresh_count.setText("刷新次数："+messageVo.getRefreshCount());

		String[] times = BizUtils.calUsefulTime(messageVo.getRefreshTime(), messageVo.getDurationTime());
		if ("1".equals(times[0])) {
			see_message_lasttime.setText("剩余时间：" + times[1]);
		} else {
			see_message_lasttime.setText("剩余时间：" + times[1]);
		}

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