package com.xinfan.blueblue.location;

import java.util.concurrent.ArrayBlockingQueue;

import android.content.Context;

import com.xinfan.blueblue.location.GpsLocation.LocationListener;

public class GpsLocationManager {

	private static ArrayBlockingQueue<GpsRequest> requests = new ArrayBlockingQueue<GpsRequest>(10);

	static boolean isProcessing = false;

	static Object lock = new Object();

	public static void addLocation(Context context, LocationListener listener) {

		long id = System.nanoTime();

		synchronized (lock) {

			requests.offer(new GpsRequest(context, listener, id));

			loop();

		}

	}

	public static void loop() {

		synchronized (lock) {

			if (!isProcessing) {

				if (!requests.isEmpty()) {
					GpsRequest request = requests.poll();
					isProcessing = true;

					request.setEndListener(new GpsLocationEndListener() {
						@Override
						public void onEnd(GpsRequest request) {
							GpsLocationManager.doEnd(request);
						}
					});

					GpsLocation.locate2(request);
				}
			}
		}

	}

	public static void doEnd(GpsRequest request) {

		synchronized (lock) {
			requests.remove(request);

			isProcessing = false;

			loop();
		}
	}
}
