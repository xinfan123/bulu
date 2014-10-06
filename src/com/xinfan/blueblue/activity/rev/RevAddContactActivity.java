package com.xinfan.blueblue.activity.rev;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.ContactVo;

public class RevAddContactActivity extends Activity implements OnClickListener {

	public TextView rev_add_contact_yes_btn;

	public TextView rev_add_contact_mark_text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.rev_add_contact);

		rev_add_contact_yes_btn = (TextView) findViewById(R.id.rev_add_contact_yes_btn);
		rev_add_contact_mark_text = (TextView) findViewById(R.id.rev_add_contact_mark_text);

		rev_add_contact_yes_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String value = rev_add_contact_mark_text.getText().toString();

				if (value.length() == 0) {
					ToastUtil.showMessage(RevAddContactActivity.this, "请填写备注信息");
					return;
				}

				if (value.length() >= 20) {
					ToastUtil.showMessage(RevAddContactActivity.this, "备注信息太长");
					return;
				}

				RevMessageVo messageVo = RevSeeMessageActivity.instance.vo;

				ContactVo vo = new ContactVo();
				vo.setMark(value);

				vo.setAccountId("2");
				vo.setIndex(0);

				MainActivity.instance.listview3.list.add(vo);
				MainActivity.instance.listview3.ad.notifyDataSetChanged();

				RevAddContactActivity.this.finish();

				ToastUtil.showMessage(RevAddContactActivity.this, "添加联系人成功");

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
