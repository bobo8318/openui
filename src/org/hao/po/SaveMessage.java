package org.hao.po;

public class SaveMessage {

	private int articleid;
	private int messageid;
	private String message;
	private String savedate;
	private String imagecode;
	private String btnStyle;
	
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSavedate() {
		return savedate;
	}
	public void setSavedate(String savedate) {
		this.savedate = savedate;
	}
	public String getImagecode() {
		return imagecode;
	}
	public void setImagecode(String imagecode) {
		this.imagecode = imagecode;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getBtnStyle() {
		return btnStyle;
	}
	public void setBtnStyle(String btnStyle) {
		this.btnStyle = btnStyle;
	}
	
	
	
}
