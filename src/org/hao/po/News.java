package org.hao.po;

import java.util.ArrayList;
import java.util.List;

import org.hao.common.Parameter;
import org.hao.common.StringParse;
import org.hao.common.Parameter.NEWS_TYPE;


public class News {

	private int newsID;
	private String newsTitle;
	private String newsDate;
	private String newsTags;
	private List<Tags> newsTagsList;
	private int newsClick;
	private int newsPraise;
	private String source;
	private String newsContent;
	private String newsShortContent;
	private String around;
	private int newsType;
	private String newsType_str = "文章";
	
	public String getAround() {
		return around;
	}
	public void setAround(String around) {
		this.around = around;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public int getNewsID() {
		return newsID;
	}
	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsTags() {
		return newsTags;
	}
	public void setNewsTags(String newsTags) {
		this.newsTags = newsTags;
		this.newsTagsList = new ArrayList<Tags>();
		if(null!=newsTags&&!"".equals(newsTags)){
			String[] strArray = newsTags.split(",");
			for(int i=0;i<strArray.length;i++){
				Tags tag = new Tags();
				tag.setTagName(strArray[i]);
				this.newsTagsList.add(tag);
			}
			 
		}
	}
	public int getNewsClick() {
		return newsClick;
	}
	public void setNewsClick(int newsClick) {
		this.newsClick = newsClick;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getNewsPraise() {
		return newsPraise;
	}
	public void setNewsPraise(int newsPraise) {
		this.newsPraise = newsPraise;
	}
	public String getNewsShortContent() {
		return newsShortContent;
	}
	public void setNewsShortContent(String newsShortContent) {
		this.newsShortContent = newsShortContent;
	}
	public List<Tags> getNewsTagsList() {
		return newsTagsList;
	}
	public void setNewsTagsList(List<Tags> newsTagsList) {
		this.newsTagsList = newsTagsList;
	}
	public void setNewsType(int newsType) {
		this.newsType = newsType;
		
		switch(newsType){
		case 1:this.newsType_str="文章";newsShortContent = StringParse.Html2Text(newsContent);break;
		case 2:this.newsType_str="视频";newsShortContent = newsContent;break;
		case 3:this.newsType_str="图片";newsShortContent = newsContent;break;
		default:this.newsType_str="文章";newsShortContent = StringParse.Html2Text(newsContent);break;
		}
	}
	public int getNewsType() {
		return newsType;
	}
	public String getNewsType_str() {
		return newsType_str;
	}
	public void setNewsType_str(String newsTypeStr) {
		newsType_str = newsTypeStr;
	}
	
	
}
