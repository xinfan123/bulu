package com.xinfan.blueblue.dao;

import com.xinfan.msgbox.http.service.vo.param.BaseParam;
import com.xinfan.msgbox.http.service.vo.param.MessageRevParam;
import com.xinfan.msgbox.http.service.vo.param.MessageUnReadCountParam;
import com.xinfan.msgbox.http.service.vo.param.SendMessageParam;
import com.xinfan.msgbox.http.service.vo.param.UserAvatarParam;
import com.xinfan.msgbox.http.service.vo.param.UserLinkmanListParam;
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
	
	public static String generateContactListCacheKey(UserLinkmanListParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getPageNo());
		return builder.toString();
	}
	
	public static String generateSeeRevMessageCacheKey(MessageRevParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getMsgId());
		return builder.toString();
	}
	
	public static String generateSeeSendMessageCacheKey(SendMessageParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getMsgId());
		return builder.toString();
	}
	
	public static String generateThemeSetListCacheKey(BaseParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId());
		return builder.toString();
	}
	
	public static String generateAccountInfoCacheKey(BaseParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId());
		return builder.toString();
	}
	
	public static String generateSystemSetCacheKey(BaseParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId());
		return builder.toString();
	}
	
	public static String generateAvatarCacheKey(UserAvatarParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId()).append(param.getAvatar());
		return builder.toString();
	}
	
	public static String generateUnReadMessageCountCacheKey(MessageUnReadCountParam param) {
		StringBuilder builder = new StringBuilder();
		builder.append(param.getFunId()).append(param.getUserId());
		return builder.toString();
	}
	
}
