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
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.dao.CacheDataDao;
import com.xinfan.blueblue.dao.DaoFactory;
import com.xinfan.blueblue.util.JSONUtils;
import com.xinfan.blueblue.util.LogUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;
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
	private static void doAsynRequest(final Context context, final Request request) {

		ThreadPoolUtils.execute(new MyRunnable(context, request));

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
	public static void requestByPost(Activity context, Request request, final RequestSucessCallBack callBack) {

		// 组织URL
		StringBuffer buffer = new StringBuffer();
		buffer.append("http://" + SystemConfigContext.getAddress(context) + "/" + Constants.http.http_request_uri);
		// buffer.append(context.getResources().getString(url));

		String requestUrl = buffer.toString();
		request.setAddress(requestUrl);
		// requestUrl = requestUrl.substring(0,requestUrl.length()-1);
		// 异步请求数据

		request.setRequestSucessCallBack(callBack);

		doAsynRequest(context, request);
	}

	public static void requestSimpleByPost(Context context, final Request request, final RequestSucessCallBack callBack) {

		// 组织URL
		StringBuffer buffer = new StringBuffer();
		buffer.append("http://" + SystemConfigContext.getAddress(context) + "/" + Constants.http.http_request_uri);

		String requestUrl = buffer.toString();
		// requestUrl = requestUrl.substring(0,requestUrl.length()-1);
		LogUtil.i("httpurl", requestUrl);
		request.setAddress(requestUrl);
		request.setRequestSucessCallBack(callBack);
		// 异步请求数据
		doAsynRequest(context, request);
	
	}

}

class MyRunnable implements Runnable {
	final Context context;
	final Request request;

	public MyRunnable(final Context context, final Request request) {
		this.context = context;
		this.request = request;

	}

	@Override
	public void run() {
		String data = null;

		if (request.isShowDialog() && context instanceof BaseActivity) {
			((BaseActivity) context).preLoading();
		}

		if (request.getRequestStartCallBack() != null) {
			request.getRequestStartCallBack().call(request);
		}

		if (Network.checkNetWorkType(context) == Network.NONETWORK) {
			ToastUtil.showMessage(context, "没有网络连接，请检查网络");

			if (request.getNetworkErrorCallBack() != null) {

				((Activity) context).runOnUiThread(new Thread() {
					public void run() {
						request.getNetworkErrorCallBack().call(request);
					}
				});
			}

			if (request.isCache()) {// 把数据缓存到本地
				data = DaoFactory.getDao(CacheDataDao.class).getURLData(request.getCacheKey());
			}

		} else {

			try {

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
					// client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
					// "UTF-8");
					client.getParams().setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, "UTF-8");
					AnsynHttpRequest.mHttpClient = client;

				}
				// HttpClient httpClient = HttpUtils.getNewHttpClient(context);
				HttpResponse response = null;
				switch (request.getSendType()) {
				case AnsynHttpRequest.GET: // get 方式提交
					HttpGet get = new HttpGet(request.getAddress());
					// get.setHeader("User-Agent", sUserAgent.toString());
					// get.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
					// "UTF-8");
					response = AnsynHttpRequest.mHttpClient.execute(get);
					break;
				case AnsynHttpRequest.POST: // post 方式提交
					HttpPost post = new HttpPost(request.getAddress());
					// post.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
					// "UTF-8");

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
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					List<Cookie> cookies = AnsynHttpRequest.mHttpClient.getCookieStore().getCookies();
					String tmpcookies = "";
					for (Cookie c : cookies) {
						tmpcookies += c.toString() + ";";
					}

					data = EntityUtils.toString(response.getEntity());
				} else {
					ToastUtil.showMessage(context, "服务请求通信异常");
					data = null;
				}

			} catch (Exception e) {
				LogUtil.e(AnsynHttpRequest.tag, e);
				ToastUtil.showMessage(context, "网络通信异常");
				data = null;

				if (request.getNetworkErrorCallBack() != null) {

					((Activity) context).runOnUiThread(new Thread() {
						public void run() {
							request.getNetworkErrorCallBack().call(request);
						}
					});
				}
			}
		}

		if (data != null && data.length() > 0) {
			BaseResult response = JSONObject.parseObject(data, request.getPath().getResultClass());

			request.setResult(response);
			request.setCode(response.getResult());
			request.setMessage(response.getMsg());

			if (request.getCode() < 0) {

				if (request.getCode() == -1) {
					autoLogin(request);
				} else {
					if (request.getRequestErrorCallBack() != null) {

						new Handler(context.getMainLooper()).post(new Runnable() {
							public void run() {
								request.getRequestErrorCallBack().call(request);
							}
						});

						/*
						 * ((Activity) context).runOnUiThread(new Thread() {
						 * public void run() {
						 * request.getRequestErrorCallBack().call(request); }
						 * });
						 */

					} else {
						ToastUtil.showMessage(context, request.getMessage());
					}
				}

			} else {

				if (request.isCache()) {// 把数据缓存到本地
					DaoFactory.getDao(CacheDataDao.class).addOrUpdateURLData(request.getCacheKey(), data);
				}

				new Handler(context.getMainLooper()).post(new Runnable() {
					public void run() {
						request.getRequestSucessCallBack().call(request);
					}
				});

				/*
				 * ((Activity) context).runOnUiThread(new Thread() { public void
				 * run() { request.getRequestSucessCallBack().call(request); }
				 * });
				 */
			}
		}

		if (request.isShowDialog() && context instanceof BaseActivity) {
			((BaseActivity) context).afterLoading();
		}

		if (request.getRequestFinishCallBack() != null) {
			request.getRequestFinishCallBack().call(request);
		}
	}

	public void autoLogin(final Request preRequest) {

		boolean login = LoginUserContext.getIsLogin(context);
		if (login) {

			String mobile = LoginUserContext.getMobile(context);
			String enPasswd = LoginUserContext.getPassword(context);

			Request loginRequest = new Request(FunIdConstants.LOGIN);
			LoginParam param = new LoginParam();
			param.setMobile(mobile);
			param.setPasswd(enPasswd);
			loginRequest.setParam(param);

			AnsynHttpRequest.requestSimpleByPost(context, loginRequest, new RequestSucessCallBack() {
				public void call(Request data) {
					AnsynHttpRequest.requestSimpleByPost(context, preRequest, preRequest.getRequestSucessCallBack());
				}
			});
		}
	}
}
