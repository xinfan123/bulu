package com.xinfan.blueblue.location;

import android.content.Context;

import com.xinfan.blueblue.location.GpsLocation.LocationListener;

public class GpsRequest {

	Context context;
	LocationListener listener;
	long id;

	GpsLocationEndListener endListener;

	public GpsLocationEndListener getEndListener() {
		return endListener;
	}

	public void setEndListener(GpsLocationEndListener endListener) {
		this.endListener = endListener;
	}

	public GpsRequest(Context context, LocationListener listener, long id, GpsLocationEndListener endListener) {
		super();
		this.context = context;
		this.listener = listener;
		this.id = id;
		this.endListener = endListener;
	}

	public GpsRequest(Context context, LocationListener listener, long id) {
		super();
		this.context = context;
		this.listener = listener;
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GpsRequest(Context context, LocationListener listener) {
		super();
		this.context = context;
		this.listener = listener;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public LocationListener getListener() {
		return listener;
	}

	public void setListener(LocationListener listener) {
		this.listener = listener;
	}

}
