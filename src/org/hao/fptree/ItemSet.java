package org.hao.fptree;

public class ItemSet {

	private String item;
	private int seq;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void addSeq(int add){
		seq += add;
	}
	
}
