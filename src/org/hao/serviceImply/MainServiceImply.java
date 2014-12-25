package org.hao.serviceImply;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.hao.common.BtnStyle;
import org.hao.common.Parameter;
import org.hao.dao.OpenUIDao;
import org.hao.po.Admin;
import org.hao.po.DoubleValue;
import org.hao.po.ImageCode;
import org.hao.po.News;
import org.hao.po.ParameterPo;
import org.hao.po.SaveMessage;
import org.hao.po.Tags;
import org.hao.po.VisitCount;
import org.hao.service.MainService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
@Service("mainService")
public class MainServiceImply implements MainService,InitializingBean {

	@Resource(name="openuiDao")
	private OpenUIDao openuiDao;
	@Override
	public List<News> getNews(int page) {
		// TODO Auto-generated method stub
		return openuiDao.getNews(page, "", "", Parameter.PAGE_SIZE);
	}

	@Override
	public List<Tags> getTags() {
		// TODO Auto-generated method stub
		return openuiDao.getTags();
	}

	@Override
	public List<News> getByTag(String tag, int page) {
		// TODO Auto-generated method stub
		return openuiDao.getNews(page, "", tag, Parameter.PAGE_SIZE);
	}

	@Override
	public List<News> search(String keys, int page) {
		// TODO Auto-generated method stub
		//对搜索关键字进行高亮显示
		List<News> result  =  openuiDao.getNews(page, keys, "", Parameter.PAGE_SIZE);
		/*Iterator<News> it = result.iterator();
		while(it.hasNext()){
			News temp = it.next();
			String old = temp.getNewsTitle();
			temp.setNewsTitle(old.replaceAll(keys, "<span class='label label-important'>"+keys+"</span>"));
		}*/
		return result;
	}

	@Override
	public int getNewsPageCount(String key, int pagesize, int countType) {
		// TODO Auto-generated method stub
		int count = 0;
		switch(countType){
		case 0:{count=openuiDao.getAllCount(key, "");break;}
		case 1:{count=openuiDao.getAllCount("", key);break;}
		}
		
		return ((count + pagesize - 1 ) / pagesize);
	}

	@Override
	public Admin login(String username, String password, String idcode) {
		// TODO Auto-generated method stub
		
		Admin admin = openuiDao.getUser(username, password);
		
		//System.out.println("login:"+username+password+idcode+"admin"+admin.getAdminId());
		if(admin != null&& !"".equals(admin.getLoginname())&&!"".equals(admin.getPassword())&&Parameter.IDCode.equals(idcode))
			return admin;
		else
			return null;
	}

	@Override
	public News getNewsById(int id) {
		// TODO Auto-generated method stub
		return openuiDao.getNewsById(id);
	}

	@Override
	public Map<String,News> getAroundArticle(int id) {
		// TODO Auto-generated method stub
		Map<String,News> result = new HashMap<String,News>();
		List<News> news = openuiDao.getAroundNews(id);
		Iterator<News> it = news.iterator();
		while(it.hasNext()){
			News temp = it.next();
			result.put(temp.getAround(), temp);
		}
		return result;
	}

	@Override
	public String getNewsByPageForAjax(int page, int pagesize) {
		// TODO Auto-generated method stub
		//取文章list
		StringBuffer sb = new StringBuffer();
		List<News> list = openuiDao.getNews(page, "", "", pagesize);
		//拼装成需要的html代码
		Iterator<News> it = list.iterator();
		sb.append(" <table class='table' ><tr><td>序号</td><td>标题</td><td>上传时间</td><td>点击率</td><td>修改/删除</td></tr>");
		int i=1;
		while(it.hasNext()){
			News temp = it.next();
			sb.append("<tr><td>");
			sb.append(i++);
			sb.append("</td><td>");
			sb.append(temp.getNewsTitle());
			sb.append("</td><td>");
			sb.append(temp.getNewsDate());
			sb.append("</td><td>");
			sb.append(temp.getNewsClick());
			sb.append("</td><td><a href='#' class=''>修改</a>/<a href='#' class=''>删除</a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
/*
 * currentpage 为返回数据后的当前页码
 * 
 * @see org.hao.service.MainService#getPaginationForAJax(int, int)
 */
	@Override
	public String getPaginationForAJax(int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		int count = openuiDao.getAllCount("", "");
		int pagecount = (count + pagesize - 1 ) / pagesize;
		StringBuffer sb = new StringBuffer();
		if(currentpage==1){
			sb.append("<ul><li class='disabled'><a href='#'>&laquo;</a></li>");
		}
		else{
			sb.append("<ul><li><a href='#' onclick='articlelist(");
			sb.append(currentpage-1);
			sb.append(")'>&laquo;</a></li>");
		}
		for(int i=1;i<=pagecount;i++){
			if(i==currentpage){
				sb.append("<li><a href='#' class='disabled'>");
				sb.append(i);
				sb.append("</a></li>");
			}else{
				sb.append("<li><a href='#' class='active' onclick='articlelist(");
				sb.append(i);
				sb.append(")'>");
				sb.append(i);
				sb.append("</a></li>");
			}
		}
		if(currentpage==pagecount){
			sb.append("<li class='disabled'><a href='#'>&laquo;</a></li></ul>");
		}else{
			sb.append(" <li><a href='#' onclick='articlelist(");
			sb.append(currentpage+1);
			sb.append(")'>&raquo;</a></li></ul>");
		}
		
		return sb.toString();
	}

	@Override
	public void addNews(News news) {
		// TODO Auto-generated method stub
		openuiDao.addNews(news);
	}

	@Override
	public void removeNews(int newsId) {
		// TODO Auto-generated method stub
		openuiDao.removeNews(newsId);
	}

	@Override
	public void updateNews(News news) {
		// TODO Auto-generated method stub
		openuiDao.updateNews(news);
	}

	@Override
	public void updateParameter(ParameterPo ppo) {
		// TODO Auto-generated method stub
		//更新
		Parameter.HEADER = ppo.getHEADER();
		Parameter.FOOTER = ppo.getFOOTER();
		Parameter.introduce = ppo.getIntroduce();
		Parameter.email = ppo.getEmail();
		Parameter.weibo = ppo.getWeibo();
		Parameter.PAGE_SIZE = ppo.getPAGE_SIZE();
		Parameter.webhost = ppo.getWebhost();
		Parameter.DATA_SOURCE_TYPE = ppo.getDATA_SOURCE_TYPE();
		openuiDao.updateParameter(ppo);
		//return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("----main afterPropertiesSet 进行系统参数设置----");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		
		ParameterPo ppo  = openuiDao.getParameter();
		Parameter.HEADER = ppo.getHEADER();
		Parameter.FOOTER = ppo.getFOOTER();
		Parameter.introduce = ppo.getIntroduce();
		Parameter.email = ppo.getEmail();
		Parameter.PAGE_SIZE = ppo.getPAGE_SIZE();
		Parameter.DATA_SOURCE_TYPE = ppo.getDATA_SOURCE_TYPE();
		Parameter.PAGE_SIZE = ppo.getPAGE_SIZE()<=0?5:ppo.getPAGE_SIZE();
		Parameter.webhost = ppo.getWebhost();
		//Parameter.visit_stat_today  = openuiDao.getTodayVisitCount(today);
		//Parameter.visit_stat_total = openuiDao.getAllVisitCount();
	}

	@Override
	public void addClick(int newsid) {
		// TODO Auto-generated method stub
		openuiDao.addClick(newsid);
	}

	@Override
	public int addPraise(int newsid) {
		// TODO Auto-generated method stub
		openuiDao.addPraise(newsid);
		return openuiDao.getPraise(newsid);
	}

	@Override
	public ImageCode getImage() {
		// TODO Auto-generated method stub
		int width=60, height=20; 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		Graphics g = image.getGraphics(); 
		Random random = new Random(); 
		g.setColor(getRandColor(200,250)); 
		g.fillRect(0, 0, width, height); 
		g.setFont(new Font("Times New Roman",Font.PLAIN,18)); 
		g.setColor(getRandColor(160,200)); 
		for (int i=0;i<155;i++) 
		{ 
		int x = random.nextInt(width); 
		int y = random.nextInt(height); 
		int xl = random.nextInt(12); 
		int yl = random.nextInt(12); 
		g.drawLine(x,y,x+xl,y+yl); 
		} 
		String sRand=""; 
		for (int i=0;i<4;i++){ 
		String rand=String.valueOf(random.nextInt(10)); 
		sRand+=rand; 
		g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110))); 
		g.drawString(rand,13*i+6,16); 
		}
		g.dispose();
		//System.out.println("sRand:"+sRand);
		ImageCode imagecode = new ImageCode();
		imagecode.setStrcode(sRand);
		imagecode.setImg(image);
		return imagecode;
	}
		private Color getRandColor(int fc,int bc) 
		{ 
		Random random = new Random(); 
		if(fc>255) fc=255; 
		if(bc>255) bc=255; 
		int r=fc+random.nextInt(bc-fc); 
		int g=fc+random.nextInt(bc-fc); 
		int b=fc+random.nextInt(bc-fc); 
		return new Color(r,g,b); 
		}

		@Override
		public void addTag(String tagname) {
			// TODO Auto-generated method stub
			openuiDao.addTag(tagname);
		}

		@Override
		public void removeTag(int tagid) {
			// TODO Auto-generated method stub
			openuiDao.removeTag(tagid);
		}

		@Override
		public void updateTag(Tags tag) {
			// TODO Auto-generated method stub
			openuiDao.updateTag(tag);
		}

		@Override
		public void addVisitStat(VisitCount vc) {
			// TODO Auto-generated method stub
			openuiDao.updateVisitCount(vc);
		}

		/*@Override
		public int getAllVisitStat() {
			// TODO Auto-generated method stub
			
			return openuiDao.getAllVisitCount();
		}

		@Override
		public int getTodayVisitStat(String today) {
			// TODO Auto-generated method stub
			return openuiDao.getTodayVisitCount(today);
		}*/

		@Override
		public List<SaveMessage> getSaveMessageByArticle(int articleid) {
			// TODO Auto-generated method stub
			List<SaveMessage> result = openuiDao.getSaveMsgByArticleId(articleid);
			Iterator<SaveMessage> it = result.iterator();
			while(it.hasNext()){
				SaveMessage sm = it.next();
				sm.setBtnStyle(BtnStyle.getRandomStyle());
			}
			return result;
		}

		@Override
		public void SaveMessage(SaveMessage sm) {
			// TODO Auto-generated method stub
			openuiDao.SaveMsg(sm);
		}
		/**
		 * 取得留言总数
		 */
		@Override
		public int getSageMsgPageCount(int pageSize) {
			// TODO Auto-generated method stub
			int count = 0;
			count = openuiDao.getMsgStat();
			return ((count + pageSize - 1 ) / pageSize);
		}
		/**
		 * 根据页数返回留言列表
		 */
		@Override
		public List<SaveMessage> getSaveMessageList(int page, int pageSize) {
			// TODO Auto-generated method stub
			return openuiDao.getSaveMsgByList(page,pageSize);
		} 
		/**
		 * 删除留言
		 */
		@Override
		public void removeMsg(String msgid) {
			// TODO Auto-generated method stub
			openuiDao.removeMsg(msgid);
		}

		
		
		@Override
		public List<String> getBtnStyle() {
			// TODO Auto-generated method stub
			List<String> btnStyle = new ArrayList<String>();
			return btnStyle;
		}
		/**
		 * sin 2014-11-3
		 * parameter date 指定日期  为""时取出全部
		 * 文章总数统计
		 */
		/*@Override
		public int getArticleStat(String date) {
			// TODO Auto-generated method stub
			if("".equals(date)) date = "%%";
			return openuiDao.getArticleStat(date);
		}*/
		/**
		 * sin 2014-11-3
		 * parameter date 指定日期  为""时取出全部
		 * 留言总数统计
		 */
		/*@Override
		public int getMsgStat(String date) {
			// TODO Auto-generated method stub
			if("".equals(date)) date = "%%";
			return openuiDao.getMsgStat(date);
		}*/

		@Override
		public String getHotNews(int top) {
			// TODO Auto-generated method stub
			StringBuffer sb = new StringBuffer();
			List<News> result = openuiDao.getHotNews(top);
			Iterator<News> iterator = result.iterator();
			while(iterator.hasNext()){
				News news = iterator.next();
				sb.append("<a href='");
				sb.append(Parameter.webhost);
				sb.append("/hao/blog/show/");
				sb.append(news.getNewsID());
				sb.append("' target='_blank'>[");
				sb.append(news.getNewsType_str());
				sb.append("]&nbsp;");
				sb.append(news.getNewsTitle());
				sb.append("&nbsp;</a><small>(");
				sb.append(news.getNewsDate());
				sb.append(")</small><br/>");
			}
			
			

			return sb.toString();
		}
		/**
		 * sin 2014-12-15
		 * 取得最新留言
		 */
		@Override
		public String getNewMsg(int top) {
			// TODO Auto-generated method stub
			StringBuffer sb = new StringBuffer();
			List<SaveMessage> result = openuiDao.getNewMsg(top);
			
			Iterator<SaveMessage> it = result.iterator();
			while(it.hasNext()){
				SaveMessage sm = it.next();
				sb.append("<a href='");
				sb.append(Parameter.webhost);
				sb.append("/hao/blog/show/");
				sb.append(sm.getArticleid());
				sb.append("' target='_blank'>");
				sb.append(sm.getMessage());
				sb.append("&nbsp;</a><small>(");
				sb.append(sm.getSavedate());
				sb.append(")</small><br/>");
			}
			return sb.toString();
		}

		@Override
		public String getWebStat(String date) {
			// TODO Auto-generated method stub
			StringBuffer sb = new StringBuffer();
			List<DoubleValue> result = openuiDao.getWebStat(date);
			DoubleValue todayVisit = new DoubleValue();
			todayVisit.setValueA(Parameter.visit_stat_today);
			todayVisit.setValueB("今日访问量：");
			result.add(todayVisit);
			Iterator<DoubleValue> it = result.iterator();
			while(it.hasNext()){
				DoubleValue dv = it.next();
				sb.append(dv.getValueB());
				sb.append(dv.getValueA());
				sb.append("<br>");
			}
			
			return sb.toString();
		}

		
		

}
