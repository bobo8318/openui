package org.hao.fptree;

import java.util.List;

public class FPTreeNode {

	private int nodeID;
	private int nodeData;
	private String nodeName;
	private FPTreeNode parentNode;
	private List<FPTreeNode> childrenNode;
	
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public int getNodeData() {
		return nodeData;
	}
	public void setNodeData(int nodeData) {
		this.nodeData = nodeData;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public FPTreeNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(FPTreeNode parentNode) {
		this.parentNode = parentNode;
	}
	public List<FPTreeNode> getChildrenNode() {
		return childrenNode;
	}
	public void setChildrenNode(List<FPTreeNode> childrenNode) {
		this.childrenNode = childrenNode;
	}
	public void addData(int data){
		nodeData += data;
	}
	
	
}
