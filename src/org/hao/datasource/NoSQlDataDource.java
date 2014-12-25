package org.hao.datasource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hao.po.Admin;
import org.hao.po.News;
import org.hao.po.Tags;

public class NoSQlDataDource {

	private static List<News> newslist;
	private static List<Tags> tagslist;
	private static List<Admin> adminlist;
	public NoSQlDataDource(){
		System.out.println("init NoSQlDataDource...........");
		newslist = new ArrayList<News>();
		News news1 = new News();
		News news2 = new News();
		News news3 = new News();
		News news4 = new News();
		News news5 = new News();
		News news6 = new News();
		
		news1.setNewsID(1);
		news1.setNewsTitle("jquery 介绍");
		news1.setNewsContent("  Jquery是继prototype之后又一个优秀的Javascript框架。它是轻量级的js库 ，它兼容CSS3，还兼容各种浏览器（IE 6.0+, FF 1.5+, Safari 2.0+, Opera 9.0+），jQuery2.0及后续版本将不再支持IE6/7/8浏览器。jQuery使用户能更方便地处理HTML（标准通用标记语言下的一个应用）、events、实现动画效果，并且方便地为网站提供AJAX交互。jQuery还有一个比较大的优势是，它的文档说明很全，而且各种应用也说得很详细，同时还有许多成熟的插件可供选择。jQuery能够使用户的html页面保持代码和html内容分离，也就是说，不用再在html里面插入一堆js来调用命令了，只需定义id即可<br><a href='http://code.jquery.com/jquery-1.11.0.js'>点击下载(右键另存为)</a>");
		news1.setNewsDate("2014-3-7");
		news1.setNewsTags("jquery");
		news1.setNewsClick(1);
		
		news2.setNewsID(2);
		news2.setNewsTitle("css3 is very good");
		news2.setNewsContent("hello css3 css3 is very good!");
		news2.setNewsDate("2014-3-7");
		news2.setNewsTags("css");
		news2.setNewsClick(1);
		
		news3.setNewsID(3);
		news3.setNewsTitle("html5 html5");
		news3.setNewsContent("  HTML5是用于取代1999年所制定的 HTML 4.01 和 XHTML 1.0 标准的 HTML [1]（标准通用标记语言下的一个应用）标准版本；现在仍处于发展阶段，但大部分浏览器已经支持某些 HTML5 技术。HTML 5有两大特点：首先，强化了 Web 网页的表现性能。其次，追加了本地数据库等 Web 应用的功能。广义论及HTML5时，实际指的是包括HTML、CSS和JavaScript在内的一套技术组合。它希望能够减少浏览器对于需要插件的丰富性网络应用服务（plug-in-based rich internet application，RIA)，如Adobe Flash、Microsoft Silverlight，与Oracle JavaFX的需求，并且提供更多能有效增强网络应用的标准集。");
		news3.setNewsDate("2014-3-7");
		news3.setNewsTags("html5");
		news3.setNewsClick(1);
		
		news4.setNewsID(4);
		news4.setNewsTitle("jquery is great");
		news4.setNewsContent("jquery jquery is great!");
		news4.setNewsDate("2014-3-7");
		news4.setNewsTags("jquery");
		news4.setNewsClick(1);
		
		news5.setNewsID(5);
		news5.setNewsTitle("初学css");
		news5.setNewsContent("  CSS目前最新版本为CSS3，是能够真正做到网页表现与内容分离的一种样式设计语言。相对于传统HTML的表现而言，CSS能够对网页中的对象的位置排版进行像素级的精确控制，支持几乎所有的字体字号样式，拥有对网页对象和模型样式编辑的能力，并能够进行初步交互设计，是目前基于文本展示最优秀的表现设计语言。CSS能够根据不同使用者的理解能力，简化或者优化写法，针对各类人群，有较强的易读性");
		news5.setNewsDate("2014-3-7");
		news5.setNewsTags("css");
		news5.setNewsClick(1);
		
		news6.setNewsID(6);
		news6.setNewsTitle("云计算");
		news6.setNewsContent("  云计算[1]（cloud computing）是基于互联网的相关服务的增加、使用和交付模式，通常涉及通过互联网来提供动态易扩展且经常是虚拟化的资源。云是网络、互联网的一种比喻说法。过去在图中往往用云来表示电信网，后来也用来表示互联网和底层基础设施的抽象。"+
 "对云计算的定义有多种说法。对于到底什么是云计算，至少可以找到100种解释。[2]目前广为接受的是中国云计算专家咨询委员会副主任、秘书长刘鹏教授，著云台团队给出的定义：“云计算是通过网络提供可伸缩的廉价的分布式计算能力”。云计算代表了以虚拟化技术为核心、以低成本为目标的动态可扩展网络应用基础设施,是近年来最有代表性的网络计算技术与模式。");
		news6.setNewsDate("2014-3-7");
		news6.setNewsTags("云计算");
		news6.setNewsClick(1);
		
		newslist.add(news1);
		newslist.add(news2);
		newslist.add(news3);
		newslist.add(news4);
		newslist.add(news5);
		newslist.add(news6);
		
		tagslist = new ArrayList<Tags>();
		Tags tag1 = new Tags();
		Tags tag2 = new Tags();
		Tags tag3 = new Tags();
		Tags tag4 = new Tags();
		tag1.setTagId(1);
		tag1.setTagName("css");
		
		tag2.setTagId(2);
		tag2.setTagName("html");
		
		tag3.setTagId(3);
		tag3.setTagName("jquery");
		
		tag4.setTagId(4);
		tag4.setTagName("云");
		
		tagslist.add(tag1);
		tagslist.add(tag2);
		tagslist.add(tag3);
		tagslist.add(tag4);
		
		adminlist = new ArrayList<Admin>();
		Admin admin = new Admin();
		admin.setAdminId(1);
		admin.setIdcode("haowenhao");
		admin.setLoginname("123");
		admin.setPassword("321");
		adminlist.add(admin);
		
		System.out.println("init NoSQlDataDource end newslist:"+newslist.size());
	}
	public List<News> getNewslist(int page, String key, String tag, int pagesize) {
		List<News> result = new ArrayList<News>();
		Iterator<News> it = newslist.iterator();
		while(it.hasNext()){
			News temp = it.next();
			if(temp.getNewsTitle().indexOf(key)>=0&&temp.getNewsTags().indexOf(tag)>=0)
				result.add(temp);
		}
		int offset = page<=1?0:(page-1)*pagesize;
		int end = (offset+pagesize)>result.size()?result.size():(offset+pagesize);
		//System.out.println("getNewslist offset:"+offset);
		//System.out.println("getNewslist result:"+result.subList(offset, end).size());
		return result.subList(offset, end);
	}
	public int getAllCount(String key, String tags){
		int result = 0;
		Iterator<News> it = newslist.iterator();
		while(it.hasNext()){
			News temp = it.next();
			if(temp.getNewsTitle().indexOf(key)>=0&&temp.getNewsTags().indexOf(tags)>=0)
				result++;
		}
		//System.out.println("getAllCount result:"+result);
		return result;
	}
	public List<Tags> getTagslist() {
		return tagslist;
	}
	
	public Admin getAdmin(String username, String password) {
		Iterator<Admin> it = adminlist.iterator();
		Admin admin = null;
		while(it.hasNext()){
			Admin temp = it.next();
			if(temp.getLoginname().indexOf(username)>=0&&temp.getPassword().indexOf(password)>=0){
				admin = temp;
				break;
			}
		}
		return admin;
	}
	public News getNewsById(int id) {
		Iterator<News> it = newslist.iterator();
		News result = null;
		while(it.hasNext()){
			News temp = it.next();
			if(temp.getNewsID() == id){
				result = temp;
				break;
			}
		}
		return result;
	}
	public List<News> getAroundNews(int id) {
		List<News> result = new ArrayList<News>();
		Iterator<News> it = newslist.iterator();

		while(it.hasNext()){
			News temp = it.next();
			if(temp.getNewsID() == id){
				if(id>1){
					News pre = newslist.get(id-2);
					pre.setAround("pre");
					result.add(pre);
					//System.out.println("newslist.size()"+newslist.size());
				}if(id<newslist.size()){
					News next = newslist.get(id);
					//System.out.println("getAroundNews:next"+next.getNewsID());
					next.setAround("next");
					result.add(next);
				}
				//System.out.println("getAroundNews:result"+id+";"+result.size());
				break;
			}
		}
		return result;
	}
	
}
