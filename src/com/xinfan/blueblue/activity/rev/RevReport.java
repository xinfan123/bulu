package com.xinfan.blueblue.activity.rev;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.ToastUtil;

public class RevReport extends Activity {
	private EditText rev_report_title;// 文本编辑框
	private Button rev_report_btn;
	private Spinner rev_report_type;

	private static String[] m = { "黄色", "暴力", "违法", "其它" };

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rev_report);

		rev_report_title = (EditText) findViewById(R.id.rev_report_title);
		rev_report_btn = (Button) findViewById(R.id.rev_report_btn);
		rev_report_type = (Spinner) findViewById(R.id.rev_report_type);

		m = this.getResources().getStringArray(R.array.rev_report_list);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		rev_report_type.setAdapter(adapter);
		rev_report_type.setPrompt(this.getResources().getString(R.string.rev_report_tip));
	}

	public void submit(View v) {
		Object id = rev_report_type.getSelectedItem();
		String typeStr = m[rev_report_type.getSelectedItemPosition()];

		String title = rev_report_title.getText().toString();
		if (title == null || title.length() == 0) {
			ToastUtil.showMessage(this, "请输入举报内容");
			return;
		}

		if (title.length() > 100) {
			ToastUtil.showMessage(this, "举报内容过长");
			return;
		}

		RevMessageVo vo = RevSeeMessageActivity.instance.vo;

		ToastUtil.showMessage(this, "举报成功：" + typeStr);
		this.finish();
	}

	public void back(View v) { // 返回
		this.finish();
	}

}
