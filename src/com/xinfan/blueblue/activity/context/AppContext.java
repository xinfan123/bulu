package com.xinfan.blueblue.activity.context;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;

import com.igexin.sdk.PushManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.dao.DBHelper;

public class AppContext {

	public static void destroy(Context context) {
		for (BaseActivity act : BaseActivity.activitys) {
			act.finish();
		}

		// 关闭数据库
		DBHelper.getInstance().close();

		// GpsGather.getInstance().start();
	}

	public static void init(Context context) {

		// 初始数据库
		DBHelper.init(context);

		PushManager.getInstance().initialize(context.getApplicationContext());

		Intent intent = new Intent("com.xinfan.blueblue.runtime.GpsService");

		context.startService(intent);
		
		initImageLoader(context);

	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().diskCacheSize(50 * 1024 * 1024) // 50
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}
