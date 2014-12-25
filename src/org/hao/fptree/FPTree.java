package org.hao.fptree;

import java.util.ArrayList;

public class FPTree {

	public FPTreeNode root;
	
	public void initTree(){
		root = new FPTreeNode();
		root.setNodeName("root");
		root.setChildrenNode(new ArrayList<FPTreeNode>());
	}
	
}
