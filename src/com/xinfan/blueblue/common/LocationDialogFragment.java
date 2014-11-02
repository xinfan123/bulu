package com.xinfan.blueblue.common;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class LocationDialogFragment extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.message_gps_location, container, false);
		return v;
	}

	public static LocationDialogFragment newInstance(String msg) {
		LocationDialogFragment fragment = new LocationDialogFragment();
		Bundle args = new Bundle();
		args.putString("msg", msg);
		fragment.setArguments(args);

		fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		fragment.setCancelable(false);

		return fragment;
	}

	public void open(Activity activity) {
		show(activity.getFragmentManager(), "df");
	}

	public void close() {
		this.dismiss();
	}

}
