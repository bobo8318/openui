package org.hao.service;

import java.util.List;
import java.util.Map;

import org.hao.fptree.CondPatternBase;
import org.hao.po.Lottery;

public interface FpTreeService {
	
	public Map<String,List<CondPatternBase>> getFPTree(int deep, String beginDate, String endDate);
	public List<Lottery> getLotteryDoubleColor(String beginDate, String endDate);
}
