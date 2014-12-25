package org.hao.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ItemScan {

	private List<List<String>> resultSet;
	private List<List<String>> resource;
	private FPTree fpTree = new FPTree();
	
	public void scan(int min){
		List<ItemSet> itset = new ArrayList<ItemSet>();
		List<ItemSet> itsetResult = new ArrayList<ItemSet>();
		
		Iterator<List<String>> it_out = resource.iterator();
		while(it_out.hasNext()){
			List<String> oneSet = it_out.next();
			Iterator<String> it_in = oneSet.iterator();
			while(it_in.hasNext()){
				
				String item = it_in.next();
				
				ItemSet ItemTemp = isContain(itset,item);
				if(ItemTemp!=null){
					ItemTemp.addSeq(1);
				}else{
					//System.out.println("add new item："+item);
					ItemSet newItem = new ItemSet();
					newItem.setItem(item);
					newItem.setSeq(1);
					itset.add(newItem);
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		int length = itset.size();
		for(int i=0;i<length;i++){
			ItemSet in = itset.get(i);
			if(in.getSeq()<min){
				//System.out.println("remove item："+itset.get(i).getItem());
			}else{
				itsetResult.add(in);
				sb.append(in.getItem());
				sb.append(":");
				sb.append(in.getSeq());
				sb.append(",");
			}
		}
		sb.append("}");
		
		System.out.println("扫描后各元素频度为：\n"+sb.toString());
		
		//重新整理resource 生成 resultSet
		StringBuffer sb2 = new StringBuffer();
		resultSet = new ArrayList<List<String>>();
		Iterator<List<String>> source_it = resource.iterator();
		while(source_it.hasNext()){
			List<String> source_one = source_it.next();
			List<String> resultSet_one = new ArrayList<String>();
			Iterator<String> source_one_it = source_one.iterator();
			sb2.append("{");
			while(source_one_it.hasNext()){
				String source_one_item = source_one_it.next();
				ItemSet temp = isContain(itsetResult,source_one_item);
				if(temp!=null){
					resultSet_one.add(temp.getItem());
					sb2.append(temp.getItem());
					sb2.append(":");
					sb2.append(temp.getSeq());
					sb2.append(",");
				}
			}
			resultSet.add(resultSet_one);
			sb2.append("}\n");
		}
		System.out.println("重新整理后各项为：\n"+sb2.toString());
		
	}
	
	public FPTree getFPTree(){
		fpTree = new FPTree();
		fpTree.initTree();
		Iterator<List<String>> it_out = resultSet.iterator();
		while(it_out.hasNext()){
			List<String> oneSet = it_out.next();
			oneTreeAdd(oneSet);
		}
		
		return fpTree;
	}
	private void oneTreeAdd(List<String> oneSet){
		Iterator<String> oneSet_it = oneSet.iterator();
		FPTreeNode parentNode = fpTree.root;
		while(oneSet_it.hasNext()){
			//在当前节点子节点中寻找是否含有这一元素
			List<FPTreeNode> cunrrentNodeList = parentNode.getChildrenNode();
			String item = oneSet_it.next();
			Iterator<FPTreeNode> nodeit = cunrrentNodeList.iterator();
			boolean flag = false;
			while(nodeit.hasNext()){
				FPTreeNode node = nodeit.next();
				//如果有 这一节点值加1 这一节点作为下次循环的父节点
				if(node.getNodeName().equals(item)){
					parentNode = node;
					node.addData(1);
					flag = true;
					break;
				}
			}
			//如果没有这一子节点 加入新子节点 并将新节点作为下次循环父节点
			if(!flag){
				FPTreeNode newnode = new FPTreeNode();
				newnode.setNodeData(1);
				newnode.setNodeName(item);
				newnode.setParentNode(parentNode);
				newnode.setChildrenNode(new ArrayList<FPTreeNode>());
				cunrrentNodeList.add(newnode);
				parentNode = newnode;
			}
		}
	}
	public void setResource(List<List<String>> resource) {
		this.resource = resource;
	}
	
	private ItemSet isContain(List<ItemSet> itemset, String item){
		Iterator<ItemSet> it = itemset.iterator();
		while(it.hasNext()){
			ItemSet oneitem = it.next();
			
			if(item.equals(oneitem.getItem())){
				
				return oneitem;
			}
			//System.out.println("对比  "+oneitem.getItem()+":"+item);
		}
		return null;
	}
	public Map<String,List<CondPatternBase>> conditionalPatternBases(){
		//用map 存储 conditionalPatternBases
		Map<String,List<CondPatternBase>> conditionalPattern = new HashMap<String,List<CondPatternBase>>();
		Stack<FPTreeNode> nodeStack = new Stack<FPTreeNode>();
		nodeStack.push(fpTree.root);
		while(!nodeStack.empty()){
			FPTreeNode currentNode = nodeStack.pop();
			if("root".equals(currentNode.getNodeName())){
				List<FPTreeNode> nodelist = currentNode.getChildrenNode();
				Iterator<FPTreeNode> it = nodelist.iterator();
				while(it.hasNext()){
					nodeStack.push(it.next());
				}
			}else{
				//判断这一节点name 并向上寻找父节点 将这一节点的信息存入对应map
				FPTreeNode insertDataNode = currentNode;
				List<String> patternList = new ArrayList<String>();
				int minData = 0;
				while(!insertDataNode.getNodeName().equals("root")){
					if(insertDataNode == currentNode) 
						minData = insertDataNode.getNodeData();
					
					minData = minData<insertDataNode.getNodeData()?minData:insertDataNode.getNodeData();
					patternList.add(insertDataNode.getNodeName());
					//向上寻找
					insertDataNode = insertDataNode.getParentNode();
				}
				//存入map
				CondPatternBase cpb = new CondPatternBase();
				cpb.setCount(minData);
				cpb.setPattern(patternList);
				if(conditionalPattern.containsKey(currentNode.getNodeName())){
					conditionalPattern.get(currentNode.getNodeName()).add(cpb);
				}else{
					List<CondPatternBase> patternlist = new ArrayList<CondPatternBase>();
					patternlist.add(cpb);
					conditionalPattern.put(currentNode.getNodeName(), patternlist);
				}
				//将其子节点压入栈
				List<FPTreeNode> nodelist = currentNode.getChildrenNode();
				Iterator<FPTreeNode> it = nodelist.iterator();
				while(it.hasNext()){
					nodeStack.push(it.next());
				}
			}
				
		}
		return conditionalPattern;
	}
}
