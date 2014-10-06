package com.xinfan.blueblue.activity;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinfan.blueblue.vo.ContactVo;

public class Contact2Adapter extends BaseAdapter {
	private Context context;
	private ArrayList<ContactVo> list = new ArrayList<ContactVo>();

	public Contact2Adapter(Context context, ArrayList<ContactVo> list) {
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
		ContactVo hh = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
		}

		ImageView pic = (ImageView) view.findViewById(R.id.tx1);
		TextView name = (TextView) view.findViewById(R.id.tx2);

		name.setText(hh.getMark());
		view.setTag(hh);

		return view;
	}

}
