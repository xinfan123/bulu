package com.xinfan.blueblue.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.test.MessageListDataService;
import com.xinfan.blueblue.util.BeanUtils;
import com.xinfan.blueblue.vo.ContactVo;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserLinkmanListParam;
import com.xinfan.msgbox.http.service.vo.result.UserLinkmanListResult;
import com.xinfan.msgbox.http.service.vo.result.UserLinkmanResult;

public class ContactListView extends ListView implements OnScrollListener, OnItemClickListener {

	public ArrayList<ContactVo> list = new ArrayList<ContactVo>();

	public Contact2Adapter ad;

	Activity context;

	// 底部footer
	View footer;
	// 当前页数
	int page = 1;
	// 每页显示数
	int pageSize = 10;
	// 是否加载数据中
	boolean isLoading = false;
	// Toast显示状态
	boolean isToast = false;

	boolean isfirst = true;

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

	public void setContext(Activity context) {
		this.context = context;
	}

	public void init() {
		// list = (ArrayList) MessageListDataService.getData(pageSize, 1);
		ad = new Contact2Adapter(this.context, list);
		addFooterView(footer);
		setAdapter(ad);
		// removeFooterView(footer);
		setOnItemClickListener(this);
		setOnScrollListener(this);
		load();
	}

	public void load() {

		if (isfirst) {
			isfirst = false;
		} else {
			addFooterView(footer);
		}

		isLoading = true;

		Request request = new Request(FunIdConstants.GET_USER_LINKMAN_LIST);
		UserLinkmanListParam param = new UserLinkmanListParam();
		param.setUserId(LoginUserContext.getUserId(context));
		param.setPageNo(page);
		param.setPageSize(pageSize);

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(context, request, new ObserverCallBack() {

			public void call(Request data) {

				UserLinkmanListResult result = (UserLinkmanListResult) data.getResult();
				List<UserLinkmanResult> rList = result.getList();
				List<ContactVo> addList = (ArrayList<ContactVo>) BeanUtils.copyList(rList, ContactVo.class);

				if (addList.size() > 0) {
					page++;
				}

				handler.sendMessage(handler.obtainMessage(1, addList));
			}
		});

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
				this.load();
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
