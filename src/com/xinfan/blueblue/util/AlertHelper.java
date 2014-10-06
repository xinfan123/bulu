package com.xinfan.blueblue.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class AlertHelper {
	/** 微软雅黑默认 */
	public static Typeface typefacenormal = Typeface.create("微软雅黑", Typeface.NORMAL);
	/** 微软雅黑粗体 */
	public static Typeface typefacebold = Typeface.create("微软雅黑", Typeface.BOLD);

	/**
	 * 弹出提示窗
	 * 
	 * @param titlestring
	 *            提示标题
	 * @param message
	 *            提示信息
	 * @param isshowcancle
	 *            是否显示取消按钮
	 * @param isshowclose
	 *            是否显示关闭按钮
	 * @param okstring
	 *            确定按钮字符
	 * @param canclestring
	 *            取消按钮字符
	 */

	public static void showNormalTips(Context context, String title, String message) {
		showTips(context, title, message, false, false, "确定", "");

	}

	public static void showTips(Context context, String titlestring, String message, boolean isshowcancle, boolean isshowclose, String okstring,
			String canclestring) {
		final AlertDialog dlg = new AlertDialog.Builder(context).setCancelable(false).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.alertdialog_showtxt);
		TextView title = (TextView) window.findViewById(R.id.AlertDialogTitle);
		Button btn_close = (Button) window.findViewById(R.id.btn_close);
		TextView text = (TextView) window.findViewById(R.id.AlertDialogText);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		Button cancel = (Button) window.findViewById(R.id.btn_cancel);
		title.setTypeface(typefacenormal);
		text.setTypeface(typefacenormal);
		ok.setTypeface(typefacebold);
		cancel.setTypeface(typefacebold);
		title.setText(titlestring);
		text.setText(Html.fromHtml(message));
		if (isshowcancle) {
			cancel.setVisibility(View.VISIBLE);
			cancel.setText(canclestring);
		} else {
			cancel.setVisibility(View.GONE);
		}
		if (isshowclose) {
			btn_close.setVisibility(View.VISIBLE);
		} else {
			btn_close.setVisibility(View.GONE);
		}
		ok.setText(okstring);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dlg.cancel();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dlg.cancel();
			}
		});
		btn_close.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dlg.cancel();
			}
		});
	}
}
