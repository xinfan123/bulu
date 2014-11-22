package com.xinfan.blueblue.activity.rev;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.util.DateUtil;

public class RevMessageListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<RevMessageSummaryVO> list = new ArrayList<RevMessageSummaryVO>();

	public RevMessageListAdapter(Context context, ArrayList<RevMessageSummaryVO> list) {
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
		RevMessageSummaryVO hh = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.rev_message_list_item, parent, false);
			// h.pic = (ImageView)view.findViewById(R.id.l1);
			view.setTag(hh);
		}

		TextView rev_message_time = (TextView) view.findViewById(R.id.rev_message_time);
		TextView rev_message_content = (TextView) view.findViewById(R.id.rev_message_content);
		TextView rev_message_title = (TextView) view.findViewById(R.id.rev_message_title);
		TextView rev_message_amount = (TextView) view.findViewById(R.id.rev_message_amount);
		TextView rev_message_send_type = (TextView) view.findViewById(R.id.rev_message_send_type);
		TextView rev_message_user_name = (TextView) view.findViewById(R.id.rev_message_user_name);
		TextView rev_message_credit = (TextView) view.findViewById(R.id.rev_message_credit);

		ImageView rev_message_last_time_image = (ImageView) view.findViewById(R.id.rev_message_last_time_image);
		TextView rev_message_last_time = (TextView) view.findViewById(R.id.rev_message_last_time);

		ImageView rev_message_user_photo = (ImageView) view.findViewById(R.id.rev_message_user_photo);

		String title = hh.getTitle();
		if (hh.getTitle().length() > 20) {
			title = hh.getTitle().substring(0, 20) + "...";
		}

		String content = hh.getContext();
		if (hh.getContext().length() > 30) {
			content = hh.getContext().substring(0, 30) + "...";
		}

		BizUtils.showAvatar(context, rev_message_user_photo, hh.getSendUserAvatar());
		// h.pic.setImageResource(Integer.parseInt(hh.getTxPath()));
		rev_message_time.setText(DateUtil.formateSimple(hh.getPubishTime()));
		rev_message_content.setText(content);
		rev_message_title.setText(title);
		rev_message_amount.setText(hh.getAmountStatus() == 1 ? "有偿:" + hh.getAmount() + "元" : "无偿消息");
		rev_message_send_type.setText("距离:" + BizUtils.calGps2mToString(hh.getGpsx(), hh.getGpsy()));
		rev_message_user_name.setText(hh.getSendUserName());
		rev_message_credit.setText("信用:" + hh.getSendUserCredit());

		String[] lastTimes = BizUtils.calUsefulTime(hh.getPubishTime(), hh.getDurationTime());
		if ("1".equals(lastTimes[0])) {
			rev_message_last_time.setText(lastTimes[1]);
			rev_message_last_time_image.setImageResource(R.drawable.time_01);
		} else {
			rev_message_last_time.setText(lastTimes[1]);
			rev_message_last_time_image.setImageResource(R.drawable.time_02);
		}

		return view;
	}
}
