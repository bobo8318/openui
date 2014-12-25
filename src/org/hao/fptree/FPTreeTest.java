package org.hao.fptree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FPTreeTest {

	public static void main(String[] argument){
		//初始化数据
		List<List<String>> source = new ArrayList<List<String>>();
		List<String> oneItem = new ArrayList<String>();
		oneItem.add("Bread");oneItem.add("Milk");
		source.add(oneItem);
		
		List<String> twoItem = new ArrayList<String>();
		twoItem.add("Bread");twoItem.add("Diaper");twoItem.add("Beer");twoItem.add("Eggs");
		source.add(twoItem);
		
		List<String> threeItem = new ArrayList<String>();
		threeItem.add("Milk");threeItem.add("Diaper");threeItem.add("Beer");threeItem.add("Coke");
		source.add(threeItem);
		
		List<String> fourItem = new ArrayList<String>();
		fourItem.add("Bread");fourItem.add("Milk");fourItem.add("Diaper");fourItem.add("Beer");
		source.add(fourItem);
		
		List<String> fiveItem = new ArrayList<String>();
		fiveItem.add("Bread");fiveItem.add("Milk");fiveItem.add("Diaper");fiveItem.add("Coke");
		source.add(fiveItem);
		
		ItemScan is = new ItemScan();
		is.setResource(source);
		is.scan(10);
		is.getFPTree();
		//System.out.println(resultTree.root.getChildrenNode().get(0).getChildrenNode().get(0).getChildrenNode().get(0).getNodeData());
		Map<String,List<CondPatternBase>> result = is.conditionalPatternBases();
		List<CondPatternBase> show = result.get("Milk");
		Iterator<CondPatternBase> iterator_it = show.iterator();
		while(iterator_it.hasNext()){
			CondPatternBase cpb = iterator_it.next();
			System.out.println(cpb.getCount());
			Iterator<String> ppp = cpb.getPattern().iterator();
			while(ppp.hasNext()){
				System.out.println(ppp.next());
			}
		}
	}
}
