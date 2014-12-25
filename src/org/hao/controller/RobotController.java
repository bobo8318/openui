package org.hao.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hao.serviceImply.RobotServiceImply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  
@RequestMapping("/iRobot") 
public class RobotController {

	@Resource(name="robotService")
	private RobotServiceImply robotService;
	
	/**
	 * 进入聊天页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/helloRobot",method=RequestMethod.GET)
	public String iRobot(HttpServletRequest request, HttpServletResponse response){
		
		return "helloRobot";
	}
	/**
	 * 聊天
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/chat",method=RequestMethod.POST)
	public void chat(HttpServletRequest request, HttpServletResponse response, String question, String code) throws IOException{
		String answer = robotService.chat(question,code);
		response.getWriter().write(answer);
		response.getWriter().close();
	}
}
