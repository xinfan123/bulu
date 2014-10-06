package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.send.SendMessageActivity;

public class MainActivity extends Activity implements OnViewChangeListener, OnClickListener {

	public static MainActivity instance;

	private MyScrollLayout mScrollLayout;
	private LinearLayout[] mImageViews;
	private int mViewCount;
	private int mCurSel;
	private ImageView set;
	private ImageView add;
	private ImageView send;

	private TextView liaotian;
	private TextView faxian;
	private TextView tongxunlu;

	private boolean isOpen = false;

	public MessageListView listview1;
	public SendedMessageListView listview2;
	public ContactListView listview3;

	SelectPicPopupWindow menuWindow;
	SelectAddPopupWindow menuWindow2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		instance = this;
	}

	private void init() {
		liaotian = (TextView) findViewById(R.id.liaotian);
		faxian = (TextView) findViewById(R.id.faxian);
		tongxunlu = (TextView) findViewById(R.id.tongxunlu);

		listview1 = (MessageListView) findViewById(R.id.listView1);
		listview2 = (SendedMessageListView) findViewById(R.id.listView2);
		listview3 = (ContactListView) findViewById(R.id.listView3);
		

		View footer = LayoutInflater.from(this).inflate(R.layout.footer, null);

		listview1.setContext(this);
		listview1.setFooter(footer);
		listview1.loadData();
		
		View footer2 = LayoutInflater.from(this).inflate(R.layout.footer, null);

		listview2.setContext(this);
		listview2.setFooter(footer2);
		listview2.loadData();
		
		
		View footer3 = LayoutInflater.from(this).inflate(R.layout.footer, null);

		listview3.setContext(this);
		listview3.setFooter(footer3);
		listview3.loadData();
		
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lllayout);
		mViewCount = mScrollLayout.getChildCount();
		mImageViews = new LinearLayout[mViewCount];
		for (int i = 0; i < mViewCount; i++) {
			mImageViews[i] = (LinearLayout) linearLayout.getChildAt(i);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setOnClickListener(this);
			mImageViews[i].setTag(i);
		}
		mCurSel = 0;
		mImageViews[mCurSel].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);

		set = (ImageView) findViewById(R.id.set);
		add = (ImageView) findViewById(R.id.add);
		send = (ImageView) findViewById(R.id.send);

		set.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uploadImage(MainActivity.this);
			}
		});
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uploadImage3(MainActivity.this);
			}
		});
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uploadImage2(MainActivity.this);
			}
		});
	}

	public void uploadImage(final Activity context) {
		menuWindow = new SelectPicPopupWindow(MainActivity.this, itemsOnClick);
		menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.set), Gravity.TOP | Gravity.RIGHT, 10, 230);
	}

	public void uploadImage3(final Activity context) {
		Intent intent = new Intent();
		intent.setClass(this, SendMessageActivity.class);
		startActivity(intent);
		// this.finish();
	}

	public void uploadImage2(final Activity context) {
		Intent intent = new Intent();
		intent.setClass(this, ThemeSetActivity.class);
		startActivity(intent);
	}

	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			menuWindow.dismiss();
		}
	};

	private OnClickListener itemsOnClick2 = new OnClickListener() {

		public void onClick(View v) {
			menuWindow2.dismiss();
		}
	};

	private void setCurPoint(int index) {
		if (index < 0 || index > mViewCount - 1 || mCurSel == index) {
			return;
		}
		mImageViews[mCurSel].setEnabled(true);
		mImageViews[index].setEnabled(false);
		mCurSel = index;

		if (index == 0) {
			liaotian.setTextColor(0xff228B22);
			faxian.setTextColor(Color.BLACK);
			tongxunlu.setTextColor(Color.BLACK);
		} else if (index == 1) {
			liaotian.setTextColor(Color.BLACK);
			faxian.setTextColor(0xff228B22);
			tongxunlu.setTextColor(Color.BLACK);
		} else {
			liaotian.setTextColor(Color.BLACK);
			faxian.setTextColor(Color.BLACK);
			tongxunlu.setTextColor(0xff228B22);
		}
	}

	@Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer) (v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_MENU)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
