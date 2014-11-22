package com.xinfan.blueblue.activity.send;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.location.LocationEntity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.SendMessageParam;

public class SendMessageActivity extends BaseActivity implements OnClickListener {

	public Button message_more_btn;

	public TextView send_message_btn;

	public View time_select_layout;

	public View area_select_layout;

	public View money_select_layout;

	public TextView time_select_label;

	public TextView area_select_label;

	public TextView money_select_label;

	public EditText message_content_edit;

	public EditText message_more_edit;

	public TextView message_content_edit_count, message_more_edit_count;

	public static SendMessageActivity instance;

	public RelativeLayout message_content_edit_layout, message_more_edit_layout;

	LocationEntity location;

	int num = 140;// 限制的最大字数　
	
	int morenum = 500;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_message);
		message_more_btn = (Button) findViewById(R.id.message_more_btn);
		message_more_edit = (EditText) findViewById(R.id.message_more_edit);
		message_content_edit = (EditText) findViewById(R.id.message_content_edit);
		time_select_layout = (View) findViewById(R.id.time_select_layout);
		area_select_layout = (View) findViewById(R.id.area_select_layout);
		money_select_layout = (View) findViewById(R.id.money_select_layout);

		time_select_label = (TextView) findViewById(R.id.time_select_label);
		area_select_label = (TextView) findViewById(R.id.area_select_label);
		send_message_btn = (TextView) findViewById(R.id.send_message_btn);

		money_select_label = (TextView) findViewById(R.id.money_select_label);
		message_content_edit_count = (TextView) findViewById(R.id.message_content_edit_count);
		message_more_edit_count = (TextView) findViewById(R.id.message_more_edit_count);

		message_content_edit_layout = (RelativeLayout) findViewById(R.id.message_content_edit_layout);
		message_more_edit_layout = (RelativeLayout) findViewById(R.id.message_more_edit_layout);

		time_select_layout.setOnClickListener(this);
		area_select_layout.setOnClickListener(this);
		money_select_layout.setOnClickListener(this);
		send_message_btn.setOnClickListener(this);

		instance = this;

		initMoreBtn();

		initWordLimit();

		location();
	}

	public void initMoreBtn() {
		message_more_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				int vis = message_more_edit.getVisibility();
				if (vis == View.GONE) {
					message_more_edit_layout.setVisibility(View.VISIBLE);
					message_more_btn.setText("关闭更多");
				} else {
					message_more_edit_layout.setVisibility(View.GONE);
					message_more_btn.setText("更多输入");
				}

			}
		});
	}

	public void initWordLimit() {

		this.message_content_edit.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				temp = s;
			}

			public void afterTextChanged(Editable s) {
				int number = BizUtils.getWordCount(temp.toString());
				message_content_edit_count.setText("" + number + "/" + morenum);
				selectionStart = message_content_edit.getSelectionStart();
				selectionEnd = message_content_edit.getSelectionEnd();
				if (BizUtils.getWordCount(temp.toString()) > morenum) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					message_content_edit.setText(s);
					message_content_edit.setSelection(tempSelection);// 设置光标在最后
				}
			}
		});

		message_more_edit_count.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				temp = s;
			}

			public void afterTextChanged(Editable s) {
				int number = BizUtils.getWordCount(temp.toString());
				message_more_edit_count.setText("" + number + "/" + num);
				selectionStart = message_more_edit.getSelectionStart();
				selectionEnd = message_more_edit.getSelectionEnd();
				if (BizUtils.getWordCount(temp.toString()) > num) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					message_more_edit.setText(s);
					message_more_edit.setSelection(tempSelection);// 设置光标在最后
				}
			}
		});
	}

	public void location() {
		Intent intent = new Intent();
		intent.setClass(SendMessageActivity.this, SendMessageLocationActivity.class);
		startActivity(intent);
	}

	public void send_msg_back(View view) {
		finish();
	}

	public void updateTimeSelect(TimeListVo vo) {
		time_select_label.setText(vo.getText());
		time_select_label.setTag(vo);
	}

	public void updateAreaSelect(AreaListVo vo) {
		area_select_label.setText(vo.getText());
		area_select_label.setTag(vo);
	}

	public void updateMoney(String value) {
		money_select_label.setText(value);
		money_select_label.setTag(value);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.time_select_layout:

			Intent intent = new Intent();
			intent.setClass(this, TimeSelectActivity.class);
			startActivity(intent);

			break;
		case R.id.area_select_layout:

			Intent intent2 = new Intent();
			intent2.setClass(this, AreaSelectActivity.class);
			startActivity(intent2);

			break;
		case R.id.money_select_layout:

			Intent intent3 = new Intent();
			intent3.setClass(this, MoneyInputActivity.class);
			startActivity(intent3);

			break;
		case R.id.send_message_btn:
			sendMessage();
			break;

		default:
			break;
		}
	}

	public void sendMessage() {

		String title = message_content_edit.getText().toString();
		String content = message_more_edit.getText().toString();

		TimeListVo timeListVo = (TimeListVo) time_select_label.getTag();

		int time = 30;
		if (timeListVo != null) {
			time = Integer.parseInt(timeListVo.getId());
		}

		AreaListVo areaListVo = (AreaListVo) area_select_label.getTag();

		int area = 1;
		if (areaListVo != null) {
			area = Integer.parseInt(areaListVo.getId());
		}

		String money = money_select_label.getText().toString();
		int amountStatus = 0;
		long amount = 0;

		if (money != null && money.length() > 0) {
			amount = Long.parseLong(money);
			amountStatus = amount > 0 ? 1 : 0;
		}

		// message.setTitle(title);
		// message.setTime(time);
		// message.setContent(content);
		// message.setArea(area);
		// message.setMoney(money);

		Request request = new Request(FunIdConstants.SEND_MESSAGE);
		SendMessageParam param = new SendMessageParam();
		param.setTitle(title);
		param.setContext(content);
		param.setCreateUserId(LoginUserContext.getUserId(this));
		param.setUserId(LoginUserContext.getUserId(this));

		param.setDurationTime(time);
		param.setAmount(amount);
		param.setAmountStatus(amountStatus);
		param.setSendType(area);
		param.setPublishType(1);

		if (location != null) {
			param.setReginCode(location.getCity());
			param.setGpsx(String.valueOf(location.getLongitude()));
			param.setGpsy(String.valueOf(location.getLatitude()));
		} else {
			param.setReginCode("长沙市");
			param.setGpsx("0");
			param.setGpsy("0");
		}

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {
				MainActivity.instance.listview2.refresh();
				// 刷新历史信息
				ToastUtil.showMessage(SendMessageActivity.this, "发送成功");
				SendMessageActivity.this.finish();
			}
		});

		// ////////////////////////////////////////////////////////////////////////////
		// MainActivity.instance.listview2.addItem(message);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}