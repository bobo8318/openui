package org.hao.iRobot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hao.dao.BaseDao;
import org.hao.dao.IRobotDao;
import org.hao.po.TripleValue;
import org.springframework.stereotype.Repository;

/**
 * sin 2013-8-7
 * 策略树池 用于存储策略树
 * @author Administrator
 *
 */
@Repository("stp")
public class StrategyTreePool {

	private static Map<String,TreeNode> pool = new HashMap<String,TreeNode>();
	private static List<TripleValue> keyIdList = new ArrayList<TripleValue>();

	/**
	 * sin 2013-8-7
	 * 根据关键字取得策略树
	 */
	public TreeNode getStrategyTree(List<String> keys){
		//遍历key nodeid对列表
		Iterator<TripleValue> iterator = keyIdList.iterator();
		int MaxMatch = 0;
		String rootid = "";
		while(iterator.hasNext()){
			int tempcount = 0;
			TripleValue tv = iterator.next();
			//如果不是根节点继续
			System.out.println("节点类型："+tv.getValueC());
			if("2".equals(tv.getValueC())) continue;
			String source = ","+tv.getValueA().toString()+",";
			for(int i=0;i<keys.size();i++){
				if(source.contains(","+keys.get(i)+",")){
					tempcount++;
				}
			}
			
			if(MaxMatch<=tempcount){
				MaxMatch = tempcount;
				rootid = tv.getValueB().toString();
			}
			System.out.println("**********当前 MaxMatch："+MaxMatch+" tempcount:"+tempcount+" rootid:"+rootid);
		}
		//如果没取到(keys 全部未命中)
		if("".equals(rootid)||MaxMatch<keys.size()){
			//内存中没有 从数据库中查询
			System.out.println("**********池中未命中 返回空");
			return null;
		}
		else {
			System.out.println("**********从 pool 中取");
			return pool.get("id"+rootid);
		}
	}
	
	/**
	 * sin 2013-8-8
	 * 根据ID取node
	 */
	public TreeNode getNodeByID(int id){
		//内存中有没有
		if(pool.containsKey("id"+id)) return pool.get("id"+id);
		else{
			
			return null;
			
		}
	}
	/**
	 * 清缓存
	 */
	public void clearPool(){
		pool.clear();
		keyIdList.clear();
	}
	
	public void addKeyId(TripleValue tv){
		keyIdList.add(tv);
	}
	public void addNode(TreeNode treeNode){
		pool.put("id"+treeNode.getNodeId(), treeNode);
	}
	public int getPoolSize(){
		return pool.size();
	}
	public int getKeyIdListSize(){
		return keyIdList.size();
	}
}
