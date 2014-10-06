package com.xinfan.blueblue.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.vo.ThemeVo;

public class ThemeSetMenu extends Activity {
	// private MyDialog dialog;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themeset_menu);
		// dialog=new MyDialog(this);
		layout = (LinearLayout) findViewById(R.id.theme_menu_id);
/*		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "退出", Toast.LENGTH_SHORT).show();
			}
		});*/

		TextView delBtn = (TextView) findViewById(R.id.btn_delete);
		delBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delete(v);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void delete(View v) {

		String id = ThemeSetMenu.this.getIntent().getStringExtra("themeid");
		List list = ThemeSetActivity.instance.list;
		for (int i = 0; i < list.size(); i++) {
			ThemeVo vo = (ThemeVo) list.get(i);
			if (vo.getId().equals(id)) {
				list.remove(vo);
				ThemeSetActivity.instance.adapter.notifyDataSetChanged();
				ThemeSetActivity.instance.reCountTip();
				ToastUtil.showMessage(v.getContext(), "删除主题成功");
				break;
			}
		}
		this.finish();
	}

}
