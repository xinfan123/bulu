package com.xinfan.blueblue.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.xinfan.blueblue.test.MessageListDataService;
import com.xinfan.blueblue.vo.ContactVo;

public class ContactListView extends ListView implements OnScrollListener, OnItemClickListener {

	public ArrayList<ContactVo> list = new ArrayList<ContactVo>();

	public Contact2Adapter ad;

	Context context;

	// 底部footer
	View footer;
	// 当前页数
	int page = 0;
	// 每页显示数
	int pageSize = 10;
	// 是否加载数据中
	boolean isLoading = false;
	// Toast显示状态
	boolean isToast = false;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == 1) {
				// 得到消息中的数据

				List addList = (List) msg.obj;
				if (addList == null || addList.isEmpty()) {
					Toast.makeText(ContactListView.this.getContext(), "没有更多的数据了...", 0).show();
				} else {
					list.addAll(addList);
					// 告诉适配器，数据变化了，从新加载listview
					ad.notifyDataSetChanged();
					// 当前页数增加
					page++;
				}

				isLoading = false;
				// 移除底部footer
				ContactListView.this.removeFooterView(footer);
				ContactListView.this.setVisibility(View.VISIBLE);

			} else if (msg.what == 0) {
				// 设置加载中为false
				isToast = false;
			}
		}
	};

	public ContactListView(Context context) {
		super(context);
	}

	public ContactListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ContactListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = (ArrayList) list;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void loadData() {
		// list = (ArrayList) MessageListDataService.getData(pageSize, 1);
		ad = new Contact2Adapter(this.context, list);

		addFooterView(footer);
		setAdapter(ad);
		// removeFooterView(footer);
		setOnItemClickListener(this);
		setOnScrollListener(this);
		getFirstData();

	}

	private void getFirstData() {

		isLoading = true;

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List addList = MessageListDataService.getContactData(pageSize, 1);
				handler.sendMessage(handler.obtainMessage(1, addList));
			}
		}.start();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// 如果正在加载，就return，防止加载多次
		if (isLoading)
			return;
		// 得到listview中显示在界面底部的id
		int lastItemid = getLastVisiblePosition();
		// 如果是listview中显示在界面底部的id=滚动条中Item总数，说明滑动到底部了，并且当前页<=总页数
		if ((lastItemid + 1) == totalItemCount) {
			// 设置正在加载中
			isLoading = true;
			if (totalItemCount > 0) {
				// 现在底部footer
				addFooterView(footer);
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						List list = MessageListDataService.getContactData(pageSize, page + 1);
						handler.sendMessage(handler.obtainMessage(1, list));
					}
				}.start();
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.instance, ContactInfoActivity.class);

		ContactVo vo = (ContactVo) list.get(arg2);

		Bundle data = new Bundle();
		data.putSerializable("vo", vo);

		vo.setIndex(arg2);
		intent.putExtras(data);

		this.getContext().startActivity(intent);
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public View getFooter() {
		return footer;
	}

	public void setFooter(View footer) {
		this.footer = footer;
	}

}
