package com.xinfan.blueblue.activity.rev;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class RevMessageListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<RevMessageVo> list = new ArrayList<RevMessageVo>();

	public RevMessageListAdapter(Context context, ArrayList<RevMessageVo> list) {
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
		RevMessageVo hh = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.liaotian, parent, false);
			// h.pic = (ImageView)view.findViewById(R.id.l1);
			view.setTag(hh);
		}

		TextView name = (TextView) view.findViewById(R.id.name);
		TextView time = (TextView) view.findViewById(R.id.time);
		TextView lastmsg = (TextView) view.findViewById(R.id.lastmsg);

		// h.pic.setImageResource(Integer.parseInt(hh.getTxPath()));
		name.setText(hh.getTitle());
		time.setText(hh.getTime());
		lastmsg.setText(hh.getContent());

		return view;
	}

}
