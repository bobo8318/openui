package org.hao.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hao.iRobot.StrategyTreePool;
import org.hao.iRobot.TreeNode;
import org.hao.po.TripleValue;
import org.springframework.stereotype.Repository;

@Repository("iRobotDao")
public class IRobotDao extends BaseDao {

	@Resource(name="stp")
	private StrategyTreePool stp;
	
	public List<TreeNode> getNodeList(int page, int page_size){
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.RobotMapper.getNodeCount");
	}
	
	public int getNodeCount(){
		return this.readSqlSession.selectOne("org.hao.dao.sqlmap.RobotMapper.getNodeCount");
	}
	
	@SuppressWarnings("unchecked")
	public TreeNode getNodeInData(List<String> keywords){
		
		System.out.println("getNodeInData 从数据库中取");
		TreeNode answer = this.readSqlSession.selectOne("org.hao.dao.sqlmap.RobotMapper.getAnswer",keywords);
		saveNodeToPool(answer);
		return answer;
	}
	/**
	 * 从池中区node
	 */
	public TreeNode getAnswerInPool(List<String> keywords){
		return stp.getStrategyTree(keywords);
	}
	/**
	 * sin 2013-8-13
	 */
	public TreeNode getNodeById(int id){
		TreeNode answer = stp.getNodeByID(id);
		
		if(answer == null){
			System.out.println("getNodeById+"+id);
			return getNodeByIdInDataBase(id);
		}
		else{
			return answer;
		}
	}
	/**
	 * sin 2013-8-8
	 * 根据id 在数据库中取node 
	 */
	public TreeNode getNodeByIdInDataBase(int id){
		//System.out.println("getNodeByIdInDataBase");
		TreeNode answer = this.readSqlSession.selectOne("org.hao.dao.sqlmap.RobotMapper.getNodeById",id);
		saveNodeToPool(answer);
		return answer;
	}
	/**
	 * sin 2013-8-12
	 * 学习新知识
	 */
	public int learn(TreeNode treeNode){
		 this.writerSqlSession.insert("org.hao.dao.sqlmap.RobotMapper.learn",treeNode);
		 return 1;
	}
	/**
	 * 插入加密信息
	 */
	public void insertNewNote(TreeNode treeNode){
		 this.writerSqlSession.insert("org.hao.dao.sqlmap.RobotMapper.learn",treeNode);
	}
	/**
	 *  sin 2014-12-23
	 *  节点存入pool
	 */
	private void saveNodeToPool(TreeNode treeNode){
		if(treeNode!=null){
			System.out.println("**********存入新节点 nodeid："+treeNode.getNodeId()+" keys:"+treeNode.getNodekey());
			TripleValue tv = new TripleValue();
			tv.setValueA(treeNode.getNodekey());
			tv.setValueB(treeNode.getNodeId());
			tv.setValueC(treeNode.getNodeType());
			
			stp.addKeyId(tv);
			
			stp.addNode(treeNode);
			System.out.println("**********当前 keyIdList ："+stp.getKeyIdListSize()+" 个");
			System.out.println("**********当前 pool ："+stp.getPoolSize()+" 个");
		}
	}
	/**
	 * sin 2013-8-21
	 * 清缓存
	 */
	public void clearPool(){
		stp.clearPool();
	}
	
	/**
	 * sin 2013-8-7
	 * 根据关键字取得策略树
	 */
	public TreeNode getAnswer(List<String> keys){
		TreeNode answer = stp.getStrategyTree(keys);//取得回答问题策略树根节点
		if(answer == null)//池中没有回答
			return getNodeInData(keys);//从数据库中取
		else return answer;
	}
}
