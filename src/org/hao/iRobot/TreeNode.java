package org.hao.iRobot;

public class TreeNode {

	private String nodeId;
	private int nodeType; // 1 root 2 thref
	private String nodekey;
	private String fatherId;
	private String childrenId;
	private String ask;
	private String answer;
	private String operate;
	private String worktype;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public int getNodeType() {
		return nodeType;
	}
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	public String getNodekey() {
		return nodekey;
	}
	public void setNodekey(String nodekey) {
		this.nodekey = nodekey;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	
	
}
