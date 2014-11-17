package com.xinfan.blueblue.activity;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinfan.blueblue.activity.common.DisplayImageOptionsConfig;
import com.xinfan.blueblue.util.BizUtils;
import com.xinfan.blueblue.vo.LinkmanVo;

public class LinkmanListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<LinkmanVo> list = new ArrayList<LinkmanVo>();

	public LinkmanListAdapter(Context context, ArrayList<LinkmanVo> list) {
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
		LinkmanVo hh = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
		}

		ImageView avatar = (ImageView) view.findViewById(R.id.linkman_avatar);
		TextView mark = (TextView) view.findViewById(R.id.linkman_mark);

		BizUtils.showAvatar(context, avatar, hh.getLinkAvatar());

		mark.setText(hh.getLinkRemark());
		view.setTag(hh);

		return view;
	}

}
