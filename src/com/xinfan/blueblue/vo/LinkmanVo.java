package com.xinfan.blueblue.vo;

import java.util.Date;

public class LinkmanVo implements java.io.Serializable {
	private Long userId;

	private String linkUserName;

	private String linkAvatar;

	private Long linkUserId;

	private String linkRemark;

	private Date createTime;

	private String userName;

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public String getLinkAvatar() {
		return linkAvatar;
	}

	public void setLinkAvatar(String linkAvatar) {
		this.linkAvatar = linkAvatar;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLinkUserId() {
		return linkUserId;
	}

	public void setLinkUserId(Long linkUserId) {
		this.linkUserId = linkUserId;
	}

	public String getLinkRemark() {
		return linkRemark;
	}

	public void setLinkRemark(String linkRemark) {
		this.linkRemark = linkRemark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
