package org.hao.iRobot;

import java.util.List;

import org.hao.common.StringParse;
import org.hao.dao.BaseDao;
import org.hao.dao.IRobotDao;

public class LearnOperate implements OperateStrategy {

	@Override
	public String operate(String question, TreeNode recentNode, BaseDao basedao) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(recentNode.getNodeId());
		sb.append(";");
		sb.append(recentNode.getAnswer());
		
		
		if(question.indexOf("ask")>=0&&question.indexOf(";")>=0&&question.indexOf("answer")>=0){
			//ask 问题;answer 回答
			String ask = question.substring(question.indexOf("ask")+3,question.indexOf(";"));
			String answer = question.substring(question.indexOf("answer")+6,question.length());
			List<String> askkeys = WordsDivid.getWordsDivid(ask);
			
			TreeNode treeNode = new TreeNode();
			
			treeNode.setAsk(ask);
			treeNode.setAnswer(answer);
			treeNode.setNodekey(StringParse.ListToString(askkeys));
			treeNode.setNodeType(1);
			
			int result = ((IRobotDao)basedao).learn(treeNode);
			if(result<=0){
				sb.append("<br>学习失败，请确定格式正确。");
			}else{
				sb.append("<br>学习成功，今天学的太多了，等我先消化消化。");
			}
		}else{
			sb.append("<br>请确定格式正确");
		}
		return sb.toString();
	}

}
