package com.xinfan.blueblue.activity.send;

public class AreaListVo implements java.io.Serializable {

	private String id;

	private int index;

	private String text;

	public String getId() {
		return id;
	}
	
	

	public AreaListVo(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}



	public void setId(String id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
