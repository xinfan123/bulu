package com.xinfan.blueblue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.contact.ContactInfoMenu;
import com.xinfan.blueblue.activity.send.WriteMessageActivity;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.vo.LinkmanVo;

public class ContactInfoActivity extends BaseActivity {

	public static ContactInfoActivity instance;

	public ContactInfoMenu menu;

	public LinkmanVo vo;

	public TextView accountView, creditView, usernameView, markView;
	
	public ImageView photo_image;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_info);

		Bundle data = this.getIntent().getExtras();
		vo = (LinkmanVo) data.getSerializable("vo");

		instance = this;

		accountView = (TextView) this.findViewById(R.id.contact_account);
		creditView = (TextView) this.findViewById(R.id.contact_credit);
		usernameView = (TextView) this.findViewById(R.id.contact_username);
		markView = (TextView) this.findViewById(R.id.contact_mark);
		photo_image = (ImageView)this.findViewById(R.id.photo_image);
		
		
		BizUtils.showAvatar(this, photo_image, vo.getLinkAvatar());
		usernameView.setText(vo.getLinkUserName());
		accountView.setText(String.valueOf(vo.getLinkUserId()));
		markView.setText(vo.getLinkRemark());
		creditView.setText(String.valueOf(vo.getLinkUserCredit()));
		
		//accountView.setText(vo.getLinkUserId());
		//creditView.setText("0");
		//usernameView.setText(vo.get);
		//markView.setText(vo.getMark());
	}

	public void btn_back(View v) {
		this.finish();
	}

	public void btn_back_send(View v) {
		this.finish();
	}

	public void send_private_message(View v) {
		Intent intent = new Intent();
		intent.setClass(ContactInfoActivity.this, WriteMessageActivity.class);

		startActivity(intent);
	}

	public void onClickMenu(View v) {
		menu = new ContactInfoMenu(this);

		menu.showAtLocation(findViewById(R.id.contact_menu), Gravity.TOP | Gravity.RIGHT, 10, 150);
	}
	
	public void updateMark(String mark){
		markView.setText(mark);
		//vo.setMark(mark);
		
		//ContactVo update = MainActivity.instance.listview3.list.get(vo.getIndex());
		//update.setMark(mark);
	}

	/*
	 * public void head_xiaohei(View v) { Intent intent = new Intent();
	 * intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
	 * startActivity(intent); }
	 */
}