package com.xinfan.blueblue.activity.send;

public class TimeListVo implements java.io.Serializable {

	private String id;

	private String text;

	public TimeListVo(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
