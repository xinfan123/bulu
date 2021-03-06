package com.xinfan.blueblue.activity.contact;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.xinfan.blueblue.activity.ContactInfoActivity;
import com.xinfan.blueblue.activity.LinkmanListView;
import com.xinfan.blueblue.activity.LoginActivity;
import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.BeanUtils;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.LinkmanVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserLinkmanParam;
import com.xinfan.msgbox.http.service.vo.result.UserLinkmanListResult;
import com.xinfan.msgbox.http.service.vo.result.UserLinkmanResult;

public class ContactInfoMenu extends PopupWindow {

	private Button btnDel;
	private Button btnModify;
	private View mMenuView;

	private Activity context;

	public ContactInfoMenu(final Activity context) {
		super(context);

		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.contact_info_menu, null);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		btnDel = (Button) mMenuView.findViewById(R.id.btn_contact_menu_del);
		btnModify = (Button) mMenuView.findViewById(R.id.btn_contact_menu_modify);

		btnModify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContactInfoMenu.this.onClickMark(v);
				ContactInfoMenu.this.dismiss();
			}
		});

		btnDel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ContactInfoMenu.this.onClickDelete(v);
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

		LinkmanVo vo = ContactInfoActivity.instance.vo;

		Request request = new Request(FunIdConstants.DELETE_USER_LINKMAN);

		UserLinkmanParam param = new UserLinkmanParam();
		param.setLinkUserId(vo.getLinkUserId());
		param.setUserId(vo.getUserId());

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(ContactInfoActivity.instance, request, new RequestSucessCallBack() {
			public void call(Request data) {
				MainActivity.instance.listview3.refresh();
				ToastUtil.showMessage(ContactInfoActivity.instance, "删除成功");
				ContactInfoActivity.instance.finish();
			}
		});
	}

	public void onClickMark(final View v) {
		Intent intent = new Intent();
		intent.setClass(this.context, ContactModifyRemarkActivity.class);
		context.startActivity(intent);
		this.dismiss();
	}
}
