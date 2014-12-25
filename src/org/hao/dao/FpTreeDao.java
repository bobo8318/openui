package org.hao.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hao.po.Lottery;
import org.springframework.stereotype.Repository;

@Repository("fpTreeDao")
public class FpTreeDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<Lottery> getLottery(String beginDate, String endDate)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return this.readSqlSession.selectList("org.hao.dao.sqlmap.FpTreeMapper.getLotteryData",map);
	}
	
	
}
