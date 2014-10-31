package com.xinfan.blueblue.activity.rev;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;


import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.MessageRevDelParam;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

public class RevMessageMenu extends PopupWindow {

	private View rev_del_message_layout;
	private View rev_report_layout;
	private View rev_add_contact_layout;

	private View mMenuView;

	public RevMessageMenu(final Activity context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.rev_message_menu, null);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		rev_del_message_layout = (View) mMenuView.findViewById(R.id.rev_del_message_layout);
		rev_report_layout = (View) mMenuView.findViewById(R.id.rev_report_layout);

		rev_add_contact_layout = (View) mMenuView.findViewById(R.id.rev_add_contact_layout);

		this.setContentView(mMenuView);
		this.setWidth(w / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);

		rev_del_message_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onClickDelete(v);
				RevMessageMenu.this.dismiss();
			}
		});

		rev_report_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickReport(v);
				RevMessageMenu.this.dismiss();
			}
		});

		rev_add_contact_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickSaveContact(v);
				RevMessageMenu.this.dismiss();
			}
		});

	}

	public void onClickDelete(View v) {

		RevMessageSummaryVO vo = RevSeeMessageActivity.instance.vo;

		Request request = new Request(FunIdConstants.DELETE_REV_MESSAGE);
		MessageRevDelParam param = new MessageRevDelParam();

		param.setPublishId(vo.getPublishId());
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(RevSeeMessageActivity.instance, request, new RequestSucessCallBack() {

			public void call(Request data) {
				ToastUtil.showMessage(RevSeeMessageActivity.instance, data.getResult().getMsg());
				RevSeeMessageActivity.instance.finish();
				MainActivity.instance.listview1.refresh();
			}
		});

	}

	public void onClickReport(View v) {
		Intent intent = new Intent(RevSeeMessageActivity.instance, RevReport.class);
		v.getContext().startActivity(intent);
	}

	public void onClickSaveContact(View v) {
		Intent intent = new Intent(RevSeeMessageActivity.instance, RevAddContactActivity.class);
		v.getContext().startActivity(intent);
	}
}
