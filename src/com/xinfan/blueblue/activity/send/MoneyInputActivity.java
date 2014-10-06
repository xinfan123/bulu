package com.xinfan.blueblue.activity.send;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.ToastUtil;

public class MoneyInputActivity extends Activity implements OnClickListener {

	public TextView write_money_yes_btn;

	public TextView write_money_input;

	public ArrayList<TimeListVo> timeList = new ArrayList<TimeListVo>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.write_message_money_input);

		write_money_yes_btn = (TextView) findViewById(R.id.write_money_yes_btn);
		write_money_input = (TextView) findViewById(R.id.write_money_input);

		write_money_yes_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String value = write_money_input.getText().toString();

				if (value.length() == 0) {
					ToastUtil.showMessage(MoneyInputActivity.this, "请填写金额大小");
					return;
				}
				SendMessageActivity.instance.updateMoney(value);
				MoneyInputActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	@Override
	public void onClick(View arg0) {

	}

}
