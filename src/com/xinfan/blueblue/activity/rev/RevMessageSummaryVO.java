package com.xinfan.blueblue.activity.rev;

import java.util.Date;

public class RevMessageSummaryVO implements java.io.Serializable {

	private Long msgId;

	private String title;

	private String context;

	private Date validTime;

	private Integer durationTime;

	private Integer sendType;

	private String sendArea;

	private String sendDistance;

	private Integer amountStatus;

	private Long amount;

	private Integer msgStatus;

	private Long createUserId;

	private String gpsy;

	private String gpsx;

	private String reginCode;

	private Date createTime;

	private Long publishId;

	private Long receivedUserid;

	private Long sendUserid;

	private Integer sendNewReply;

	private Integer receivedNewReply;

	private Integer receivedStaus;

	private Date readTime;

	private Date deleteTime;

	private Date pubishTime;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Integer getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getSendArea() {
		return sendArea;
	}

	public void setSendArea(String sendArea) {
		this.sendArea = sendArea;
	}

	public String getSendDistance() {
		return sendDistance;
	}

	public void setSendDistance(String sendDistance) {
		this.sendDistance = sendDistance;
	}

	public Integer getAmountStatus() {
		return amountStatus;
	}

	public void setAmountStatus(Integer amountStatus) {
		this.amountStatus = amountStatus;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getGpsy() {
		return gpsy;
	}

	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
	}

	public String getGpsx() {
		return gpsx;
	}

	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}

	public String getReginCode() {
		return reginCode;
	}

	public void setReginCode(String reginCode) {
		this.reginCode = reginCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public Long getReceivedUserid() {
		return receivedUserid;
	}

	public void setReceivedUserid(Long receivedUserid) {
		this.receivedUserid = receivedUserid;
	}

	public Long getSendUserid() {
		return sendUserid;
	}

	public void setSendUserid(Long sendUserid) {
		this.sendUserid = sendUserid;
	}

	public Integer getSendNewReply() {
		return sendNewReply;
	}

	public void setSendNewReply(Integer sendNewReply) {
		this.sendNewReply = sendNewReply;
	}

	public Integer getReceivedNewReply() {
		return receivedNewReply;
	}

	public void setReceivedNewReply(Integer receivedNewReply) {
		this.receivedNewReply = receivedNewReply;
	}

	public Integer getReceivedStaus() {
		return receivedStaus;
	}

	public void setReceivedStaus(Integer receivedStaus) {
		this.receivedStaus = receivedStaus;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Date getPubishTime() {
		return pubishTime;
	}

	public void setPubishTime(Date pubishTime) {
		this.pubishTime = pubishTime;
	}

}
