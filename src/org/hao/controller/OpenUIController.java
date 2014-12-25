package org.hao.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hao.common.LocalSession;
import org.hao.common.Parameter;
import org.hao.common.StringParse;
import org.hao.common.Tools;
import org.hao.po.Admin;
import org.hao.po.ImageCode;
import org.hao.po.News;
import org.hao.po.ParameterPo;
import org.hao.po.SaveMessage;
import org.hao.po.Tags;
import org.hao.po.VisitCount;
import org.hao.serviceImply.MainServiceImply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/blog")
public class OpenUIController {
	@Resource(name="mainService")
	private MainServiceImply mainService;
	
	@RequestMapping("/uEditorConfig")
	public void uEditorConfig(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		 request.setCharacterEncoding( "utf-8" );
		 response.setHeader("Content-Type" , "text/html");
		 String path = request.getContextPath();
		 String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		 response.getWriter().write( new ActionEnter( request, rootPath ).exec() );

	}
	@RequestMapping("/center")
	public String center(){
		return "center";
	}
	/**统计显示**/
	/**
	 * 取得热门文章
	 */
	@RequestMapping("/hotNews")
	public void getHotNews(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		response.getWriter().write(mainService.getHotNews(10));
		response.getWriter().close();
	}
	/**
	 * 网站统计
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/webStat")
	public void getStat(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		response.getWriter().write(mainService.getWebStat(Tools.getToday("yyyy-MM-dd")));
		response.getWriter().close();
		
	}
	/**
	 * 最新留言
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/newSaveMsg")
	public void getnewSaveMsg(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		response.getWriter().write(mainService.getNewMsg(10));
		response.getWriter().close();
	}
	/**标签管理**/
	/**
	 * 标签管理
	 */
	@RequestMapping("/tagManage")
	public String  tagManage(HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","tagManage");
		List<Tags> tagslist =  mainService.getTags();
		request.setAttribute("tagslist",tagslist);
		
		return "tagManage";
	}
	/**
	 * 根据标签 显示文章
	 * @param page tagname
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/tag/{tagname}/{page}")
	public String tag(@PathVariable String tagname, @PathVariable int page, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		List<News> newslist = mainService.getByTag(tagname,page);
		
		if(newslist!=null){
			int totalpage = mainService.getNewsPageCount(tagname, Parameter.PAGE_SIZE, 1);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		request.setAttribute("newslist",newslist);
		request.setAttribute("PAGESIZE",Parameter.PAGE_SIZE);
		Tags tag = new Tags();
		tag.setTagName(tagname);
		request.setAttribute("tag",tag);
		request.setAttribute("tagslist", mainService.getTags());
		
		return "tag";
	}
	@RequestMapping(value="/addTag",method=RequestMethod.POST)
	public String addTag(HttpServletRequest request ,HttpServletResponse response, String tagname) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","tagManage");
		mainService.addTag(tagname);

		List<Tags> tagslist =  mainService.getTags();
		request.setAttribute("tagslist",tagslist);
		
		return "tagManage";
	}
	@RequestMapping(value="/updateTag",method=RequestMethod.POST)
	public String updateTag(HttpServletRequest request ,HttpServletResponse response, Tags tags) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","tagManage");
		mainService.updateTag(tags);

		List<Tags> tagslist =  mainService.getTags();
		request.setAttribute("tagslist",tagslist);
		
		return "tagManage";
	}
	@RequestMapping(value="/removeTag/{tagid}",method=RequestMethod.GET)
	public String removeTag(@PathVariable int tagid,HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","tagManage");
		mainService.removeTag(tagid);

		List<Tags> tagslist =  mainService.getTags();
		request.setAttribute("tagslist",tagslist);
		
		return "tagManage";
	}
	/**
	 * 列出文章进行管理
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/list/{page}")
	public String  listByPage(@PathVariable int page, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		page = page<1?1:page;
		request.setAttribute("sidebar","article");
		List<News> newslist =  mainService.search("", page);
		request.setAttribute("newslist",newslist);
		
		if(newslist!=null){
			int totalpage = mainService.getNewsPageCount("", Parameter.PAGE_SIZE, 0);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		return "articleManage";
	}
	@RequestMapping("/pagination/{currentpage}")
	public void  pageCount(@PathVariable int currentpage, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		response.getWriter().write(mainService.getPaginationForAJax(currentpage,Parameter.PAGE_SIZE));
		response.getWriter().close();
	}
	@RequestMapping("/index/{page}")
	public String index(@PathVariable int page, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		page = page<1?1:page;
		
		request.setAttribute("tagslist", mainService.getTags());
		List<News> newslist = mainService.getNews(page);
		
		if(newslist!=null){
			int totalpage = mainService.getNewsPageCount("", Parameter.PAGE_SIZE, 0);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		request.setAttribute("newslist",newslist);
		request.setAttribute("PAGESIZE",Parameter.PAGE_SIZE);
		
		return "index";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		int page=1;
		if(null!=request.getParameter("page"))page = Integer.parseInt(request.getParameter("page").toString());
		String keys="";
		if(null!=request.getParameter("keys"))keys = request.getParameter("keys").toString();
		
		List<News> newslist = mainService.search(keys,page);
		
		if(newslist!=null){
			int totalpage = mainService.getNewsPageCount("", Parameter.PAGE_SIZE, 0);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		request.setAttribute("newslist",newslist);
		request.setAttribute("PAGESIZE",Parameter.PAGE_SIZE);
		request.setAttribute("keys",keys);
		request.setAttribute("tagslist", mainService.getTags());
		return "search";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request ,HttpServletResponse response,Admin admin) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		Admin loginadmin = mainService.login(admin.getLoginname(), admin.getPassword(), admin.getIdcode());
		String imagecode = request.getSession().getAttribute("imagecode").toString();
		if(imagecode==null||"".equals(imagecode)||!imagecode.equals(admin.getImagecode())){
			request.setAttribute("msg","<script>alert('验证码不正确!')</script>");
			return "OSPF";
		}
		if(loginadmin!=null){//login success
			//session
			request.getSession().setAttribute("admin", loginadmin);
			//LocalSession.setAttribute("admin", loginadmin);
			request.setAttribute("msg","login success!");
			request.setAttribute("parameter",new Parameter());
			
			return "center";
		}else{//login faile
			request.setAttribute("msg","<script>alert('login fail!')</script>");
			return "OSPF";
		}
	}
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		LocalSession.remove("admin");
		request.getSession().removeAttribute("admin");
		return "OSPF";
	}
	
	@RequestMapping("/showLogin")
	public String showLogin(HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		/*Admin loginadmin = mainService.login(admin.getLoginname(), admin.getPassword(), admin.getIdcode());
		if(loginadmin!=null){//login success
			//session
			request.getSession().setAttribute("admin", loginadmin);
			request.setAttribute("msg","login success!");
			return "main.jsp";
		}else{//login faile
			request.setAttribute("msg","login faile!");
			return "index.jsp";
		}*/
		
		return "OSPF";
		
	}
	
	@RequestMapping(value="/show/{id}")
	public String show(@PathVariable int id, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		News news = mainService.getNewsById(id);
		//添加点击率
		mainService.addClick(id);
		request.setAttribute("news", news);
		//给出上一篇及下一篇文章连接
		Map<String,News> around = mainService.getAroundArticle(id);
		request.setAttribute("pre", around.get("pre"));
		request.setAttribute("next", around.get("next"));
		request.setAttribute("tagslist", mainService.getTags());
		//显示留言
		//request.setAttribute("saveMsg", mainService.getSaveMessageByArticle(id));
		
		return "article";
	}
	
	@RequestMapping(value="/toParameter")
	public String toParameter(HttpServletRequest request ,HttpServletResponse response, ParameterPo parameter) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		
		request.setAttribute("sidebar","parameter");
		request.setAttribute("parameter",new Parameter());
		
		return "parameterSet";
		
	}
	@RequestMapping(value="/parameter",method=RequestMethod.POST)
	public String parameter(HttpServletRequest request ,HttpServletResponse response, ParameterPo parameter) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		
		request.setAttribute("parameter",new Parameter());
		request.setAttribute("sidebar","parameter");
		mainService.updateParameter(parameter);
		
		return "parameterSet";
		
	}
/**文章管理**/
	@RequestMapping(value="/toAdd")
	public String toAddArticle(HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","article");
		request.setAttribute("tags",mainService.getTags());
		request.setAttribute("today",Tools.getToday("yyyy-MM-dd"));
		return "addarticle";
	}
	@RequestMapping(value="/toUpdate/{newsid}")
	public String toUpdateArticle(@PathVariable int newsid, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","article");
		request.setAttribute("news",mainService.getNewsById(newsid));
		return "updateArticle";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addArticle(HttpServletRequest request ,HttpServletResponse response, News news) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","article");
		mainService.addNews(news);
		request.setAttribute("tags",mainService.getTags());
		request.setAttribute("today",Tools.getToday("yyyy-MM-dd"));
		return "addarticle";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateArticle(HttpServletRequest request ,HttpServletResponse response, News news) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","article");
		mainService.updateNews(news);
		request.setAttribute("news",mainService.getNewsById(news.getNewsID()));
		return "updateArticle";
	}
	/**
	 * 删除文章
	 * @param newsid
	 * @param request
	 * @param response
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/remove/{newsid}")
	public String removeArtice(@PathVariable int newsid, HttpServletRequest request ,HttpServletResponse response, SaveMessage message) throws IOException{  
		 //response,request会自动传进来
		Admin admininfo = (Admin)request.getSession().getAttribute("admin");
		//Admin admininfo = (Admin)LocalSession.getAttribute("admin");
		if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		request.setAttribute("sidebar","article");
		int page = 1;
		mainService.removeNews(newsid);
		List<News> newslist =  mainService.search("", page);
		request.setAttribute("newslist",newslist);
		
		if(newslist!=null){
			int totalpage = mainService.getNewsPageCount("", Parameter.PAGE_SIZE, 0);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		return "articleManage";
	}
	
	/**
	 * 对文章点赞
	 * @param newsid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/praise/{newsid}")
	public void addPraise(@PathVariable int newsid, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		int praise = mainService.addPraise(newsid);
		//System.out.println(praise);
		response.getWriter().write(""+praise);
		
		response.getWriter().close();
	}
	/**
	 * 取得验证码图片
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/image")
	public void getImage( HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		//System.out.println(praise);
		ImageCode imgCode = mainService.getImage();
		request.getSession().setAttribute("imagecode", imgCode.getStrcode());
		ImageIO.write(imgCode.getImg(), "JPEG", response.getOutputStream()); 

	}
	/**留言管理**/
	
	/**
	 * 对文章进行留言
	 */
	@RequestMapping(value="/saveMessage",method=RequestMethod.POST)
	public void saveMessage( HttpServletRequest request ,HttpServletResponse response, SaveMessage message) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		//System.out.println(praise);
		
		String imagecode = request.getSession().getAttribute("imagecode").toString();
		if(imagecode==null||"".equals(imagecode)||!imagecode.equals(message.getImagecode())){
			response.getWriter().write("验证码不正确!");
		}else{
			message.setSavedate(Tools.getToday("yyyy-MM-dd"));
			if(message!=null&&message.getMessage()!=null){
				message.setMessage(StringParse.Html2Text(message.getMessage()));
				mainService.SaveMessage(message);
				response.getWriter().write("留言成功!");
			}
		}
		response.getWriter().close();
	}
	
	/**
	 * 回复留言
	 * @param request
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public void reply( HttpServletRequest request ,HttpServletResponse response, SaveMessage message) throws IOException{  
		 //response,request会自动传进来
		//AdminInfo admininfo = (AdminInfo)request.getSession().getAttribute("admininfo");
		//if(admininfo == null) return Parameter.OUT_TIME_PAGE;
		//System.out.println(praise);
		String imagecode = request.getSession().getAttribute("imagecode").toString();
		if(imagecode==null||"".equals(imagecode)||!imagecode.equals(message.getImagecode())){
			response.getWriter().write("验证码不正确!");
		}else{
			response.getWriter().write("留言成功!");
		}
		response.getWriter().close();
	}
	/**
	 * 留言管理
	 */
	@RequestMapping(value="/getSaveMsgList/{page}")
	public String getSaveMsgByList(@PathVariable int page, HttpServletRequest request ,HttpServletResponse response) throws IOException{  
		 //response,request会自动传进来
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin == null) return Parameter.OUT_TIME_PAGE;
		page = page<1?1:page;
		request.setAttribute("sidebar","article");
		List<SaveMessage> msglist =  mainService.getSaveMessageList(page,Parameter.PAGE_SIZE);
		request.setAttribute("msglist",msglist);
		
		if(msglist!=null){
			int totalpage = mainService.getSageMsgPageCount(Parameter.PAGE_SIZE);

			int responsepage = page==0?1:page;
			int responsePageEnd = totalpage>10?10:totalpage;
			int responsePageStart = 1;
			if(totalpage>10&&responsepage>5){
				responsePageEnd = responsepage+4>totalpage?totalpage:responsepage+4;
				responsePageStart = responsepage-5<1?1:responsepage-5;
			}
			request.setAttribute("responsePageStart", responsePageStart);
			request.setAttribute("responsePageEnd", responsePageEnd);
			request.setAttribute("CURRENTPAGE", responsepage);
			request.setAttribute("totalpage", totalpage);
		}
		return "SaveMessageManage";
	}
	/**
	 * 删除留言
	 */
	@RequestMapping(value="/remaveMsg/{msgId}")
	public String removeMsgByID(@PathVariable String msgId, HttpServletRequest request, HttpServletResponse response){
		mainService.removeMsg(msgId);
		
		return "SaveMessageManage";
	}
	
}
