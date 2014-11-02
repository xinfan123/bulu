package com.xinfan.blueblue.location;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class GpsServiceLocationManager implements TencentLocationListener {

	private static GpsServiceLocationManager instance;

	private static final String[] NAMES = new String[] { "GEO", "NAME", "ADMIN AREA", "POI" };

	private static final int[] LEVELS = new int[] { TencentLocationRequest.REQUEST_LEVEL_GEO, TencentLocationRequest.REQUEST_LEVEL_NAME,
			TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA, TencentLocationRequest.REQUEST_LEVEL_POI };

	private static final int DEFAULT = 2;

	private int mIndex = DEFAULT;
	private int mLevel = LEVELS[DEFAULT];
	private TencentLocationManager mLocationManager;

	private LocationListener listener;

	private Handler mHandler;
	private HandlerThread mThread;

	public static GpsServiceLocationManager getInstance(Context context) {

		if (instance == null) {
			instance = new GpsServiceLocationManager();
			instance.onCreate(context);
		}

		return instance;
	}

	private void onCreate(Context context) {
		mLocationManager = TencentLocationManager.getInstance(context);
		// 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
		mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);

		mThread = new HandlerThread("Thread_demo_" + (int) (Math.random() * 10));
		mThread.start();
		mHandler = new Handler(mThread.getLooper());
	}

	// ====== view listener

	// 响应点击"停止"
	public void stopLocation() {
		mLocationManager.removeUpdates(this);

		// 清空
		mHandler.removeCallbacksAndMessages(null);
		// 停止线程
		mThread.getLooper().quit();
	}

	// 响应点击"开始"
	public void startLocation(LocationListener listener) {

		this.listener = listener;
		// 创建定位请求
		final TencentLocationRequest request = TencentLocationRequest.create().setInterval(5000) // 设置定位周期
				.setRequestLevel(mLevel); // 设置定位level

		// 在 mThread 线程发起定位
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				// 开始定位
				mLocationManager.requestLocationUpdates(request, instance);
			}
		});

	}

	// ====== view listener

	// ====== location callback

	@Override
	public void onLocationChanged(final TencentLocation location, final int error, String reason) {

		// 在 mThread 线程发起定位
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				LocationEntity uersLocation = new LocationEntity();
				if (error == TencentLocation.ERROR_OK) {

					uersLocation.setProvince(location.getProvince());
					uersLocation.setLatitude(location.getLatitude());
					uersLocation.setLongitude(location.getLongitude());
					uersLocation.setAddress(location.getAddress());
					uersLocation.setProvider(location.getProvider());
					uersLocation.setCity(location.getCity());
					uersLocation.setDistrict(location.getDistrict());
					uersLocation.setTown(location.getTown());
					uersLocation.setVillage(location.getVillage());
					uersLocation.setStreetNo(location.getStreetNo());

					if (listener != null) {
						listener.onLocationSucess(uersLocation);
					}
				} else {
					if (listener != null) {
						listener.onLocationError();
					}
				}
			}
		});

	}

	@Override
	public void onStatusUpdate(String name, int status, String desc) {
		// ignore
	}

	public static interface LocationListener {

		public void onLocationSucess(LocationEntity uersLocation);

		public void onLocationError();
	}
}
