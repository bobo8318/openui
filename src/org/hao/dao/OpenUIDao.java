package org.hao.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hao.po.Admin;
import org.hao.po.DoubleValue;
import org.hao.po.News;
import org.hao.po.ParameterPo;
import org.hao.po.SaveMessage;
import org.hao.po.Tags;
import org.hao.po.VisitCount;
import org.springframework.stereotype.Repository;
@Repository("openuiDao")
public class OpenUIDao extends BaseDao{

	//private NoSQlDataDource nsdd = new NoSQlDataDource();
	@SuppressWarnings("unchecked")
	public Admin getUser(String username, String pwd){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", pwd);

		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getUser",map);

	}
	
	@SuppressWarnings("unchecked")
	public List<News> getNews(int page, String keys, String tags, int pagesize)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newstitle", keys);
		map.put("newstags", tags);
		map.put("start", (page-1)*pagesize);
		map.put("size", pagesize);
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getNews",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<SaveMessage> getSaveMsgByList(int page, int pagesize)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (page-1)*pagesize);
		map.put("size", pagesize);
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getSaveMsgByPage",map);
	}
	@SuppressWarnings("unchecked")
	public void removeMsg(String msgid)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msgid", msgid);
		this.writerSqlSession.delete("org.hao.dao.sqlmap.BlogMapper.removeMsg", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Tags> getTags()
	{
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.selectAllTags");
	}
	/**
	 * 
	 * @param counttype 0 search 1 tag
	 * @param tags
	 * @param counttype
	 * @return
	 */
	public int getAllCount(String keys, String tags)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newstitle", keys);
		map.put("newstags", tags);
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getAllCount",map);
	}
	
	
	@SuppressWarnings("unchecked")
	public News getNewsById(int id){
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getNewsById",id);
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAroundNews(int id){
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getAroundNews",id);
	}
	
	public void addNews(News news){
		 this.writerSqlSession.insert("org.hao.dao.sqlmap.BlogMapper.addNews", news);
	}
	
	public void removeNews(int newsid){
		 this.writerSqlSession.delete("org.hao.dao.sqlmap.BlogMapper.removeNews", newsid);
	}
	
	public void updateNews(News news){
		
		 this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.updateNews",news);
	}
	
	public void updateParameter(ParameterPo parameter){
		
		 this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.updateParameter",parameter);
	}
	public ParameterPo getParameter(){
		
		 return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getParameter");
	}
	public void addClick(int newsid){
		 this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.addClick",newsid);
	}
	public void addPraise(int newsid){
		this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.addPraise",newsid);
	}
	public int getPraise(int newsid){
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getPraise",newsid);
	}
	
	public void addTag(String tagname){
		 this.writerSqlSession.insert("org.hao.dao.sqlmap.BlogMapper.addTag",tagname);
	}
	public void removeTag(int tagid){
		 this.writerSqlSession.delete("org.hao.dao.sqlmap.BlogMapper.removeTag",tagid);
	}
	public void updateTag(Tags tag){
		 this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.updateTag",tag);
	}
	//public void insertVisitNewDate(String date){
		// this.writerSqlSession.insert("org.hao.dao.sqlmap.BlogMapper.updateVisitCount",vc);
	//}
	public void updateVisitCount(VisitCount vc){
		 this.writerSqlSession.update("org.hao.dao.sqlmap.BlogMapper.insertVisitNewDate",vc);
	}
	
	/*public int getAllVisitCount(){
		 return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getAllVisitCount");
	}
	
	public int getTodayVisitCount(String today){
		//判断是否已经存在今日访问统计项
		 if(Integer.parseInt(this.writerSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getTodayVisit",today).toString()) == 0){
			//System.out.println("create table!");
			 this.writerSqlSession.insert("org.hao.dao.sqlmap.BlogMapper.addTodayVisit", today);
			 return 0;
		 }
		 else
			return  this.writerSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getTodayVisitCount",today);
	}*/
	
	public List<SaveMessage> getSaveMsgByArticleId(int articleId){
		 return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getSaveMsgByArticleId",articleId);
	}
	
	public void SaveMsg(SaveMessage message){
		 this.writerSqlSession.insert("org.hao.dao.sqlmap.BlogMapper.saveMessage",message);
	}
	
	/*public int getArticleStat(String date){
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getArticleStat",date);
	}
	*/
	public int getMsgStat(){
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.BlogMapper.getMsgStat");
		
	}
	
	public List<SaveMessage> getNewMsg(int top){
		 return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getNewMsg",top);
	}
	public List<News> getHotNews(int top){
		 return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getHotNews",top);
	}
	
	public List<DoubleValue> getWebStat(String date){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date+"%");
		 return this.readSqlSession.selectList("org.hao.dao.sqlmap.BlogMapper.getWebStat",map);
	}
}
