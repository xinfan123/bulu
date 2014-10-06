package com.xinfan.blueblue.activity;

import com.xinfan.blueblue.util.ToastUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateNickname extends Activity {
	private EditText mNewNickname; 
	private String newNickname;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_nickname);

		mNewNickname = (EditText) findViewById(R.id.new_nickname_edit);



	}

	public void set_nickname(View v) {
		newNickname=mNewNickname.getText().toString();
		if (newNickname==null||newNickname.equals("")){
			ToastUtil.showMessage(UpdateNickname.this, "昵称不能为空！");
		} 																								
		else {
			UserInfoActivity.instance.updateNickName(newNickname);
			ToastUtil.showMessage(this, "修改成功！");
			this.finish();
		}
	}

	public void nickname_back(View v) {
		this.finish();
	}


}
