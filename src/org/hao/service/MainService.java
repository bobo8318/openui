package org.hao.service;

import java.util.List;
import java.util.Map;

import org.hao.po.Admin;
import org.hao.po.ImageCode;
import org.hao.po.News;
import org.hao.po.ParameterPo;
import org.hao.po.SaveMessage;
import org.hao.po.Tags;
import org.hao.po.VisitCount;

public interface MainService {

	public Admin login(String username, String password, String idcode);
	
	public List<News> getNews(int Page);
	public List<Tags> getTags();
	
	public News getNewsById(int id);
	
	public List<News> search(String keys, int page);
	public List<News> getByTag(String tag, int page);
	
	public Map<String,News> getAroundArticle(int id);
	public String getNewsByPageForAjax(int page, int pagesize);
	public String getPaginationForAJax(int currentpage, int pagesize);
	/**
	 * 
	 * @param countType 0 search type;1 tag type;
	 * @param pagesize
	 * @param countType
	 * @return
	 */
	public int getNewsPageCount(String key,int pagesize, int countType);
	
	public void removeNews(int newsId);
	public void updateNews(News news);
	public void addNews(News news);
	
	public void removeTag(int tagid);
	public void updateTag(Tags tag);
	public void addTag(String tagname);
	
	public void updateParameter(ParameterPo ppo);
	
	//添加点击率
	public void addClick(int newsid);
	//点赞
	public int addPraise(int newsid);
	//验证码
	public ImageCode  getImage();
	
	public void addVisitStat(VisitCount vc);
	
	//public int getTodayVisitStat(String today);
	//public int getAllVisitStat();
	//public int getArticleStat(String date);
	//public int getMsgStat(String date);
	
	public List<SaveMessage> getSaveMessageByArticle(int articleid);
	public List<SaveMessage> getSaveMessageList(int page, int pageSize);
	public int getSageMsgPageCount(int pageSize);
	public void removeMsg(String msgid);
	public void SaveMessage(SaveMessage sm);
	
	public List<String> getBtnStyle();
	
	//热门文章
	public String getHotNews(int top);
	//最新留言
	public String getNewMsg(int top);
	
	public String getWebStat(String date);
}
