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
import com.xinfan.blueblue.util.ToastUtil;

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

		RevMessageVo vo = RevSeeMessageActivity.instance.vo;

		MainActivity.instance.listview1.list.remove(vo.getIndex());
		MainActivity.instance.listview1.ad.notifyDataSetChanged();

		ToastUtil.showMessage(v.getContext(), "删除成功");
		RevSeeMessageActivity.instance.finish();
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
