package com.xinfan.blueblue.activity.send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.ToastUtil;

public class SendMessageActivity extends Activity implements OnClickListener {

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

	public static SendMessageActivity instance;

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

		message_more_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				int vis = message_more_edit.getVisibility();
				if (vis == View.GONE) {
					message_more_edit.setVisibility(View.VISIBLE);
					message_more_btn.setText("关闭更多");
				} else {
					message_more_edit.setVisibility(View.GONE);
					message_more_btn.setText("更多输入");
				}
			}
		});
		time_select_layout.setOnClickListener(this);
		area_select_layout.setOnClickListener(this);
		money_select_layout.setOnClickListener(this);
		send_message_btn.setOnClickListener(this);

		instance = this;
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
		SendMessageVo message = new SendMessageVo();

		String title = message_content_edit.getText().toString();
		String content = message_more_edit.getText().toString();
		String time = time_select_label.getText().toString();
		String area = area_select_label.getText().toString();
		String money = money_select_label.getText().toString();

		message.setTitle(title);
		message.setTime(time);
		message.setContent(content);
		message.setArea(area);
		message.setMoney(money);

		ToastUtil.showMessage(this, "发送成功");

		MainActivity.instance.listview2.addItem(message);

		this.finish();

	}

}