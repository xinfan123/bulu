package com.xinfan.blueblue.location;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.xinfan.blueblue.location.GpsLocation.LocationListener;

public class GpsLocationManager {

	private static List<GpsRequest> requests = new ArrayList<GpsRequest>();

	static boolean isProcessing = false;

	public synchronized static void addLocation(Context context, LocationListener listener) {

		long id = System.nanoTime();

		requests.add(new GpsRequest(context, listener, id));

		loop();

	}

	public synchronized static void loop() {

		if (!isProcessing) {

			if (!requests.isEmpty()) {
				GpsRequest request = requests.get(0);
				isProcessing = true;
				GpsLocation.locate2(request.getId(), request.getContext(), request.getListener(), new GpsLocationEndListener() {
					@Override
					public void onEnd(long id) {
						GpsLocationManager.doEnd(id);
					}
				});
			}
		}
	}

	public synchronized static void doEnd(long id) {
		for (GpsRequest request : requests) {
			if (request.getId() == id) {
				requests.remove(request);
			}
		}

		isProcessing = false;

		loop();
	}
}
