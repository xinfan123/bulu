package com.xinfan.blueblue.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserSentParam;

public class ThemeInputActivity extends BaseActivity {

	private TextView ok;

	private EditText text;

	private RelativeLayout theme_input_edit_layout;

	private TextView theme_input_edit_count;

	int wordnum = 140;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_input);

		ok = (TextView) this.findViewById(R.id.click_ok);
		text = (EditText) this.findViewById(R.id.theme_text);
		theme_input_edit_count = (TextView) this.findViewById(R.id.theme_input_edit_count);
		theme_input_edit_layout = (RelativeLayout) this.findViewById(R.id.theme_input_edit_layout);

		initWordLimit();

		initEvent();
	}

	public void initEvent() {

		ok.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String str = (String) text.getText().toString();
				if (str == null || str.length() == 0) {
					ToastUtil.showMessage(ThemeSetActivity.instance, "请输入正确内容");
					return;
				}

				if (str.length() > 140) {
					ToastUtil.showMessage(ThemeSetActivity.instance, "内容太长，不能超过140个字符");
					return;
				}

				Request request = new Request(FunIdConstants.SET_USER_SENT);

				UserSentParam param = new UserSentParam();
				param.setUserId(LoginUserContext.getUserId(ThemeInputActivity.this));
				param.setUserSent(str);
				request.setParam(param);
				request.setShowDialog(false);

				AnsynHttpRequest.requestSimpleByPost(ThemeInputActivity.this, request, new RequestSucessCallBack() {
					public void call(Request request) {
						ThemeSetActivity.instance.load();
						ToastUtil.showMessage(ThemeInputActivity.this, "添加主题成功");
					}
				});

				ThemeInputActivity.this.finish();
			}
		});
	}

	public void initWordLimit() {

		this.text.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				temp = s;
			}

			public void afterTextChanged(Editable s) {
				int number = BizUtils.getWordCount(temp.toString());
				theme_input_edit_count.setText("" + number + "/" + wordnum);
				selectionStart = text.getSelectionStart();
				selectionEnd = text.getSelectionEnd();
				if (BizUtils.getWordCount(temp.toString()) > wordnum) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					text.setText(s);
					text.setSelection(tempSelection);// 设置光标在最后
				}
			}
		});

	}

	public void send_msg_back(View view) {
		finish();
	}

	public void click_ok(View view) {
		finish();
	}

}