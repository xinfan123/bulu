package com.xinfan.blueblue.activity.contact;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.ContactInfoActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.LinkmanVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserLinkmanParam;

public class ContactModifyRemarkActivity extends BaseActivity implements OnClickListener {

	public TextView contact_add_mark_yes_btn;

	public TextView contact_add_mark_text;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.contact_modify_remark);

		contact_add_mark_yes_btn = (TextView) findViewById(R.id.contact_add_mark_yes_btn);
		contact_add_mark_text = (TextView) findViewById(R.id.contact_add_mark_text);

		LinkmanVo vo = ContactInfoActivity.instance.vo;
		contact_add_mark_text.setText(vo.getLinkRemark());

		contact_add_mark_yes_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String value = contact_add_mark_text.getText().toString();

				if (value.length() == 0) {
					ToastUtil.showMessage(ContactModifyRemarkActivity.this, "请填写备注信息");
					return;
				}

				if (value.length() >= 20) {
					ToastUtil.showMessage(ContactModifyRemarkActivity.this, "备注信息太长");
					return;
				}

				LinkmanVo vo = ContactInfoActivity.instance.vo;
				ContactInfoActivity.instance.updateMark(value);
				ToastUtil.showMessage(v.getContext(), "修改成功");

				Request request = new Request(FunIdConstants.UPDATE_USER_LINKMAN);

				UserLinkmanParam param = new UserLinkmanParam();
				param.setLinkUserId(vo.getLinkUserId());
				param.setUserId(vo.getUserId());
				param.setLinkRemark(value);

				request.setParam(param);
				request.setShowDialog(false);

				AnsynHttpRequest.requestSimpleByPost(ContactInfoActivity.instance, request, new RequestSucessCallBack() {
					public void call(Request data) {
					}
				});

				ContactModifyRemarkActivity.this.finish();
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
