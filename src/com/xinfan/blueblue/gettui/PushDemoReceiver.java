package com.xinfan.blueblue.gettui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.xinfan.blueblue.activity.Login;
import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;
import com.xinfan.msgbox.http.service.vo.param.UserCIDParam;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

public class PushDemoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {

		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");

			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

			if (payload != null) {
				String data = new String(payload);

				Log.d("GetuiSdkDemo", "==============================Got Payload:" + data);
				// if (GetuiSdkDemoActivity.tLogView != null)
				// GetuiSdkDemoActivity.tLogView.append(data + "\n");
			}
			break;
		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			// 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
			String cid = bundle.getString("clientid");
			Log.d("GetuiSdkDemo", "===============================Got GET_CLIENTID:" + cid);

			updateCID(cid);

			// if (GetuiSdkDemoActivity.tView != null)
			// GetuiSdkDemoActivity.tView.setText(cid);
			break;
		case PushConsts.THIRDPART_FEEDBACK:
			/*
			 * String appid = bundle.getString("appid"); String taskid =
			 * bundle.getString("taskid"); String actionid =
			 * bundle.getString("actionid"); String result =
			 * bundle.getString("result"); long timestamp =
			 * bundle.getLong("timestamp");
			 * 
			 * Log.d("GetuiSdkDemo", "appid = " + appid); Log.d("GetuiSdkDemo",
			 * "taskid = " + taskid); Log.d("GetuiSdkDemo", "actionid = " +
			 * actionid); Log.d("GetuiSdkDemo", "result = " + result);
			 * Log.d("GetuiSdkDemo", "timestamp = " + timestamp);
			 */
			break;
		default:
			break;
		}
	}

	public void updateCID(String cidFromServer) {

		String cidFromSession = LoginUserContext.getCID(MainActivity.instance);

		if (cidFromServer != null && cidFromServer.length() > 1) {

			if (!cidFromServer.equals(cidFromSession)) {

				Request request = new Request(FunIdConstants.USER_UPDATE_CID);
				UserCIDParam param = new UserCIDParam();
				param.setCid(cidFromServer);
				request.setParam(param);

				AnsynHttpRequest.requestSimpleByPost(MainActivity.instance, request, new RequestSucessCallBack() {

					public void call(Request data) {
						ToastUtil.showMessage(MainActivity.instance, data.getResult().getMsg());
					}
				});
			}

		}

	}
}
