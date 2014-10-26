package com.xinfan.blueblue.activity.rev;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserLinkmanParam;

public class RevAddContactActivity extends BaseActivity implements OnClickListener {

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

				RevMessageSummaryVO messageVo = RevSeeMessageActivity.instance.vo;

				Request request = new Request(FunIdConstants.ADD_USER_LINKMAN);
				UserLinkmanParam param = new UserLinkmanParam();

				param.setLinkRemark(value);
				param.setLinkUserId(messageVo.getSendUserid());
				param.setUserId(messageVo.getReceivedUserid());

				request.setParam(param);

				AnsynHttpRequest.requestSimpleByPost(RevSeeMessageActivity.instance, request, new RequestSucessCallBack() {

					public void call(Request data) {
						ToastUtil.showMessage(RevSeeMessageActivity.instance, data.getResult().getMsg());
						MainActivity.instance.listview3.refresh();
					}
				});

				RevAddContactActivity.this.finish();
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
