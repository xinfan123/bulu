package com.xinfan.blueblue.request;

public interface CastObserverCallBack<T> extends RequestSucessCallBack {
	
	public void castCall(T bean); 
}