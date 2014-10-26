package com.xinfan.blueblue.gettui;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserCIDParam;

public class GetuiPushReceiver extends BroadcastReceiver {

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

			updateCID(context, cid);

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

	public void updateCID(Context context, String cidFromServer) {

		String cidFromSession = LoginUserContext.getCID(context);

		if (cidFromServer != null && cidFromServer.length() > 1) {

			if (!cidFromServer.equals(cidFromSession)) {

				LoginUserContext.setCID(context, cidFromServer);
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

	public void show(Context context) {

		if (!isRunningForeground(context)) {
			NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			String title = "通知标题";
			String content = "通知内容";
			Notification n = new Notification(R.drawable.ic_launcher, "通知", System.currentTimeMillis());
			Intent intent = new Intent(context, MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
			n.setLatestEventInfo(context, title, content, pi);
			nm.notify(1, n);
		}

	}

	public boolean isRunningForeground(Context context) {

		String packageName = context.getPackageName();

		String topActivityClassName = getTopActivityName(context);
		System.out.println("packageName=" + packageName + ",topActivityClassName=" + topActivityClassName);
		if (packageName != null && topActivityClassName != null && topActivityClassName.startsWith(packageName)) {
			System.out.println("---> isRunningForeGround");
			return true;
		} else {
			System.out.println("---> isRunningBackGround");
			return false;
		}
	}

	public String getTopActivityName(Context context) {
		String topActivityClassName = null;
		ActivityManager activityManager = (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
		List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
		if (runningTaskInfos != null) {
			ComponentName f = runningTaskInfos.get(0).topActivity;
			topActivityClassName = f.getClassName();
		}
		return topActivityClassName;
	}

}
