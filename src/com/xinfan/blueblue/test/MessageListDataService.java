package com.xinfan.blueblue.test;

import java.util.ArrayList;
import java.util.List;

import com.xinfan.blueblue.activity.HuiHua;
import com.xinfan.blueblue.activity.rev.RevMessageVo;
import com.xinfan.blueblue.activity.send.SendMessageVo;
import com.xinfan.blueblue.vo.ContactVo;

public class MessageListDataService {

	public static List<HuiHua> getData(int pageSize, int page) {
		List<HuiHua> list = new ArrayList<HuiHua>();
		int index = (pageSize * (page - 1) + 1);
		int max = pageSize * page;
		for (int i = index; i <= max; i++) {
			HuiHua map = new HuiHua();
			map.setName1("姓名:" + i);
			map.setLastTime("时间：" + i);
			map.setLastContent("内容:" + i);
			list.add(map);
		}
		return list;
	}

	public static List<ContactVo> getContactData(int pageSize, int page) {
		List<ContactVo> list = new ArrayList<ContactVo>();
		int index = (pageSize * (page - 1) + 1);
		int max = pageSize * page;
		for (int i = index; i <= max; i++) {
			ContactVo map = new ContactVo();
			map.setId(String.valueOf(index));
			map.setAccountId("帐号:" + i);
			map.setMark("备注:" + i);
			map.setUserid("userid" + i);
			map.setUsername("用户名:" + i);
			map.setCredit("10");
			list.add(map);
		}
		return list;
	}
	
	

	public static List<RevMessageVo> getRevMessageData(int pageSize, int page) {
		List<RevMessageVo> list = new ArrayList<RevMessageVo>();
		int index = (pageSize * (page - 1) + 1);
		int max = pageSize * page;
		for (int i = index; i <= max; i++) {
			RevMessageVo map = new RevMessageVo();
			map.setIndex(index);
			map.setArea("地区：" + i);
			map.setContent("更多内容：" + i);
			map.setMoney("金额:" + i);
			map.setTitle("标题：" + i);
			map.setTime("时间："+i);

			list.add(map);
		}
		return list;
	}
	

	public static List<SendMessageVo> getSendMessageData(int pageSize, int page) {
		List<SendMessageVo> list = new ArrayList<SendMessageVo>();
		int index = (pageSize * (page - 1) + 1);
		int max = pageSize * page;
		for (int i = index; i <= max; i++) {
			SendMessageVo map = new SendMessageVo();
			map.setIndex(index);
			map.setArea("地区：" + i);
			map.setContent("更多内容：" + i);
			map.setMoney("金额:" + i);
			map.setTitle("标题：" + i);
			map.setTime("时间："+i);

			list.add(map);
		}
		return list;
	}

}
