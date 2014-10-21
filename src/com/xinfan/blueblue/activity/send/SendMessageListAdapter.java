package com.xinfan.blueblue.activity.send;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class SendMessageListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<SendMessageSummaryVO> list = new ArrayList<SendMessageSummaryVO>();

	public SendMessageListAdapter(Context context, ArrayList<SendMessageSummaryVO> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.send_list_item, parent, false);
			// h.pic = (ImageView)view.findViewById(R.id.l1);
			view.setTag(hh);
		}

		TextView titleView = (TextView) view.findViewById(R.id.message_title);
		TextView timeView = (TextView) view.findViewById(R.id.message_time);
		TextView moreView = (TextView) view.findViewById(R.id.message_more);
		TextView refreshView = (TextView) view.findViewById(R.id.mesage_refresh_count);
		TextView pushView = (TextView) view.findViewById(R.id.message_push_count);
		TextView readView = (TextView) view.findViewById(R.id.mesage_read_count);

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
		refreshView.setText("刷新次数："+String.valueOf(hh.getRefreshCount()));
		pushView.setText("推送人数："+String.valueOf(hh.getPublishCount()));
		readView.setText("阅读人数"+String.valueOf(hh.getReadCount()));

		return view;
	}

}
