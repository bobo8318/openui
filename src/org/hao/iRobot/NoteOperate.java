package org.hao.iRobot;

import org.hao.dao.BaseDao;

public class NoteOperate implements OperateStrategy {

	@Override
	public String operate(String question, TreeNode recentNode, BaseDao basedao) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		if(question.lastIndexOf(" ")<0||question.lastIndexOf("pwd")<0) {
			sb.append(recentNode.getFatherId());
			sb.append(";");
			sb.append("密码输入格式不正确");
		}
		else{
			String pwd = question.substring(question.lastIndexOf(" ")+1,question.length());
			if(!"xtc123".equals(pwd)) {
				sb.append(recentNode.getFatherId());
				sb.append(";");
				sb.append("密码错误");
			}else{
				sb.append(recentNode.getNodeId());
				sb.append(";");
				sb.append("进入记事本成功，请输入内容");
			}
		}
		return sb.toString();
	}

}
