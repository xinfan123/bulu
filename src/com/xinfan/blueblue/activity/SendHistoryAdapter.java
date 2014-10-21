package com.xinfan.blueblue.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.send.SendMessageSummaryVO;

public class SendHistoryAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<SendMessageSummaryVO> list = new ArrayList<SendMessageSummaryVO>();

	public SendHistoryAdapter(Context context, ArrayList<SendMessageSummaryVO> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		SendMessageSummaryVO hh = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.send_history, parent, false);
			// h.pic = (ImageView)view.findViewById(R.id.l1);
			view.setTag(hh);
		}

		TextView titleView = (TextView) view.findViewById(R.id.message_title);
		TextView timeView = (TextView) view.findViewById(R.id.message_time);
		TextView moreView = (TextView) view.findViewById(R.id.message_more);

		// h.pic.setImageResource(Integer.parseInt(hh.getTxPath()));

		int dtime = hh.getDurationTime() == null ? 0 : hh.getDurationTime();
		String time = "";

		Calendar createtime = Calendar.getInstance();
		createtime.setTime(hh.getRefreshTime());
		createtime.add(Calendar.MINUTE, dtime);

		Calendar currenttime = Calendar.getInstance();

		if (createtime.before(currenttime)) {
			time = "已过期";
		} else {

			long lasttime = createtime.getTimeInMillis() - currenttime.getTimeInMillis();
			int lastmin = (int) lasttime / (1000 * 60);
			time = lastmin + "分后过期";
		}

		String title = hh.getTitle();
		if (hh.getTitle().length() > 20) {
			title = hh.getTitle().substring(0, 20) + "...";
		}

		String content = hh.getContext();
		if (hh.getContext().length() > 30) {
			content = hh.getContext().substring(0, 30) + "...";
		}

		titleView.setText(title);
		moreView.setText(content);
		timeView.setText(time);

		return view;
	}

}
