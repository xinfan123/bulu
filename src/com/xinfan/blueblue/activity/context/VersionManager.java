package com.xinfan.blueblue.activity.context;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xinfan.blueblue.activity.UpdateVersionActivity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ClientVersionParam;
import com.xinfan.msgbox.http.service.vo.result.ClientVersionResult;

public class VersionManager {

	private Activity context;

	public VersionManager(Activity context) {
		this.context = context;
	}

	public void checkUpdate() {

		Request request = new Request(FunIdConstants.GET_CLIENTVERSION);
		ClientVersionParam param = new ClientVersionParam();
		param.setClientCode("android");
		request.setParam(param);
		request.setShowDialog(false);

		AnsynHttpRequest.requestSimpleByPost(context, request, new RequestSucessCallBack() {
			public void call(Request data) {

				ClientVersionResult result = (ClientVersionResult) data.getResult();
				String currentVersion = result.getCurrentVersion();
				String oldVersion = VersionContext.getVersion(context);

				if (currentVersion != null && currentVersion.compareTo(oldVersion) > 0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();

					Bundle bundle = new Bundle();
					bundle.putSerializable("vo", result);
					intent.putExtras(bundle);
					intent.setClass(context, UpdateVersionActivity.class);
					context.startActivity(intent);
				} else {

				}
			}
		});
	}

	public void checkManualUpdate() {

		Request request = new Request(FunIdConstants.GET_CLIENTVERSION);
		ClientVersionParam param = new ClientVersionParam();
		param.setClientCode("android");
		request.setParam(param);
		request.setShowDialog(true);

		AnsynHttpRequest.requestSimpleByPost(context, request, new RequestSucessCallBack() {
			public void call(Request data) {

				ClientVersionResult result = (ClientVersionResult) data.getResult();
				String currentVersion = result.getCurrentVersion();
				String oldVersion = VersionContext.getVersion(context);

				if (currentVersion != null && currentVersion.compareTo(oldVersion) > 0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();

					Bundle bundle = new Bundle();
					bundle.putSerializable("vo", result);
					intent.putExtras(bundle);
					intent.setClass(context, UpdateVersionActivity.class);
					context.startActivity(intent);
				} else {
					ToastUtil.showMessage(context, "已经是最新版本了");
				}
			}
		});
	}
}
