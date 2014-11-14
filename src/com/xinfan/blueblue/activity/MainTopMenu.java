package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xinfan.blueblue.activity.main.PhotoSelectActivity;
import com.xinfan.blueblue.dao.RequestCacheKeyHelper;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserAvatarParam;
import com.xinfan.msgbox.http.service.vo.result.UserAvatarResult;

public class MainTopMenu extends PopupWindow {
	private TextView menu_userid_text;
	private TextView menu_username_text;
	private Button btn_complain;// 意见反馈按钮
	private Button btn_cancel;// 退出按钮
	private Button btn_account;// 我的账户
	private Button btn_system;// 系统设置
	private View mMenuView;
	public static MainTopMenu instance;

	public ImageView photo_image;
	public boolean imageLoaded = false;
	public Context context;

	public MainTopMenu(final Activity context, OnClickListener itemsOnClick) {
		super(context);

		this.context = context;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.bottomdialog, null);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
		btn_complain = (Button) mMenuView.findViewById(R.id.btn_complain);
		btn_system = (Button) mMenuView.findViewById(R.id.btn_system);
		btn_account = (Button) mMenuView.findViewById(R.id.btn_account);
		menu_userid_text = (TextView) mMenuView.findViewById(R.id.menu_userid_text);
		menu_username_text = (TextView) mMenuView.findViewById(R.id.menu_username_text);
		photo_image = (ImageView) mMenuView.findViewById(R.id.photo_image);
		// 监听系统设置

		btn_system.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// SaveDate.saveDate(context, new OAuthV2());

				Intent intent = new Intent();
				intent.setClass(v.getContext(), SystemSetActivity.class);
				v.getContext().startActivity(intent);
				MainTopMenu.this.dismiss();
				// context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(w / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable sdw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(sdw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

		// 监听意见反馈按钮
		btn_complain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// SaveDate.saveDate(context, new OAuthV2());

				Intent intent = new Intent();
				intent.setClass(v.getContext(), ComplianActivity.class);
				v.getContext().startActivity(intent);
				dismiss();
				// context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(w / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable cdw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(cdw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		// 监听我的账户
		btn_account.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(v.getContext(), AccountInfoActivity.class);
				v.getContext().startActivity(intent);
				MainTopMenu.this.dismiss();
			}
		});
		// 监听退出
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// SaveDate.saveDate(context, new OAuthV2());

				Intent intent = new Intent();
				intent.setClass(v.getContext(), ExitActivity.class);
				v.getContext().startActivity(intent);
				MainTopMenu.this.dismiss();
				// context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(w / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

		photo_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(context, PhotoSelectActivity.class);
				context.startActivity(intent);
			}
		});

		showLoginUserInfo();
	}

	public void showLoginUserInfo() {
		SharePreferenceUtil util = new SharePreferenceUtil(mMenuView.getContext(), Constants.USER_INFO);
		String userid = String.valueOf(util.getUserId());
		String username = util.getUsername();

		menu_userid_text.setText(userid);
		menu_username_text.setText(username);

		getAvatar(util.getAvatar());
	}

	public void getAvatar(String name) {
		
		if (!imageLoaded) {

			Request request = new Request(FunIdConstants.USER_AVATAR_GET);
			UserAvatarParam param = new UserAvatarParam();
			param.setAvatar(name);
			request.setParam(param);
			request.setShowDialog(false);
			request.setCache(true);
			request.setCacheKey(RequestCacheKeyHelper.generateAvatarCacheKey(param));

			AnsynHttpRequest.requestSimpleByPost(context, request, new RequestSucessCallBack() {
				public void call(Request data) {
					UserAvatarResult result = (UserAvatarResult) data.getResult();
					String avatar = result.getAvatar();
					if (avatar != null && avatar.length() > 100) {
						byte[] bytes = android.util.Base64.decode(avatar, android.util.Base64.DEFAULT);
						Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
						photo_image.setImageBitmap(bm);
					} else {
						photo_image.setImageDrawable(context.getResources().getDrawable(R.drawable.nophoto));
					}
				}
			});
			imageLoaded = true;
		}
	}
	
	public void updateAvatar(Bitmap avatar){
		this.imageLoaded =true;
		this.photo_image.setImageBitmap(avatar);
	}
	
	/*
	 * public void click_myaccount(View v){ Intent intent = new Intent();
	 * intent.setClass(v.getContext(),UserInfoActivity.class);
	 * v.getContext().startActivity(intent); }
	 */

}
