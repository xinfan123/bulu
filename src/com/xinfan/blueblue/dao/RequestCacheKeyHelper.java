package com.xinfan.blueblue.dao;

import com.xinfan.msgbox.http.service.vo.param.UserMessageListParam;

public class RequestCacheKeyHelper {
	
	/**
	 * 收信列表缓存key
	 * @param param
	 * @return
	 */
	public static String generateRevMessageListCacheKey(UserMessageListParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getPageNo());
		return builder.toString();
	}
	
	
	public static String generateSendedMessageListCacheKey(UserMessageListParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getPageNo());
		return builder.toString();
	}
	

}
