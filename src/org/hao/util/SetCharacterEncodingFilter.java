
package org.hao.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hao.common.LocalSession;
import org.hao.common.Parameter;
import org.hao.common.Tools;
import org.hao.po.VisitCount;
import org.hao.service.MainService;
import org.springframework.web.context.WebApplicationContext;

public class SetCharacterEncodingFilter implements Filter
{
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException
    {

        this.filterConfig = filterConfig;
    }
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
    {
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpServletResponse res = (HttpServletResponse)response;
        try
        {
        	String uri = req.getRequestURI();
        	if(uri.indexOf(".jsp")>=0){
        		//System.out.println("somebody is reading jsp file! forbidden!");
        		res.sendError(HttpServletResponse.SC_NOT_FOUND);
        	}
            /*f(!Config.encode.equalsIgnoreCase("utf-8"))
            {//如果当前项目采用的不是UTF-8编码
                HttpServletRequest httpRequest=(HttpServletRequest)request;
                String contentType=httpRequest.getContentType();
                if (contentType != null
                        && contentType.toLowerCase().startsWith(
                                "application/x-www-form-urlencoded; charset=utf-8"))
                {//报表提交
                    request.setCharacterEncoding("UTF-8");
                }else
                {
                    request.setCharacterEncoding("UTF-8");
                }
                response.setContentType("text/html; charset=UTF-8");
            }else*/
            {
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                
                //设置头部通用 底部通用
                request.setAttribute("admin", LocalSession.getAttribute("admin"));
                request.setAttribute("HEADER", Parameter.HEADER);
                request.setAttribute("FOOTER", Parameter.FOOTER);
                request.setAttribute("weibo", Parameter.weibo);
                request.setAttribute("email", Parameter.email);
                request.setAttribute("introduce", Parameter.introduce);
                request.setAttribute("version", Parameter.VERSION);
                //访问统计
                String today = Tools.getToday("yyyy-MM-dd");
              //判断是不是新的一天
        		if(today.equals(Parameter.visit_date)){
        			Parameter.visit_stat_today += 1;
        		}else//新的一天
        		{
        			//老的数据存入数据库 重新计数新一天
        			VisitCount vc = new VisitCount();
        			vc.setCountdate(today);
        			vc.setVisitstat(Parameter.visit_stat_today);
        			
        			WebApplicationContext context = (WebApplicationContext) filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        			MainService mainService = (MainService) context.getBean("mainService");
        			mainService.addVisitStat(vc);
        			//新的一天
        			Parameter.visit_date = today;
        			Parameter.visit_stat_today = 1;
        			
        		}
                
            }
            chain.doFilter(request,response);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void destroy()
    {
        this.filterConfig = null;
    }
}