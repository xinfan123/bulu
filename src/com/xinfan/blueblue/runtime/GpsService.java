package com.xinfan.blueblue.runtime;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.xinfan.blueblue.location.GpsLocation;
import com.xinfan.blueblue.location.GpsLocation.LocationListener;
import com.xinfan.blueblue.location.LocationEntity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.util.LogUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserGpsParam;

public class GpsService extends Service {

	private IBinder binder = new GpsService.LocalBinder();

	@Override
	public IBinder onBind(Intent arg0) {

		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		start();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		this.stop();
	}

	public class LocalBinder extends Binder {
		// 返回本地服务
		GpsService getService() {
			return GpsService.this;
		}
	}

	private final double EARTH_RADIUS = 6378137.0;
	private final double MOVE_THRESHOLD = 1000;
	private final long LOCATE_INTERVAL = 5000;// 60 * 60 * 1000;

	private Thread thread;

	private double last_gpsx;

	private double last_gpsy;

	public void start() {

		thread = new Thread() {
			public void run() {
				while (true) {

					GpsLocation.locate(getApplicationContext(), new LocationListener() {

						@Override
						public void onLocationSucess(LocationEntity uersLocation) {
							updateServer(uersLocation);
						}

						@Override
						public void onLocationError() {

						}
					});

					try {
						sleep(LOCATE_INTERVAL);
					} catch (InterruptedException e) {
						e.printStackTrace();
						LogUtil.e(e.getMessage(), e);
					}
				}
			}
		};

		thread.start();
	}

	public void stop() {
		try {

			if (thread != null) {
				thread.stop();
			}
		} catch (Exception e) {
			LogUtil.e(e.getMessage(), e);
		}

	}

	public boolean checkIfUpdate(LocationEntity uersLocation) {

		if (true) {
			return true;
		}
		if (this.last_gpsx == 0) {
			return true;
		}

		double distince = gps2m(this.last_gpsx, this.last_gpsy, Double.valueOf(uersLocation.getLongitude()), Double.valueOf(uersLocation.getLatitude()));

		if (distince >= MOVE_THRESHOLD) {
			return true;
		}

		return false;
	}

	public void updateServer(LocationEntity uersLocation) {

		if (checkIfUpdate(uersLocation)) {

			Request request = new Request(FunIdConstants.SET_USER_GPS);
			UserGpsParam param = new UserGpsParam();
			param.setLongitude(String.valueOf(uersLocation.getLongitude()));
			param.setLatitude(String.valueOf(uersLocation.getLatitude()));
			param.setArea(uersLocation.getCity());

			request.setParam(param);

			AnsynHttpRequest.requestSimpleByPost(getApplicationContext(), request, new RequestSucessCallBack() {
				public void call(Request data) {
					ToastUtil.showMessage(GpsService.this.getApplicationContext(), "定位更新成功");
				}
			});
		}

		this.last_gpsx = uersLocation.getLongitude();
		this.last_gpsy = uersLocation.getLatitude();
	}

	public double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

}
