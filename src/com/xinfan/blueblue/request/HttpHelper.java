package com.xinfan.blueblue.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.xinfan.blueblue.util.JSONUtils;

public class HttpHelper {

	public static String httpaddress = "";

	public static void init(String address, String port) {
		httpaddress = "http://" + address + ":" + port + "/"
				+ Constants.SERVER_SUFFIX;
	}

	public static void post(final Activity context, final DataMap asyncMap,
			final ResponseCallBack call) {

		AsyncTask<DataMap, Void, DataMap> t = new AsyncTask<DataMap, Void, DataMap>() {

			private Dialog mDialog;

			protected void onPreExecute() {
				mDialog = DialogFactory.creatRequestDialog(context, "正在处理...");
				mDialog.show();
			}

			@Override
			protected DataMap doInBackground(DataMap... paramters) {

				SharePreferenceUtil util = new SharePreferenceUtil(context,
						Constants.USER_INFO);

				DataMap postParam = paramters[0];

				String sessionid = util.getSessionId();
				postParam.put("sessionid", sessionid);

				List<NameValuePair> formparams = toNameValues(postParam);
				// 创建默认的httpClient实例.
				HttpClient httpclient = new DefaultHttpClient();
				
				// 创建httppost
				HttpPost httppost = new HttpPost(httpaddress);

				long start = System.currentTimeMillis();

				try {
					UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
							formparams, "UTF-8");
					httppost.setEntity(uefEntity);

					System.out
							.println("executing request " + httppost.getURI());
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						System.out
								.println("--------------------------------------");
						String msg = EntityUtils.toString(entity, "UTF-8");
						System.out.println("Response content: " + msg);
						System.out
								.println("--------------------------------------");
						long end = System.currentTimeMillis();
						System.out
								.println("cost :" + (end - start) * 0.1 / 100);

						DataMap result = JSONUtils.parseObject(msg,
								DataMap.class);

						return result;

						// call.call(result);
					}
				} catch (UnsupportedEncodingException e) {
					Log.e(e.getMessage(), e.getMessage(), e);
					DataMap map = new DataMap();
					map.put("result", -2);
					map.put("message", "网络异常，请检查连接情况或是本软件配置");
					return map;
				} catch (ClientProtocolException e) {
					Log.e(e.getMessage(), e.getMessage(), e);
					DataMap map = new DataMap();
					map.put("result", -2);
					map.put("message", "网络异常，请检查连接情况或是本软件配置");
					return map;
				} catch (IOException e) {
					Log.e(e.getMessage(), e.getMessage(), e);
					DataMap map = new DataMap();
					map.put("result", -2);
					map.put("message", "网络异常，请检查连接情况或是本软件配置");
					return map;
				} catch (Exception e) {
					Log.e(e.getMessage(), e.getMessage(), e);
					DataMap map = new DataMap();
					map.put("result", -2);
					map.put("message", "网络连接异常");
					return map;

				} finally {
					httpclient.getConnectionManager().shutdown();
				}
				return null;
			}

			@Override
			protected void onPostExecute(DataMap rtMap) {
				int result = rtMap.getInt("result");
				String message = rtMap.getString("message");
				if (result == -1) {
					Intent intent = new Intent();
					//intent.setClass(context, LoginActivity.class);
					context.startActivity(intent);
					context.finish();
				} else if (result == -2) {
					DialogFactory.ToastDialog(context, "信息提示", message);
				} else {

					call.call(rtMap);

					if (mDialog != null) {
						mDialog.dismiss();
					}
				}
			}
		};

		t.execute(asyncMap);
	}

	public static List<NameValuePair> toNameValues(Map map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String val = String.valueOf(map.get(key));
			formparams.add(new BasicNameValuePair(key.toUpperCase(), val));
		}
		return formparams;
	}

}
