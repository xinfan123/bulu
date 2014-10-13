package com.xinfan.blueblue.vo;

import java.util.Date;

public class ThemeVo {
	private Long id;

	private Long userId;

	private String userSent;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserSent() {
		return userSent;
	}

	public void setUserSent(String userSent) {
		this.userSent = userSent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
