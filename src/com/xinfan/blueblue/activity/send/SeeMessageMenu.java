package com.xinfan.blueblue.activity.send;

import android.app.Activity;
import android.content.Context;
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
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.SendMessageParam;

public class SeeMessageMenu extends PopupWindow {

	private View btnDel;
	private View btnResend;
	private View mMenuView;

	public Activity context;

	public SeeMessageMenu(final Activity context) {
		super(context);

		this.context = context;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.see_message_menu, null);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		btnDel = (View) mMenuView.findViewById(R.id.btn_see_message_menu_del);
		btnResend = (View) mMenuView.findViewById(R.id.btn_see_message_menu_resend);

		btnResend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SeeMessageMenu.this.onClickResend(v);
				SeeMessageMenu.this.dismiss();
			}
		});

		btnDel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SeeMessageMenu.this.onClickDelete(v);
				SeeMessageMenu.this.dismiss();
			}
		});

		this.setContentView(mMenuView);
		this.setWidth(w / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);

	}

	public void onClickDelete(View v) {

		SendMessageSummaryVO vo = SeeMessageActivity.instance.vo;

		Request request = new Request(FunIdConstants.DELETE_MESSAGE);
		SendMessageParam param = new SendMessageParam();
		param.setUserId(LoginUserContext.getUserId(this.context));
		param.setMsgId(vo.getMsgId());

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this.context, request, new ObserverCallBack() {

			public void call(Request data) {
				ToastUtil.showMessage(SeeMessageActivity.instance, "删除成功");
				MainActivity.instance.listview2.refresh();
				SeeMessageActivity.instance.finish();
			}
		});
	}

	public void onClickResend(View v) {

		SendMessageSummaryVO vo = SeeMessageActivity.instance.vo;

		Request request = new Request(FunIdConstants.RESEND_MESSAGE);
		SendMessageParam param = new SendMessageParam();
		param.setUserId(LoginUserContext.getUserId(this.context));
		param.setMsgId(vo.getMsgId());

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this.context, request, new ObserverCallBack() {

			public void call(Request data) {
				ToastUtil.showMessage(SeeMessageActivity.instance, "重新发送成功");
				MainActivity.instance.listview2.refresh();
				SeeMessageActivity.instance.load();
			}
		});
	}
}
