package com.xinfan.blueblue.activity.send;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.xinfan.blueblue.activity.ContactInfoActivity;
import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.ToastUtil;

public class SeeMessageMenu extends PopupWindow {

	private View btnDel;
	private View btnResend;
	private View mMenuView;

	public SeeMessageMenu(final Activity context) {
		super(context);
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

		SendMessageVo vo = SeeMessageActivity.instance.vo;

		MainActivity.instance.listview2.list.remove(vo.getIndex());
		MainActivity.instance.listview2.ad.notifyDataSetChanged();

		ToastUtil.showMessage(v.getContext(), "删除成功");
		SeeMessageActivity.instance.finish();
	}

	public void onClickResend(View v) {
		ToastUtil.showMessage(v.getContext(), "重新发送成功");
	}
}
