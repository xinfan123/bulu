package com.xinfan.blueblue.test;

import java.util.ArrayList;
import java.util.List;

import com.xinfan.blueblue.activity.HuiHua;
import com.xinfan.blueblue.activity.rev.RevMessageSummaryVO;
import com.xinfan.blueblue.activity.send.SendMessageSummaryVO;

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


	public static List<RevMessageSummaryVO> getRevMessageData(int pageSize, int page) {
		List<RevMessageSummaryVO> list = new ArrayList<RevMessageSummaryVO>();
/*		int index = (pageSize * (page - 1) + 1);
		int max = pageSize * page;
		for (int i = index; i <= max; i++) {
			RevMessageSummaryVO map = new RevMessageSummaryVO();
			map.setIndex(index);
			map.setArea("地区：" + i);
			map.setContent("更多内容：" + i);
			map.setMoney("金额:" + i);
			map.setTitle("标题：" + i);
			map.setTime("时间："+i);

			list.add(map);
		}*/
		return list;
	}
	

	public static List<SendMessageSummaryVO> getSendMessageData(int pageSize, int page) {
		List<SendMessageSummaryVO> list = new ArrayList<SendMessageSummaryVO>();
	/*	int index = (pageSize * (page - 1) + 1);
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
		}*/
		return list;
	}

}
