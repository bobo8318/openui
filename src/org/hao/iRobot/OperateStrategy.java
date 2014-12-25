package org.hao.iRobot;

import org.hao.dao.BaseDao;

public interface OperateStrategy {
	public String operate(String ask, TreeNode recentNode, BaseDao basedao);
	
}
