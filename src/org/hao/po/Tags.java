package org.hao.po;

import org.hao.common.BtnStyle;

public class Tags {

	private int tagId;
	private String tagName;
	private String style;
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
		//设置style
		style = BtnStyle.getRandomStyle();
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	
}
