package com.xinfan.blueblue.request;

public interface CastObserverCallBack<T> extends ObserverCallBack {
	
	public void castCall(T bean);
}