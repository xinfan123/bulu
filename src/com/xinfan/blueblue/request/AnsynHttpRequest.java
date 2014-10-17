package com.xinfan.blueblue.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.blueblue.common.LoadingDialogFragment;
import com.xinfan.blueblue.dao.DBHelper;
import com.xinfan.blueblue.util.JSONUtils;
import com.xinfan.blueblue.util.LogUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class AnsynHttpRequest {

	public static final int REQUEST_TIMEOUT = 20 * 1000;// 设置请求超时30秒钟
	public static final int SO_TIMEOUT = 20 * 1000; // 设置等待数据超时时间30秒钟
	static final int POST = 1; // post 提交
	static final int GET = 2; // get 提交

	static String tag = AnsynHttpRequest.class.getSimpleName();

	public static DefaultHttpClient mHttpClient;

	/**
	 * 异步请求网络数据
	 * 
	 * @param sendType
	 *            请求类型
	 * @param map
	 *            参数
	 * @param context
	 * @param callBack
	 *            回调对象
	 * @param url
	 * @param isCache
	 *            是否缓存
	 * @param isShowDialog
	 *            是否有提示框
	 * @param intUrl
	 */
	private static void doAsynRequest(final Activity context, final Request request, final ObserverCallBack callBack) {

		ThreadPoolUtils.execute(new MyRunnable(context, request, callBack));
	}

	/**
	 * 
	 * 访问网络初始化函数 支持Post请求方式
	 * 
	 * @param context
	 * @param callBack
	 *            回调执行函数 支持线程
	 * @param url
	 *            每个执行url
	 * @param map
	 *            参数
	 * @param isCache
	 *            是否本地缓存
	 * @param isShowDialog
	 *            是否弹出提示等待框
	 */
	public static void requestByPost(Activity context, Request request, final ObserverCallBack callBack) {

		// 组织URL
		StringBuffer buffer = new StringBuffer();
		buffer.append(Constants.http.http_request_head);
		// buffer.append(context.getResources().getString(url));

		String requestUrl = buffer.toString();
		request.setAddress(requestUrl);
		// requestUrl = requestUrl.substring(0,requestUrl.length()-1);
		// 异步请求数据
		doAsynRequest(context, request, callBack);
	}

	public static void requestSimpleByPost(Activity context, final Request request, final ObserverCallBack callBack) {

		// 组织URL
		StringBuffer buffer = new StringBuffer();
		buffer.append(Constants.http.http_request_head);

		String requestUrl = buffer.toString();
		// requestUrl = requestUrl.substring(0,requestUrl.length()-1);
		LogUtil.i("httpurl", requestUrl);
		request.setAddress(requestUrl);
		// 异步请求数据
		doAsynRequest(context, request, callBack);
	}

}

class MyRunnable implements Runnable {
	final Activity context;
	final ObserverCallBack callBack;
	final Request request;

	public MyRunnable(final Activity context, final Request request, final ObserverCallBack callBack) {
		this.context = context;
		this.callBack = callBack;
		this.request = request;

	}

	@Override
	public void run() {
		String data = null;
		LoadingDialogFragment loading = null;

		request.setShowDialog(false);

		if (Network.checkNetWorkType(context) == Network.NONETWORK) {
			ToastUtil.showMessage(context, "没有网络连接，请检查网络");
			return;
		}

		try {

			if (request.isShowDialog()) {
				loading = LoadingDialogFragment.newInstance(request.getDialogMessage());
				loading.open(context);
			}

			// 设置请求头超时请求参数
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, AnsynHttpRequest.REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams, AnsynHttpRequest.SO_TIMEOUT);
			
			if (AnsynHttpRequest.mHttpClient == null) {
				// AnsynHttpRequest.mHttpClient = new
				// DefaultHttpClient(httpParams);
				DefaultHttpClient client = new DefaultHttpClient(httpParams);
				ClientConnectionManager mgr = client.getConnectionManager();
				HttpParams params = client.getParams();
				client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, mgr.getSchemeRegistry()), params);
				//client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
				client.getParams().setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, "UTF-8");
				AnsynHttpRequest.mHttpClient = client;
			
			}
			// HttpClient httpClient = HttpUtils.getNewHttpClient(context);
			HttpResponse response = null;
			switch (request.getSendType()) {
			case AnsynHttpRequest.GET: // get 方式提交
				HttpGet get = new HttpGet(request.getAddress());
				// get.setHeader("User-Agent", sUserAgent.toString());
				//get.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
				response = AnsynHttpRequest.mHttpClient.execute(get);
				break;
			case AnsynHttpRequest.POST: // post 方式提交
				HttpPost post = new HttpPost(request.getAddress());
				//post.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");

				// post.setHeader("User-Agent", sUserAgent.toString());
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				JSONObject map = JSONUtils.toJSONObject(request.getRequestParamter());

				map.put("class", String.valueOf(request.getRequestParamter().getClass().getName()));

				if (map != null && map.size() > 0) {
					for (String key : map.keySet()) {
						params.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
					}
				}
				HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
				post.setEntity(entity);
				response = AnsynHttpRequest.mHttpClient.execute(post);
				break;
			default:
				break;
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~"+(response.getStatusLine().getStatusCode()));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// httpClient.getCookieStore().getCookies().g
				data = EntityUtils.toString(response.getEntity());
				if (request.isCache()) {// 把数据缓存到本地
					DBHelper.getInstance(context).addOrUpdateURLData(request.getParam().getFunId(), data);
				}
			} else {
				if (!request.isCache()) {
					// AnsynHttpRequest.sendBroadcastReceiverMessage(context,
					// "11111111111");
				}
				data = null;
			}
		} catch (Exception e) {
			LogUtil.e(AnsynHttpRequest.tag, e);
			ToastUtil.showMessage(context, "网络通信异常");
			data = null;
		} finally {
			if (request.isShowDialog()) {
				if (loading != null) {
					loading.close();
				}
			}
		}

		try { // 回调数据
			if (callBack != null) {
				if (data != null && data.length() > 0) {
					BaseResult response = JSONObject.parseObject(data, request.getPath().getResultClass());

					request.setResult(response);
					request.setCode(response.getResult());
					request.setMessage(response.getMsg());

					if (request.getCode() < 0) {
						ToastUtil.showMessage(context, request.getMessage());
					} else {
						((Activity) context).runOnUiThread(new Runnable() {
							public void run() {
								callBack.call(request);
							}
						});
					}
				}
			}
		} catch (Exception e) {
			LogUtil.e(e.getMessage(), e);
			ToastUtil.showMessage(context, "请求数据异常");
		}

	}
}
