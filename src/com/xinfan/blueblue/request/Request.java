package com.xinfan.blueblue.request;

import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.BaseParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class Request implements java.io.Serializable {

	private FunIdConstants path;

	private BaseParam param;

	private BaseResult result;

	private int code;

	private String message;

	private boolean isCache;

	private String cacheKey;

	private boolean isShowDialog = true;

	private String dialogMessage = "正在处理...";

	private int sendType = 1; // 1 post 2get

	private String address;

	private RequestErrorCallBack requestErrorCallBack;

	private RequestSucessCallBack requestSucessCallBack;

	private NetworkErrorCallBack networkErrorCallBack;

	private RequestFinishCallBack requestFinishCallBack;

	private RequestStartCallBack requestStartCallBack;

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public Request(FunIdConstants path) {
		this.path = path;
		Class paramClass = path.getParamClass();
		try {
			param = (BaseParam) paramClass.newInstance();
			param.setFunId(path.getFunId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDialogMessage() {
		return dialogMessage;
	}

	public void setDialogMessage(String dialogMessage) {
		this.dialogMessage = dialogMessage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isShowDialog() {
		return isShowDialog;
	}

	public void setShowDialog(boolean isShowDialog) {
		this.isShowDialog = isShowDialog;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseParam getRequestParamter() {
		return param;
	}

	public FunIdConstants getPath() {
		return path;
	}

	public void setPath(FunIdConstants path) {
		this.path = path;
	}

	public BaseParam getParam() {
		return param;
	}

	public void setParam(BaseParam param) {
		this.param = param;
		this.param.setFunId(path.getFunId());
	}

	public BaseResult getResult() {
		return result;
	}

	public void setResult(BaseResult result) {
		this.result = result;
	}

	public boolean isCache() {
		return isCache;
	}

	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public RequestErrorCallBack getRequestErrorCallBack() {
		return requestErrorCallBack;
	}

	public void setRequestErrorCallBack(RequestErrorCallBack requestErrorCallBack) {
		this.requestErrorCallBack = requestErrorCallBack;
	}

	public RequestSucessCallBack getRequestSucessCallBack() {
		return requestSucessCallBack;
	}

	public void setRequestSucessCallBack(RequestSucessCallBack requestSucessCallBack) {
		this.requestSucessCallBack = requestSucessCallBack;
	}

	public NetworkErrorCallBack getNetworkErrorCallBack() {
		return networkErrorCallBack;
	}

	public void setNetworkErrorCallBack(NetworkErrorCallBack networkErrorCallBack) {
		this.networkErrorCallBack = networkErrorCallBack;
	}

	public RequestFinishCallBack getRequestFinishCallBack() {
		return requestFinishCallBack;
	}

	public void setRequestFinishCallBack(RequestFinishCallBack requestFinishCallBack) {
		this.requestFinishCallBack = requestFinishCallBack;
	}

	public RequestStartCallBack getRequestStartCallBack() {
		return requestStartCallBack;
	}

	public void setRequestStartCallBack(RequestStartCallBack requestStartCallBack) {
		this.requestStartCallBack = requestStartCallBack;
	}

}
