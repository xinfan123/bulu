package com.xinfan.blueblue.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class SelectPicPopupWindow extends PopupWindow {

	private Button  btn_complain;//意见反馈按钮
	private Button  btn_cancel;//退出按钮
	private Button btn_account;//我的账户
	private Button btn_system;//我的账户
	private View mMenuView;

	public SelectPicPopupWindow(final Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.bottomdialog, null);
		
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
		btn_complain= (Button) mMenuView.findViewById(R.id.btn_complain);
		btn_system= (Button) mMenuView.findViewById(R.id.btn_system);
		btn_account= (Button) mMenuView.findViewById(R.id.btn_account);
		//监听系统设置

		btn_system.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//SaveDate.saveDate(context, new OAuthV2()); 
				
				Intent intent = new Intent();
	        	intent.setClass(v.getContext(),SystemSet.class);
	        	v.getContext().startActivity(intent);
	        	SelectPicPopupWindow.this.dismiss();
	        	//context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(w/2+50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable sdw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(sdw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});
		
		//监听意见反馈按钮
				btn_complain.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						//SaveDate.saveDate(context, new OAuthV2()); 
						
						Intent intent = new Intent();
			        	intent.setClass(v.getContext(),Complian.class);
			        	v.getContext().startActivity(intent);
			        	dismiss();
						//context.finish();
					}
				});
				this.setContentView(mMenuView);
				this.setWidth(w/2+50);
				this.setHeight(LayoutParams.WRAP_CONTENT);
				this.setFocusable(true);
				this.setAnimationStyle(R.style.mystyle);
				ColorDrawable cdw = new ColorDrawable(0000000000);
				this.setBackgroundDrawable(cdw);
				mMenuView.setOnTouchListener(new OnTouchListener() {
					
					public boolean onTouch(View v, MotionEvent event) {
						
						int height = mMenuView.findViewById(R.id.pop_layout).getTop();
						int y=(int) event.getY();
						if(event.getAction()==MotionEvent.ACTION_UP){
							if(y<height){
								dismiss();
							}
						}				
						return true;
					}
				});
		//监听退出按钮
		btn_account.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
		    	intent.setClass(v.getContext(),UserInfoActivity.class);
		    	v.getContext().startActivity(intent);	
		    	SelectPicPopupWindow.this.dismiss();
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//SaveDate.saveDate(context, new OAuthV2()); 
				
				Intent intent = new Intent();
	        	intent.setClass(v.getContext(),Exit.class);
	        	v.getContext().startActivity(intent);
	        	SelectPicPopupWindow.this.dismiss();
	        	//context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(w/2+50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});
	}
	
/*	public void click_myaccount(View v){
		Intent intent = new Intent();
    	intent.setClass(v.getContext(),UserInfoActivity.class);
    	v.getContext().startActivity(intent);
	}*/

}
