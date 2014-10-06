package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xinfan.blueblue.activity.contact.ContactInfoMenu;
import com.xinfan.blueblue.activity.send.SendMessageActivity;
import com.xinfan.blueblue.vo.ContactVo;

public class ContactInfoActivity extends Activity {

	public static ContactInfoActivity instance;

	public ContactInfoMenu menu;

	public ContactVo vo;

	public TextView accountView, creditView, usernameView, markView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_info);

		Bundle data = this.getIntent().getExtras();
		vo = (ContactVo) data.getSerializable("vo");

		instance = this;

		accountView = (TextView) this.findViewById(R.id.contact_account);
		creditView = (TextView) this.findViewById(R.id.contact_credit);
		usernameView = (TextView) this.findViewById(R.id.contact_username);
		markView = (TextView) this.findViewById(R.id.contact_mark);

		accountView.setText(vo.getAccountId());
		creditView.setText(vo.getCredit());
		usernameView.setText(vo.getUsername());
		markView.setText(vo.getMark());
	}

	public void btn_back(View v) {
		this.finish();
	}

	public void btn_back_send(View v) {
		this.finish();
	}

	public void send_private_message(View v) {
		Intent intent = new Intent();
		intent.setClass(ContactInfoActivity.this, SendMessageActivity.class);

		startActivity(intent);
	}

	public void onClickMenu(View v) {
		menu = new ContactInfoMenu(this);

		menu.showAtLocation(findViewById(R.id.contact_menu), Gravity.TOP | Gravity.RIGHT, 10, 150);
	}
	
	public void updateMark(String mark){
		markView.setText(mark);
		vo.setMark(mark);
		
		ContactVo update = MainActivity.instance.listview3.list.get(vo.getIndex());
		update.setMark(mark);
	}

	/*
	 * public void head_xiaohei(View v) { Intent intent = new Intent();
	 * intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
	 * startActivity(intent); }
	 */
}