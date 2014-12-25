package org.hao.serviceImply;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hao.dao.FpTreeDao;
import org.hao.fptree.CondPatternBase;
import org.hao.fptree.FPTree;
import org.hao.fptree.ItemScan;
import org.hao.po.Lottery;
import org.hao.service.FpTreeService;
import org.springframework.stereotype.Service;
@Service("FpTreeService")
public class FpTreeServiceImply implements FpTreeService {
	
	@Resource(name="fpTreeDao")
	private FpTreeDao fpTreeDao;

	@Override
	public Map<String,List<CondPatternBase>> getFPTree(int deep, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		List<Lottery> lotteryData = fpTreeDao.getLottery(beginDate,endDate);
		List<List<String>> source = new ArrayList<List<String>>();
		Iterator<Lottery> iterator = lotteryData.iterator();
		while(iterator.hasNext()){
			List<String> oneItem = new ArrayList<String>();
			Lottery lottery = iterator.next();
			String[] strArray = lottery.getLotteryData().split(",");
			
			for(int i=0;i<strArray.length;i++){
				oneItem.add(strArray[i]);
			}
			
			source.add(oneItem);
		}
		//扫描
		ItemScan is = new ItemScan();
		is.setResource(source);
		is.scan(10);
		is.getFPTree();
		Map<String,List<CondPatternBase>> result = is.conditionalPatternBases();
		return result;
	}

	@Override
	public List<Lottery> getLotteryDoubleColor(String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return fpTreeDao.getLottery(beginDate, endDate);
	}
}
