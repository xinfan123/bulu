package com.xinfan.blueblue.activity.contact;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.xinfan.blueblue.activity.R;

public class MarkModifyWindow extends PopupWindow {

	private Button btn_ok;
	private View view;

	public MarkModifyWindow(final Activity context,final OnClickYesMark call) {
	
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(R.layout.contact_mark_input_dialog, null);
		
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText input = (EditText)view.findViewById(R.id.input_text);
				String str = input.getText().toString();
				call.onClickYesMark(str);
			}
		});
		
		this.setContentView(view);
		this.setWidth(w/2+50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mypopwindow_anim_style);
		//setAnimationStyle(R.style.PopupWindowAnimation);
		//ColorDrawable dw = new ColorDrawable(0000000000);
		//this.setBackgroundDrawable(dw);
		 ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setOutsideTouchable(true);
		this.setTouchable(true);
		
/*		view.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = view.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		}); */
		
		
	}


}
