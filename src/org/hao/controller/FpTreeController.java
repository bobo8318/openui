package org.hao.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hao.common.Tools;
import org.hao.fptree.CondPatternBase;
import org.hao.serviceImply.FpTreeServiceImply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lottery")
public class FpTreeController {
	@Resource(name="FpTreeService")
	private FpTreeServiceImply fpTreeService;
	
	@RequestMapping("/DCFind")
	public void DCFind(){
		Map<String,List<CondPatternBase>> result = fpTreeService.getFPTree(10,"","");
		
		List<CondPatternBase> show = result.get("1");
		Iterator<CondPatternBase> iterator_it = show.iterator();
		while(iterator_it.hasNext()){
			CondPatternBase cpb = iterator_it.next();
			System.out.println(cpb.getCount());
			Iterator<String> ppp = cpb.getPattern().iterator();
			while(ppp.hasNext()){
				System.out.println(ppp.next());
			}
		}
		
	}
	
	@RequestMapping(value="/doubleColor",method=RequestMethod.GET)
	public String getLottery(HttpServletRequest request ,HttpServletResponse response, String beginDate, String endDate) throws IOException{ 
		if(beginDate==null||"".equals(beginDate))beginDate = Tools.getFewTodaysAgo("yyyy-MM-dd", 60);
		if(endDate==null||"".equals(endDate)) endDate = Tools.getToday("yyyy-MM-dd");
		request.setAttribute("dcList",fpTreeService.getLotteryDoubleColor(beginDate, endDate));
		request.setAttribute("beginDate",beginDate);
		request.setAttribute("endDate",endDate);
	
		return "lottery";
	}
	
	@RequestMapping(value="/updateData",method=RequestMethod.GET)
	public String updateData(HttpServletRequest request ,HttpServletResponse response, String beginDate, String endDate) throws IOException{ 
	
		return "lottery/addData";
	}

}
