package com.xinfan.blueblue.activity.common;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xinfan.blueblue.activity.R;

public class DisplayImageOptionsConfig {

	public static DisplayImageOptions avatar_options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.nophoto).cacheInMemory(true)
			.cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

}
