package com.xinfan.blueblue.activity.send;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.BizUtils;

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
		TextView sended_msg_time = (TextView) view.findViewById(R.id.sended_msg_time);
		TextView moreView = (TextView) view.findViewById(R.id.message_more);
		TextView refreshView = (TextView) view.findViewById(R.id.mesage_refresh_count);
		TextView pushView = (TextView) view.findViewById(R.id.message_push_count);
		TextView readView = (TextView) view.findViewById(R.id.mesage_read_count);
		ImageView sended_msg_time_image = (ImageView) view.findViewById(R.id.sended_msg_time_image);

		String title = hh.getTitle();
		if (hh.getTitle().length() > 20) {
			title = hh.getTitle().substring(0, 20) + "...";
		}

		String content = hh.getContext();
		if (hh.getContext().length() > 30) {
			content = hh.getContext().substring(0, 30) + "...";
		}
		
		
		String[] lastTimes = BizUtils.calUsefulTime(hh.getPublishTime(), hh.getDurationTime());
		if ("1".equals(lastTimes[0])) {
			sended_msg_time.setText(lastTimes[1]);
			sended_msg_time_image.setImageResource(R.drawable.time_02);
		} else {
			sended_msg_time.setText(lastTimes[1]);
			sended_msg_time_image.setImageResource(R.drawable.time_01);
		}
		
		titleView.setText(title);
		moreView.setText(content);
		refreshView.setText("刷新次数："+String.valueOf(hh.getRefreshCount()));
		pushView.setText("推送人数："+String.valueOf(hh.getPublishCount()));
		readView.setText("阅读人数："+String.valueOf(hh.getReadCount()));

		return view;
	}

}
