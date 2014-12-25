package org.hao.serviceImply;

import java.util.List;

import javax.annotation.Resource;

import org.hao.iRobot.LearnOperate;
import org.hao.iRobot.NoteOperate;
import org.hao.iRobot.OperateStrategy;
import org.hao.iRobot.StrategyTreePool;
import org.hao.iRobot.TreeNode;
import org.hao.iRobot.WordsDivid;
import org.hao.common.StringParse;
import org.hao.dao.FpTreeDao;
import org.hao.dao.IRobotDao;
import org.hao.service.RobotService;
import org.springframework.stereotype.Service;
@Service("robotService")
public class RobotServiceImply implements RobotService {

	@Resource(name="iRobotDao")
	private IRobotDao robotDao;
	
	private OperateStrategy operateStrategy;

	@Override
	public String chat(String question, String lastNodeId) {
		// TODO Auto-generated method stub
		//清缓存 clear pool
		if("clearpool".equals(question)){
			robotDao.clearPool();
			return ";缓存清理完成";
		}
		//
		List<String> keys = WordsDivid.getWordsDivid(question);
		
		if(keys!=null&&keys.size()>1){
			if("showkeys".equalsIgnoreCase(keys.get(0))){
				return lastNodeId+";"+StringParse.ListToString(keys);
			}
			/*if("note".equalsIgnoreCase(keys.get(0))){//加密记录关键
				if("get".equalsIgnoreCase(keys.get(1))){//查询
					TreeNode answer = robotDao.getAnswer(keys.subList(2, keys.size()));
					if(answer!=null)
						return StringParse.StringParser(answer.getNodeId()+";"+answer.getAnswer());
					else
						return ";你说什么？";
				}else if("insert".equalsIgnoreCase(keys.get(1))){//插入
					setOperateStrategy(new LearnOperate());
					return ";插入成功！";
				}
			}*/
		}
		
		//取当前节点
		if(lastNodeId!=null&&!"".equals(lastNodeId)&&!"0".equals(lastNodeId)){
			TreeNode recentTreenode = robotDao.getNodeById(Integer.parseInt(lastNodeId));
			//如果是退出 返回父节点
			if("exit".equals(question)){
				return recentTreenode.getFatherId()+";#/退出成功，返回上一级";
		}
		
		//如果有子节点 优先从子节点中查找
		if(recentTreenode.getChildrenId()!=null&&!"0".equals(recentTreenode.getChildrenId())&&!"".equals(recentTreenode.getChildrenId())){
			String[] childrenid = recentTreenode.getChildrenId().split(",");
			int MaxMatch = 0;
			TreeNode resultNode = null;
			for(int i=0;i<childrenid.length;i++){
				TreeNode childrennode = robotDao.getNodeById(Integer.parseInt(childrenid[i]));
				//进行匹配
				int tempcount = 0;
				for(int j=0;j<keys.size();j++){
					if(childrennode.getNodekey().contains(keys.get(j))){
						tempcount++;
					}
				}
				if(MaxMatch<=tempcount){
					MaxMatch = tempcount;
					resultNode = childrennode;
				}
			}
			//如果匹配出来返回回答
			if(MaxMatch>0&&resultNode!=null){
				//先查看是否有操作
				StringBuffer sb = new StringBuffer();
				String operate = resultNode.getOperate();
				String worktype = resultNode.getWorktype();
				/*sb.append(resultNode.getNodeId());
				sb.append(";");
				sb.append(resultNode.getAnswer());*/
				//如果有操作 进入操作
				if(!"".equals(operate)&&!"0".equals(operate)&&operate!=null){
					//学习
					if("learn".equals(operate)){
						setOperateStrategy(new LearnOperate());
					}else if("pwd".equals(operate)){
						setOperateStrategy(new NoteOperate());
					}
					sb.append(operateStrategy.operate(question, resultNode, robotDao));
				}else{
					if("note".equals(worktype)){
						sb.append(resultNode.getFatherId());
					}else
						sb.append(resultNode.getNodeId());
					sb.append(";");
					sb.append(resultNode.getAnswer());
				}
				return StringParse.StringParser(sb.toString());
			}
		}
		
		}
		//装入新策略树
		TreeNode treenode = robotDao.getAnswer(keys);
		if(treenode!=null)
			return StringParse.StringParser(treenode.getNodeId()+";"+treenode.getAnswer());
		else
			return ";你说什么？";
		
		
	}

	public OperateStrategy getOperateStrategy() {
		return operateStrategy;
	}

	public void setOperateStrategy(OperateStrategy operateStrategy) {
		this.operateStrategy = operateStrategy;
	}
}
